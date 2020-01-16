package model.langElements.objectOriented;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.DataType;
import model.langElements.procedural.FormalParameter;
import model.langElements.procedural.Method;

import java.util.List;
import java.util.stream.Collectors;

public class Constructor extends Method implements CodeText {
    protected ConstructorCall thisCall;
    protected ConstructorCall superCall;
    protected boolean isExplicit = true;

    public Constructor(ClassElement ownerClass, List<FormalParameter> parameterList) {
        super(ownerClass, ownerClass.name, DataType.makeObjectType(ownerClass), parameterList);
    }

    public Constructor(ClassElement ownerClass, FormalParameter... parameters) {
        super(ownerClass, ownerClass.name, DataType.makeObjectType(ownerClass), parameters);
    }

    public Constructor(ClassElement ownerClass) {
        super(ownerClass, ownerClass.name, DataType.makeObjectType(ownerClass));
    }

    public ConstructorCall getThisCall() {
        return thisCall;
    }

    public void setThisCall(ConstructorCall thisCall) {
        this.thisCall = thisCall;
    }

    public ConstructorCall getSuperCall() {
        return superCall;
    }

    public void setSuperCall(ConstructorCall superCall) {
        this.superCall = superCall;
    }

    public boolean isExplicit() {
        return isExplicit;
    }

    public void setExplicit(boolean explicit) {
        isExplicit = explicit;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        String code = style.currentIndentation + modifiers.getCodeText(style)
                + name + "(";
        code += parameterList.stream()
                .map(param -> param.getCodeText(style))
                .collect(Collectors.joining(", "));
        code += ") " + block.getCodeText(style);
        return code;
    }

}
