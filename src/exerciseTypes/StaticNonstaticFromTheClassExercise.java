package exerciseTypes;

import model.exerciseElements.Exercise;
import model.exerciseElements.Figure;
import model.exerciseElements.Solution;
import model.langElements.general.DataType;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.imperative.Assignment;
import model.langElements.imperative.Declaration;
import model.langElements.imperative.Expression;
import model.langElements.imperative.Statement;
import model.langElements.objectOriented.ClassElement;
import model.langElements.objectOriented.Field;
import model.langElements.objectOriented.MemberAccess;
import model.langElements.procedural.FormalParameter;
import model.langElements.procedural.Method;
import model.langElements.procedural.MethodCall;
import model.langElements.projectStructure.Program_Default1;

import java.util.Random;

public class StaticNonstaticFromTheClassExercise extends Exercise {
    private static final String DEFAULT_mainClassName = "Main";
    private static final String DEFAULT_sampleClassName = "ExampleClass";
    private static final String DEFAULT_objectNameInMain = "obj1";
    private static final String DEFAULT_objectNameInCallerMethod = "obj2";
    private static final String DEFAULT_callerMethodName = "method1";
    private static final String DEFAULT_varOrMethodName = "something";
    private static final boolean DEFAULT_isMethod = false;
    private static final Value DEFAULT_usedValue = Value.getInt(5);
    private static final String DEFAULT_parameterName = "p";

    public StaticNonstaticFromTheClassExercise(String mainClassName, String sampleClassName,
                                               String objectNameInMain, String objectNameInCallerMethod,
                                               String callerMethodName, String varOrMethodName,
                                               boolean isMethod, Value usedValue,
                                               boolean isStatic, NOTATION_TYPE notationByWhat) {
        super("Static vs non-static",
                "Accessing a static or non-static field or member of the class",
                "Is the code syntactically correct?",
                null,
                new Solution.BoolSolution(true));

        Program_Default1 program = new Program_Default1();
        program.getMainClass().setName(mainClassName);

        ClassElement exampleClass = program.getSampleClass();
        exampleClass.setName(sampleClassName);
        DataType exampleClassDataType = DataType.makeObjectType(exampleClass);

        DataType usedDataType = (usedValue == null ? DataType.VOID : usedValue.getDataType());

        Method main = program.getEntryPoint();
        Variable objInMain = new Variable(objectNameInMain, exampleClassDataType);
        main.add(
                new Statement.DeclarationStatement(
                    Declaration.getNew(
                            objInMain,
                            Expression.make(
                                    exampleClass.addDefaultConstructor()
                            )
                    )
                )
        );

        Method callerMethod = exampleClass.addMethod(callerMethodName, DataType.VOID, null);
        main.add(Statement.makeExpressionAsStmt(
           MemberAccess.makeMethodAccess(objInMain, new MethodCall(callerMethod))
        ));


        Variable objInCallerMethod = new Variable(objectNameInCallerMethod, exampleClassDataType);
        Expression notation = notationByWhat == NOTATION_TYPE.OBJECT
                ? Expression.make(objInCallerMethod)
                : Expression.make(exampleClass) //class or none (in the last case it will be discarded)
        ;
        callerMethod.add(
                new Statement.DeclarationStatement(
                        Declaration.getNew(
                                objInCallerMethod,
                                Expression.make(
                                        exampleClass.addDefaultConstructor()
                                )
                        )
                )
        );

        if (isMethod) { //method
            Method sampleMethod = program.getSampleClass().addMethod(varOrMethodName, DataType.VOID,
                    FormalParameter.create(usedDataType, DEFAULT_parameterName));
            if (isStatic) sampleMethod.setStatic();
            callerMethod.add(Statement.makeExpressionAsStmt(
               notationByWhat == NOTATION_TYPE.NONE
                    ? Expression.make(sampleMethod)
                    : MemberAccess.makeMethodAccess(
                            notation,
                            new MethodCall(sampleMethod, Expression.make(usedValue)))
            ));
        } else { //field
            Field sampleField = program.getSampleClass().addField(varOrMethodName, usedDataType);
            if (isStatic) sampleField.setStatic();
            callerMethod.add(Statement.makeExpressionAsStmt(
                            new Assignment(
                                notationByWhat == NOTATION_TYPE.NONE
                                    ? Expression.make(sampleField)
                                    : MemberAccess.makeFieldAccess(
                                            notation,
                                            sampleField),
                                Expression.make(usedValue)
                            )
                    )
            );
        }

        if (!isStatic && notationByWhat==NOTATION_TYPE.CLASS) {
            solution = new Solution.BoolSolution(false);
        } else {
            solution = new Solution.BoolSolution(true);
        }

        exerciseFigure = new Figure.CodeFigure(program);
    }

    public StaticNonstaticFromTheClassExercise(boolean isMethod, Value usedValue,
                                               boolean isStatic, NOTATION_TYPE notationByWhat) {
        this(DEFAULT_mainClassName, DEFAULT_sampleClassName,
                DEFAULT_objectNameInMain, DEFAULT_objectNameInCallerMethod,
                DEFAULT_callerMethodName, DEFAULT_varOrMethodName,
                isMethod, usedValue, isStatic, notationByWhat);
    }

    public StaticNonstaticFromTheClassExercise(boolean isStatic, NOTATION_TYPE notationByWhat) {
        this(DEFAULT_isMethod, DEFAULT_usedValue, isStatic, notationByWhat);
    }

    public static StaticNonstaticFromTheClassExercise getRandomExercise(boolean balancedAnswers) {
        Random rnd = new Random();
        Value usedValue = Value.getInt(rnd.nextInt(100));
        boolean isMethod = rnd.nextBoolean();
        if (balancedAnswers) {
            boolean answer = rnd.nextBoolean();
            boolean isStatic;
            NOTATION_TYPE notationByWhat;
            if (!answer) {  //Syntactically incorrect: calling instance with class notation
                isStatic = false;   //instance
                notationByWhat = NOTATION_TYPE.CLASS;   //class notation
            } else {
                if (rnd.nextInt(3) > 1) { //    NON-STATIC --> 1/3 possibility
                    isStatic = false;
                    notationByWhat = NOTATION_TYPE.OBJECT;
                } else {                         //     STATIC --> 2/3 possibility
                    isStatic = true;
                    notationByWhat = NOTATION_TYPE.values()[
                            rnd.nextInt(NOTATION_TYPE.values().length)];
                }
            }
            return new StaticNonstaticFromTheClassExercise(isMethod, usedValue, isStatic, notationByWhat);
        } else {
            boolean isStatic = rnd.nextBoolean();
            NOTATION_TYPE notationByWhat = NOTATION_TYPE.values()[
                    rnd.nextInt(NOTATION_TYPE.values().length)];
            return new StaticNonstaticFromTheClassExercise(isMethod, usedValue, isStatic, notationByWhat);
        }
    }

    public enum NOTATION_TYPE {CLASS, OBJECT, NONE}
}
