package model.langElements.objectOriented;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.DataType;
import model.langElements.general.Variable;
import model.langElements.imperative.Expression;

public class Field implements CodeText {
    protected ClassElement ownerClass;
    protected Modifiers modifiers = new Modifiers();
    protected DataType dataType;
    protected String fieldName;
    protected Expression initExpression;

    public Field(ClassElement ownerClass, DataType dataType, String fieldName) {
        this.ownerClass = ownerClass;
        this.dataType = dataType;
        this.fieldName = fieldName;
    }

    public Field(ClassElement ownerClass, DataType dataType, String fieldName, Expression initExpression) {
        this.ownerClass = ownerClass;
        this.dataType = dataType;
        this.fieldName = fieldName;
        this.initExpression = initExpression;
    }

    public Field setPrivate() { modifiers.setVisibility(Modifiers.Visibility.PRIVATE); return this; }
    public Field setPackagePrivate() { modifiers.setVisibility(Modifiers.Visibility.PACKAGE); return this; }
    public Field setProtected() { modifiers.setVisibility(Modifiers.Visibility.PROTECTED); return this; }
    public Field setPublic() { modifiers.setVisibility(Modifiers.Visibility.PUBLIC); return this; }

    public Field setStatic() {
        modifiers.setStatic(true);
        if (ownerClass != null) {
            if (initExpression != null) {
                ownerClass.staticFields.put(this, new Variable(fieldName, dataType, initExpression.evaluate()));
            } else {
                ownerClass.staticFields.put(this, new Variable(fieldName, dataType));
            }
        }
        return this;
    }
    public Field setNonStatic() {
        modifiers.setStatic(false);
        if (ownerClass != null) ownerClass.staticFields.remove(this);
        return this;
    }
    public Field setAbstract() { modifiers.setAbstract(true); return this; }
    public Field setDefault() { modifiers.setDefault(true); return this; }
    public Field setFinal() { modifiers.setFinal(true); return this; }



    public DataType getDataType() {
        return dataType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Expression getInitExpression() {
        return initExpression;
    }

    public void setInitExpression(Expression initExpression) {
        this.initExpression = initExpression;
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        String code = style.currentIndentation + modifiers.getCodeText(style)
                + dataType.getCodeText(style) + " " + fieldName;
        //TODO: initExpression
        return code + ";";

    }
}
