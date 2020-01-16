package model.langElements.imperative;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.Literal;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.objectOriented.*;
import model.langElements.procedural.Method;
import model.langElements.procedural.MethodCall;

public abstract class Expression implements CodeText {

    public abstract Value evaluate();

    public static LiteralExpression make(Value literalValue) {
        return new Expression.LiteralExpression(
                Literal.createByValue(literalValue)
        );
    }
    public static VariableExpression make(Variable variable) {
        return new VariableExpression(variable);
    }
    public static MethodCallExpression make(Method calledMethod, Expression... actualParameters) {
        return new MethodCallExpression(
                new MethodCall(calledMethod, actualParameters)
        );
    }
    public static ConstructorCallExpression make(Constructor calledConstructor, Expression... actualParameters) {
        return new ConstructorCallExpression(
                new ConstructorCall(calledConstructor, actualParameters)
        );
    }
    public static ClassIdentifierExpression make(ClassElement classElement) {
        return new ClassIdentifierExpression(classElement);
    }
    public static FieldExpression make(Field field) {
        return new FieldExpression(field);
    }

    //Literal
    public static class LiteralExpression extends Expression {
        protected Literal literal;

        @Override
        public Value evaluate() {
            return literal.getValue();
        }

        public LiteralExpression(Literal literal) {
            this.literal = literal;
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return literal.getCodeText(style);
        }
    }

    //Variable identifier
    public static class VariableExpression extends Expression {
        protected Variable variable;

        public VariableExpression(Variable variable) {
            this.variable = variable;
        }

        @Override
        public Value evaluate() {
            return variable.getValue();
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return variable.getName();
        }

        public Variable getVariable() {
            return variable;
        }

        public void setVariable(Variable variable) {
            this.variable = variable;
        }
    }

    //Field identifier
    public static class FieldExpression extends Expression {
        protected Field field;

        public FieldExpression(Field field) {
            this.field = field;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        @Override
        public Value evaluate() {
            return null;
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return field.getFieldName();
        }

    }


    //Class identifier
    public static class ClassIdentifierExpression extends Expression {
        protected ClassElement classElement;

        public ClassIdentifierExpression(ClassElement classElement) {
            this.classElement = classElement;
        }

        @Override
        public Value evaluate() {
            return null;
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return classElement.getName();
        }

        public ClassElement getClassElement() {
            return classElement;
        }

        public void setClassElement(ClassElement classElement) {
            this.classElement = classElement;
        }
    }

    //Method call
    public static class MethodCallExpression extends Expression {
        protected MethodCall methodCall;
        public MethodCallExpression(MethodCall methodCall) {
            this.methodCall = methodCall;
        }
        public MethodCall getMethodCall() {
            return methodCall;
        }

        @Override
        public Value evaluate() {
            return null;
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return methodCall.getCodeText(style);
        }
    }

    //Constructor call
    public static class ConstructorCallExpression extends Expression {
        protected ConstructorCall constructorCall;
        public ConstructorCallExpression(ConstructorCall constructorCall) {
            this.constructorCall = constructorCall;
        }
        public ConstructorCall getConstructorCall() {
            return constructorCall;
        }

        @Override
        public Value evaluate() {
            return null;
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return constructorCall.getCodeText(style);
        }
    }


    public static class ParenthesisExpression extends Expression {
        @Override
        public Value evaluate() {
            return null;
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return null;
        }
    }

    public static class ExplicitTypeCast extends Expression {
        @Override
        public Value evaluate() {
            return null;
        }

        @Override
        public String getCodeText(CodingStyle style) {
            return null;
        }
    }
}
