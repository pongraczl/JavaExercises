package model.langElements.imperative.statementBuilders;

import model.langElements.imperative.Statement;

public class StatementBuilder_Custom {
    String text;

    public StatementBuilder_Custom setText(String text) {
        this.text = text;
        return this;
    }

    public Statement.CustomStatement build() {
        return new Statement.CustomStatement(text);
    }
}
