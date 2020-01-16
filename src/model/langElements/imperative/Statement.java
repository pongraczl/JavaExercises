package model.langElements.imperative;

import model.CodeText;
import model.CodingStyle;
import model.langElements.imperative.statementBuilders.StatementBuilder;
import model.langElements.general.DataType;
import model.langElements.general.Literal;
import model.langElements.general.Value;
import model.langElements.general.Variable;

public abstract class Statement implements CodeText {
    protected CodeText elementCodeText;

    public static StatementBuilder builder() { return new StatementBuilder(); }

    //Declaration
    public static class DeclarationStatement extends Statement {
        protected Declaration declaration;
        public DeclarationStatement(Declaration declaration) {
            this.declaration = declaration;
            this.elementCodeText = declaration;
        }

        public Declaration getDeclaration() {
            return declaration;
        }
    }
    public static DeclarationStatement makeDeclaration(DataType dataType, String variableName) {
        return new DeclarationStatement(
                Declaration.getNew(new Variable(variableName, dataType))
        );
    }
    public static DeclarationStatement makeDeclaration(String variableName, Value initValue) {
        return new DeclarationStatement(
                Declaration.getNew(new Variable(variableName, initValue.getDataType(), initValue),
                        new Expression.LiteralExpression(
                                Literal.createByValue(initValue)
                        ))
        );
    }

    //expression as a statement
    public static class ExpressionStatement extends Statement {
        protected Expression expression;

        public ExpressionStatement(Expression expression) {
            this.expression = expression;
            this.elementCodeText = expression;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }
    }
    public static ExpressionStatement makeExpressionAsStmt(Expression expression) {
        ExpressionStatement expressionStatement = new ExpressionStatement(expression);
        return expressionStatement;
    }

    //return
    public static class ReturnStatement extends Statement {
        Expression returnExpression;

        public ReturnStatement(Expression returnExpression) {
            this.returnExpression = returnExpression;
            this.elementCodeText =
                returnExpression == null
                ? (codingStyle) -> "return"
                : (codingStyle) -> "return " + returnExpression.getCodeText(codingStyle);
        }
    }
    public static ReturnStatement makeReturn(Expression returnExpression) {
        return new ReturnStatement(returnExpression);
    }

    public static class CustomStatement extends Statement {
        String statementText;

        public CustomStatement(String statementText) {
            this.statementText = statementText;
            this.elementCodeText = (codingStyle) -> statementText==null ? "" : statementText;
        }
    }

    @Override
    public String getCodeText(CodingStyle style) {
        return style.currentIndentation + elementCodeText.getCodeText(style) + ";";
    }
}
