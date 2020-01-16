package model.langElements.objectOriented;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.DataType;
import model.langElements.general.Variable;
import model.langElements.procedural.Method;
import model.langElements.procedural.FormalParameter;
import model.langElements.projectStructure.File;

import java.util.*;
import java.util.stream.Collectors;

public class ClassElement implements CodeText {
    protected ClassType classType = ClassType.CLASS;
    protected File parentFile;
    protected String name;
    protected Modifiers modifiers = new Modifiers();
    protected List<Method> methods = new ArrayList<>();
    protected List<Constructor> constructors = new ArrayList<>();
    protected List<Field> fieldDeclarations = new ArrayList<>();
    protected Map<Field, Variable> staticFields = new LinkedHashMap<>();
    protected List<InitializerBlock> initializerBlocks = new ArrayList<>();

    protected List<CodeText> codeTextElementsInOrder = new ArrayList<>();

    public ClassElement(File containerFile, String className) {
        this.parentFile = containerFile;
        this.name = className;
    }

    public Method addMethod(String methodName, DataType returnType, List<FormalParameter> parameters) {
        Method method = new Method(this, methodName, returnType, parameters);
        methods.add(method);
        codeTextElementsInOrder.add(method);
        return method;
    }

    public Constructor addConstructor(List<FormalParameter> parameters) {
        Constructor constructor = new Constructor(this, parameters);
        constructors.add(constructor);
        codeTextElementsInOrder.add(constructor);
        return constructor;
    }

    public Constructor addDefaultConstructor() {
        Constructor constructor = new Constructor(this);
        constructors.add(constructor);
        constructor.setExplicit(false);
        //should not be added to codeTextElementsInOrder
        return constructor;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public Field addField(String fieldName, DataType dataType) {
        Field field = new Field(this, dataType, fieldName);
        fieldDeclarations.add(field);
        codeTextElementsInOrder.add(field);
        return field;
    }

    public ClassElement setPrivate() { modifiers.setVisibility(Modifiers.Visibility.PRIVATE); return this; }
    public ClassElement setPackagePrivate() { modifiers.setVisibility(Modifiers.Visibility.PACKAGE); return this; }
    public ClassElement setProtected() { modifiers.setVisibility(Modifiers.Visibility.PROTECTED); return this; }
    public ClassElement setPublic() { modifiers.setVisibility(Modifiers.Visibility.PUBLIC); return this; }

    public ClassElement setStatic() { modifiers.setStatic(true); return this; }
    public ClassElement setAbstract() { modifiers.setAbstract(true); return this; }
    public ClassElement setDefault() { modifiers.setDefault(true); return this; }
    public ClassElement setFinal() { modifiers.setFinal(true); return this; }


    public List<Method> getMethods() {
        return methods;
    }

    public List<Method> getMethods(String methodName) {
        return methods.stream()
                .filter(m -> m.getName().equals(methodName))
                .collect(Collectors.toList());
    }

    public Method getMethod(String methodName) {
        return methods.stream()
                .filter(m -> m.getName().equals(methodName))
                .findFirst()
                .orElse(null);
    }

    public ClassType getClassType() {
        return classType;
    }

    public File getParentFile() {
        return parentFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public List<Field> getFieldDeclarations() {
        return fieldDeclarations;
    }

    public Field getField(String fieldName) {
        return fieldDeclarations.stream()
                .filter(f -> f.getFieldName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }

    public Map<Field, Variable> getStaticFields() {
        return staticFields;
    }

    public Variable getStaticField(Field fieldDeclaration) {
        return staticFields.get(fieldDeclaration);
    }

    public Variable getStaticField(String fieldName) {
        for (Field fieldDeclaration : fieldDeclarations) {
            if (fieldDeclaration.fieldName.equals(fieldName)) return staticFields.get(fieldDeclaration);
        }
        return null;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        String ind = style.currentIndentation;
        String code = ind + modifiers.getCodeText(style) + "class " + name + " {\n";
        for (CodeText element : codeTextElementsInOrder) {
            code += element.getCodeText(new CodingStyle(style, true)) + "\n";
        }

        code += ind + "}\n";

        return code;
    }

    public enum ClassType {
        CLASS, INTERFACE, ENUM
    }
}

