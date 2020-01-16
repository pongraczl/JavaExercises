package model.langElements.imperative.statementBuilders;

public class StatementBuilder {

    public StatementBuilder_Declare.Stmt_Declare_DataType declaration() {
        return new StatementBuilder_Declare.StatementBuilder_Declare_Builder();
    }

    public StatementBuilder_Assignment.StatementBuilder_Assignment_LeftExpressionNeeded assignment() {
        return new StatementBuilder_Assignment.Builder();
    }

    public StatementBuilder_MethodCall.ChoosingMethod methodCall() {
        return new StatementBuilder_MethodCall.Builder();
    }

    public StatementBuilder_Return returnStmt() {
        return new StatementBuilder_Return();
    }

    public StatementBuilder_Custom custom() {
        return new StatementBuilder_Custom();
    }

    //public StatementBuilder_Expression
}
