package model.langElements.objectOriented;

import model.langElements.general.Variable;

import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectElement {
    protected ClassElement ownClass;
    protected Map<Field, Variable> instanceVariables = new LinkedHashMap<>();

    public ObjectElement(ClassElement ownClass) {
        this.ownClass = ownClass;
    }

    public ClassElement getOwnClass() {
        return ownClass;
    }

    public Map<Field, Variable> getInstanceVariables() {
        return instanceVariables;
    }

    public Variable getInstanceVariable(String fieldName) {
        for (Field fieldDeclaration : ownClass.fieldDeclarations) {
            if (fieldDeclaration.fieldName.equals(fieldName)) return instanceVariables.get(fieldDeclaration);
        }
        return null;
    }

}
