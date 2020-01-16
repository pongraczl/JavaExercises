package model.langElements.imperative;

import model.CodeText;
import model.CodingStyle;

import java.util.ArrayList;
import java.util.List;

public class Block implements CodeText {
    protected List<Statement> statements = new ArrayList<>();

    public Block add(Statement statement) {
        statements.add(statement);
        return this;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        CodingStyle contentsStyle = new CodingStyle(style, true);
        String code = "{\n";
        for (Statement statement : statements) {
            code += statement.getCodeText(contentsStyle) + "\n";
        }
        code += style.currentIndentation + "}";
        return code;
    }
}
