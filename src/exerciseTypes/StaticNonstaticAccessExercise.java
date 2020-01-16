package exerciseTypes;

import model.exerciseElements.Exercise;
import model.exerciseElements.Figure;
import model.exerciseElements.Solution;
import model.langElements.general.DataType;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.imperative.Expression;
import model.langElements.imperative.Statement;
import model.langElements.objectOriented.ClassElement;
import model.langElements.objectOriented.Field;
import model.langElements.objectOriented.MemberAccess;
import model.langElements.procedural.Method;
import model.langElements.projectStructure.Program_Default1;

import java.util.Random;

public class StaticNonstaticAccessExercise extends Exercise {
    private static final String DEFAULT_mainClassName = "Main";
    private static final String DEFAULT_sampleClassName = "ExampleClass";
    private static final String DEFAULT_objectName = "obj";
    private static final String DEFAULT_varOrMethodName = "something";
    private static final boolean DEFAULT_isMethod = false;
    private static final Value DEFAULT_usedValue = Value.getInt(5);



    public StaticNonstaticAccessExercise(String mainClassName, String sampleClassName, String objectName, String varOrMethodName,
                                         boolean isMethod, Value usedValue,
                                         boolean isStatic, boolean notationByObjectName) {
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
        Variable objVar = new Variable(objectName, exampleClassDataType);
        main.add(Statement.builder()
                .declaration()
                .dataType(objVar.getDataType())
                .addVariable(objVar)
                .initLastVariable(
                        Expression.make(exampleClass.addDefaultConstructor())
                ).build()
        );

        Expression notation = notationByObjectName
                ? Expression.make(objVar)
                : Expression.make(exampleClass)
        ;

        if (isMethod) { //method
            Method sampleMethod = program.getSampleClass().addMethod(varOrMethodName, usedDataType,
                    null);
            if (isStatic) sampleMethod.setStatic();
            main.add(Statement.builder()
                    .methodCall()
                    .method(sampleMethod)
                    .memberOf(notation)
                    .build()
            );
            sampleMethod.add(Statement.builder()
                    .returnStmt()
                    .returnValue(
                            Expression.make(usedValue)
                    ).build()
            );
        } else { //field
            Field sampleField = program.getSampleClass().addField(varOrMethodName, usedDataType);
            if (isStatic) sampleField.setStatic();
            main.add(Statement.builder()
                    .assignment()
                    .leftExp(
                            MemberAccess.makeFieldAccess(notation, sampleField)
                    )
                    .rightExp(usedValue)
                    .build()
            );
        }


        if (!isStatic && !notationByObjectName) {
            solution = new Solution.BoolSolution(false);
        } else {
            solution = new Solution.BoolSolution(true);
        }

        exerciseFigure = new Figure.CodeFigure(program);
    }

    public StaticNonstaticAccessExercise(boolean isMethod, Value usedValue,
                                         boolean isStatic, boolean notationByObjectName) {
        this(DEFAULT_mainClassName, DEFAULT_sampleClassName, DEFAULT_objectName, DEFAULT_varOrMethodName,
                isMethod, usedValue, isStatic, notationByObjectName);
    }

    public StaticNonstaticAccessExercise(boolean isStatic, boolean notationByObjectName) {
        this(DEFAULT_isMethod, DEFAULT_usedValue, isStatic, notationByObjectName);
    }

    public static StaticNonstaticAccessExercise getRandomExercise(boolean balancedAnswers) {
        Random rnd = new Random();
        Value usedValue = Value.getInt(rnd.nextInt(100));
        boolean isMethod = rnd.nextBoolean();
        if (balancedAnswers) {
            boolean answer = rnd.nextBoolean();
            boolean isStatic, notationByObjectName;
            if (!answer) {  //Syntactically incorrect: calling instance with class notation
                isStatic = false;   //instance
                notationByObjectName = false;   //class notation
            } else {
                switch (rnd.nextInt(3)) {
                    case 0: isStatic = false; notationByObjectName = true; break;
                    case 1: isStatic = true; notationByObjectName = false; break;
                    case 2:
                    default: isStatic = true; notationByObjectName = true; break;
                }
            }
            return new StaticNonstaticAccessExercise(isMethod, usedValue, isStatic, notationByObjectName);
        } else {
            boolean isStatic = rnd.nextBoolean();
            boolean notationByObjectName = rnd.nextBoolean();
            return new StaticNonstaticAccessExercise(isMethod, usedValue, isStatic, notationByObjectName);
        }
    }
}
