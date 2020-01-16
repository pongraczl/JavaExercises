package model.langElements.imperative.statementBuilders;

import model.langElements.general.Literal;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.imperative.Assignment;
import model.langElements.imperative.Expression;
import model.langElements.imperative.Operator;
import model.langElements.imperative.Statement;

import static model.langElements.imperative.Assignment.DEFAULT_ASSIGNMENT_OPERATOR;


public class StatementBuilder_Assignment {

    public static class Builder
            implements
                StatementBuilder_Assignment_LeftExpressionNeeded,
                StatementBuilder_Assignment_RightExpressionNeeded,
                StatementBuilder_Assignment_Build
    {
        Expression leftExpression, rightExpression;
        Operator.AssignmentOperator operator = DEFAULT_ASSIGNMENT_OPERATOR;

        Builder() {}

        @Override
        public StatementBuilder_Assignment_RightExpressionNeeded leftExp(Variable variable) {
            leftExpression = Expression.make(variable);
            return this;
        }

        @Override
        public StatementBuilder_Assignment_RightExpressionNeeded leftExp(Expression expression) {
            leftExpression = expression;
            return this;
        }

        @Override
        public StatementBuilder_Assignment_RightExpressionNeeded operator(Operator.AssignmentOperator operator) {
            this.operator = operator;
            return this;
        }

        @Override
        public StatementBuilder_Assignment_Build rightExp(Value value) {
            this.rightExpression = Expression.make(value);
            return this;
        }

        @Override
        public StatementBuilder_Assignment_Build rightExp(Literal literal) {
            this.rightExpression = Expression.make(literal.getValue());
            return this;
        }

        @Override
        public StatementBuilder_Assignment_Build rightExp(Variable variable) {
            this.rightExpression = Expression.make(variable);
            return this;
        }

        @Override
        public StatementBuilder_Assignment_Build rightExp(Expression expression) {
            this.rightExpression = expression;
            return this;
        }

        @Override
        public Statement.ExpressionStatement build() {
            return new Statement.ExpressionStatement(
                    new Assignment(leftExpression, operator, rightExpression)
            );
        }
    }

    public interface StatementBuilder_Assignment_LeftExpressionNeeded {
        StatementBuilder_Assignment_RightExpressionNeeded leftExp(Variable variable);
        StatementBuilder_Assignment_RightExpressionNeeded leftExp(Expression expression);
    }

    public interface StatementBuilder_Assignment_RightExpressionNeeded {
        StatementBuilder_Assignment_RightExpressionNeeded operator(Operator.AssignmentOperator operator);
        StatementBuilder_Assignment_Build rightExp(Value value);
        StatementBuilder_Assignment_Build rightExp(Literal literal);
        StatementBuilder_Assignment_Build rightExp(Variable variable);
        StatementBuilder_Assignment_Build rightExp(Expression expression);
    }

    public interface StatementBuilder_Assignment_Build {
        Statement.ExpressionStatement build();
    }

}
