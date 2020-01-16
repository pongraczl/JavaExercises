package model.langElements.projectStructure;

import model.langElements.general.DataType;
import model.langElements.objectOriented.ClassElement;
import model.langElements.procedural.FormalParameter;

public class Program_Default1 extends Program{
    protected ClassElement mainClass;
    protected ClassElement sampleClass;

    public Program_Default1() {
        File file = getRootPackage().addChildFile("Main", true);
        mainClass = file.getClasses().get(0);
        entryPoint = mainClass.addMethod("main", DataType.VOID,
                FormalParameter.create(DataType.makeArrayType(DataType.STRING), "args"))
                .setPublic()
                .setStatic();
        sampleClass = file.addClass("SampleClass");

    }

    public ClassElement getMainClass() {
        return mainClass;
    }

    public void setMainClass(ClassElement mainClass) {
        this.mainClass = mainClass;
    }

    public ClassElement getSampleClass() {
        return sampleClass;
    }

    public void setSampleClass(ClassElement sampleClass) {
        this.sampleClass = sampleClass;
    }
}
