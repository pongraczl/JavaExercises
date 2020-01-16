package model.langElements.objectOriented;

import model.CodeText;
import model.CodingStyle;

public class Modifiers implements CodeText {
    protected Visibility visibility = Visibility.PACKAGE;
    protected boolean isStatic = false;
    protected boolean isFinal = false;
    protected boolean isAbstract = false;
    protected boolean isDefault = false;


    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public enum Visibility {
        PUBLIC("public"),
        PROTECTED("protected"),
        PACKAGE(""),
        PRIVATE("private");

        private String keyword;

        private Visibility(String keyword) {
            this.keyword = keyword;
        }

        @Override
        public String toString() {
            return keyword;
        }
    }

    @Override
    public String getCodeText(CodingStyle style) {
        String visibilityKeyword = visibility.toString();
        return
                (!visibilityKeyword.isEmpty() ? visibilityKeyword + " " : "")
                + (isStatic   ? "static "   : "")
                + (isFinal    ? "final "    : "")
                + (isDefault  ? "default "  : "")
                + (isAbstract ? "abstract " : "")
        ;
    }
}

