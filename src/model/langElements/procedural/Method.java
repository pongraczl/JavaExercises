package model.langElements.procedural;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.DataType;
import model.langElements.imperative.Block;
import model.langElements.imperative.Statement;
import model.langElements.objectOriented.ClassElement;
import model.langElements.objectOriented.Modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Method implements CodeText {
    protected String name;
    protected DataType returnType;
    protected List<FormalParameter> parameterList;
    protected Block block = new Block();
    protected ClassElement ownerClass;
    protected Modifiers modifiers = new Modifiers();

    public Method(ClassElement ownerClass, String name, DataType returnType, List<FormalParameter> parameterList) {
        this.name = name;
        this.returnType = returnType;
        this.parameterList = parameterList == null ? new ArrayList<>() : parameterList;
        this.ownerClass = ownerClass;
    }

    public Method(ClassElement ownerClass, String name, DataType returnType, FormalParameter... parameters) {
        this(ownerClass, name, returnType, FormalParameter.create(parameters));
    }

    public Method setPrivate() { modifiers.setVisibility(Modifiers.Visibility.PRIVATE); return this; }
    public Method setPackagePrivate() { modifiers.setVisibility(Modifiers.Visibility.PACKAGE); return this; }
    public Method setProtected() { modifiers.setVisibility(Modifiers.Visibility.PROTECTED); return this; }
    public Method setPublic() { modifiers.setVisibility(Modifiers.Visibility.PUBLIC); return this; }

    public Method setStatic() { modifiers.setStatic(true); return this; }
    public Method setAbstract() { modifiers.setAbstract(true); return this; }
    public Method setDefault() { modifiers.setDefault(true); return this; }
    public Method setFinal() { modifiers.setFinal(true); return this; }

    public Method add(Statement statement) {
        block.add(statement);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getReturnType() {
        return returnType;
    }

    public void setReturnType(DataType returnType) {
        this.returnType = returnType;
    }

    public List<FormalParameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<FormalParameter> parameterList) {
        this.parameterList = parameterList;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        String code = style.currentIndentation + modifiers.getCodeText(style)
                + returnType.getCodeText(style) + " " + name + "(";
        code += parameterList.stream()
                .map(param -> param.getCodeText(style))
                .collect(Collectors.joining(", "));
        code += ") " + block.getCodeText(style);
        return code;
    }
}
