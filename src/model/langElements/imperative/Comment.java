package model.langElements.imperative;

import model.CodeText;
import model.CodingStyle;

public class Comment extends Statement implements CodeText {
    protected String comment;

    protected Comment(String text){
        this.comment = text;
    }

    public static Comment endLineComment(String text) {
        return new Comment("//" + text);
    }

    public static Comment newLineComment(String text) {
        return new Comment("\n//" + text);
    }

    public static Comment blankLine() {
        return new Comment("\n");
    }


    @Override
    public String getCodeText(CodingStyle style) {
        return comment;
    }
}
