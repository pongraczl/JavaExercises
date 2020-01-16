package model.langElements.objectOriented;

import model.CodingStyle;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.imperative.Expression;
import model.langElements.procedural.MethodCall;

public class MemberAccess extends Expression {
    protected Expression leftExpression;
    protected Expression rightExpression;

    public static FieldAccess makeFieldAccess(Expression leftExpression, Field fieldToAccess) {
        return new FieldAccess(leftExpression, fieldToAccess);
    }
    public static FieldAccess makeFieldAccess(ClassElement classElement, Field fieldToAccess) {
        return new FieldAccess(Expression.make(classElement), fieldToAccess);
    }
    public static FieldAccess makeFieldAccess(Variable variable, Field fieldToAccess) {
        return new FieldAccess(Expression.make(variable), fieldToAccess);
    }

    public static MethodAccess makeMethodAccess(Expression leftExpression, MethodCall methodCall) {
        return new MethodAccess(leftExpression, methodCall);
    }
    public static MethodAccess makeMethodAccess(ClassElement classElement, MethodCall methodCall) {
        return new MethodAccess(Expression.make(classElement), methodCall);
    }
    public static MethodAccess makeMethodAccess(Variable variable, MethodCall methodCall) {
        return new MethodAccess(Expression.make(variable), methodCall);
    }
    public static MemberAccess makeAccess(Expression leftExpression, Expression rightExpression) {
        MemberAccess ma = new MemberAccess();
        ma.leftExpression = leftExpression;
        ma.rightExpression = rightExpression;
        return ma;
    }

    protected MemberAccess() {}

    public static class FieldAccess extends MemberAccess {
        protected Field fieldToAccess;

        public FieldAccess(Expression leftExpression, Field fieldToAccess) {
            this.leftExpression = leftExpression;
            this.rightExpression = Expression.make(fieldToAccess);
            this.fieldToAccess = fieldToAccess;
        }

    }

    public static class MethodAccess extends MemberAccess {
        protected MethodCall methodToAccess;

        public MethodAccess(Expression leftExpression, MethodCall methodToAccess) {
            this.leftExpression = leftExpression;
            this.rightExpression = new MethodCallExpression(methodToAccess);
            this.methodToAccess = methodToAccess;
        }
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public void setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }


    @Override
    public Value evaluate() {
        return null;    //TODO: static/instance
    }

    @Override
    public String getCodeText(CodingStyle style) {
        return leftExpression.getCodeText(style) + "." + rightExpression.getCodeText(style);
    }
}
