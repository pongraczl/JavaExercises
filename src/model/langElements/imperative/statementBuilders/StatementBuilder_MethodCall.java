package model.langElements.imperative.statementBuilders;

import model.langElements.general.Literal;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.imperative.Expression;
import model.langElements.imperative.Statement;
import model.langElements.objectOriented.MemberAccess;
import model.langElements.procedural.Method;
import model.langElements.procedural.MethodCall;

import java.util.ArrayList;
import java.util.List;

public class StatementBuilder_MethodCall {

    public static class Builder
            implements
                ChoosingMethod,
                Build
    {
        private List<Expression> accessors = new ArrayList<>();
        private Method method;
        private List<Expression> actualParameters = new ArrayList<>();

        Builder() {}

        @Override
        public Build method(Method method) {
            this.method = method;
            return this;
        }

        @Override
        public Build addParameter(Value value) {
            actualParameters.add(Expression.make(value));
            return this;
        }

        @Override
        public Build addParameter(Literal literal) {
            actualParameters.add(Expression.make(literal.getValue()));
            return this;
        }

        @Override
        public Build addParameter(Variable variable) {
            actualParameters.add(Expression.make(variable));
            return this;
        }

        @Override
        public Build addParameter(Expression paramExpression) {
            actualParameters.add(paramExpression);
            return this;
        }

        @Override
        public Build memberOf(Expression primaryAccessor) {
            accessors.add(0, primaryAccessor);
            return this;
        }

        @Override
        public Statement.ExpressionStatement build() {
            MethodCall call = new MethodCall(method, actualParameters);
            Expression finalExpression = new Expression.MethodCallExpression(call);
            for (Expression e : accessors) {
                finalExpression = MemberAccess.makeAccess(e, finalExpression);
            }
            return new Statement.ExpressionStatement(finalExpression);
        }
    }

    public interface ChoosingMethod {
        StatementBuilder_MethodCall.Build method(Method method);
    }

    public interface Build {
        StatementBuilder_MethodCall.Build addParameter(Value value);
        StatementBuilder_MethodCall.Build addParameter(Literal literal);
        StatementBuilder_MethodCall.Build addParameter(Variable variable);
        StatementBuilder_MethodCall.Build addParameter(Expression paramExpression);
        StatementBuilder_MethodCall.Build memberOf(Expression primaryAccessor);
        Statement.ExpressionStatement build();
    }
}
