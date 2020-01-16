package exerciseTypes;

import model.exerciseElements.*;
import model.langElements.general.DataType;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.imperative.Expression;
import model.langElements.imperative.Statement;
import model.langElements.objectOriented.ClassElement;
import model.langElements.objectOriented.Field;
import model.langElements.objectOriented.MemberAccess;
import model.langElements.procedural.Method;
import model.langElements.projectStructure.Program;
import model.langElements.projectStructure.Program_Default1;

import java.util.ArrayList;
import java.util.List;

public class StaticNonStaticAccess extends ExerciseFactory {
    private static final String PARAM_MAIN_CLASS_NAME = "main class name";
    private static final String PARAM_OBJECT_NAME = "object name";
    private static final String PARAM_VAR_OR_METHOD_NAME = "name of the variable or method";
    private static final String PARAM_IS_METHOD = "is it method?";
    private static final String PARAM_USED_VALUE = "used value";
    private static final String PARAM_IS_STATIC= "is static?";
    private static final String PARAM_IS_NOTATION_BY_OBJECT_NAME = "notation by object name?";

    private static final ExerciseSettings DEFAULT_SETTINGS = new ExerciseSettings();
    static {
        DEFAULT_SETTINGS.setCurrentExerciseName("Static vs non-static");
        DEFAULT_SETTINGS.setCustomTitle("Static vs non-static");
        DEFAULT_SETTINGS.setCustomDescription("Accessing a static or non-static field or method of the class");
        DEFAULT_SETTINGS.setCustomQuestion("Is the code syntactically correct?");
        DEFAULT_SETTINGS.setCustomSolutionExplanation("An instance member can be accessed through an object.\n" +
                "A static member should be accessed through the class itself, but can be accessed through an object too.");
    }


    private String mainClassName;
    private String objectName;
    private String sampleClassName;
    private String varOrMethodName;
    private boolean isMethod;
    private Value usedValue;
    private boolean isStatic;
    private boolean notationByObjectName;


    @Override
    protected ExerciseSettings getDefaultSettings() {
        return DEFAULT_SETTINGS;
    }

    @Override
    protected void initParameters() {
        mainClassName = finalSettings.getStringParamOrDefault(PARAM_MAIN_CLASS_NAME, "Main");
        sampleClassName = finalSettings.getStringParamOrDefault(PARAM_OBJECT_NAME, "ExampleClass");
        objectName = finalSettings.getStringParamOrDefault(PARAM_OBJECT_NAME, "obj");
        varOrMethodName = finalSettings.getStringParamOrDefault(
                PARAM_VAR_OR_METHOD_NAME, "something");
        isMethod = finalSettings.getBoolParamOrDefault(PARAM_IS_METHOD,
                random.nextBoolean());
        usedValue = finalSettings.getParamOrDefault(PARAM_USED_VALUE,
                Value.getInt(random.nextInt(100)));
        isStatic = finalSettings.getBoolParamOrDefault(PARAM_IS_STATIC,
                random.nextBoolean());
        notationByObjectName = finalSettings.getBoolParamOrDefault(PARAM_IS_NOTATION_BY_OBJECT_NAME,
                random.nextBoolean());
    }

    @Override
    protected void makeItBalanced() {
        boolean answer = random.nextBoolean();
        if (!answer) {  //Syntactically incorrect: calling instance with class notation
            isStatic = false;   //instance
            notationByObjectName = false;   //class notation
        } else {
            switch (random.nextInt(3)) {
                case 0: isStatic = false; notationByObjectName = true; break;
                case 1: isStatic = true; notationByObjectName = false; break;
                case 2:
                default: isStatic = true; notationByObjectName = true; break;
            }
        }
    }

    @Override
    protected Program createProgram() {
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
        return program;
    }

    @Override
    protected Solution getSolution() {
        if (!isStatic && !notationByObjectName) {
            return new Solution.BoolSolution(false);
        } else {
            return new Solution.BoolSolution(true);
        }
    }

    @Override
    public List<ExerciseParameter> getPossibleParameters() {
        List<ExerciseParameter> parameters = new ArrayList<>();

        parameters.add(new ExerciseParameter(PARAM_MAIN_CLASS_NAME, DataType.STRING, ExerciseParameter.Role.NAME));
        parameters.add(new ExerciseParameter(PARAM_OBJECT_NAME, DataType.STRING, ExerciseParameter.Role.NAME));
        parameters.add(new ExerciseParameter(PARAM_VAR_OR_METHOD_NAME,
                DataType.STRING, ExerciseParameter.Role.NAME));
        parameters.add(new ExerciseParameter(PARAM_IS_METHOD, DataType.BOOLEAN, ExerciseParameter.Role.STRUCTURAL));
        parameters.add(new ExerciseParameter(PARAM_USED_VALUE, null, ExerciseParameter.Role.STRUCTURAL));
        parameters.add(new ExerciseParameter(PARAM_IS_STATIC, DataType.BOOLEAN, ExerciseParameter.Role.ESSENTIAL));
        parameters.add(new ExerciseParameter(PARAM_IS_NOTATION_BY_OBJECT_NAME, DataType.BOOLEAN, ExerciseParameter.Role.ESSENTIAL));

        return parameters;
    }
}
