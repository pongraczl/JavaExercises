package model.langElements.imperative.statementBuilders;

import model.langElements.imperative.Expression;
import model.langElements.imperative.Statement;

public class StatementBuilder_Return {
    private Expression returnExpression;

    public StatementBuilder_Return returnValue(Expression returnExpression) {
        this.returnExpression = returnExpression;
        return this;
    }

    public Statement.ReturnStatement build() {
        return new Statement.ReturnStatement(returnExpression);
    }
}
