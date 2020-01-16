package model.langElements.projectStructure;

import model.langElements.procedural.Method;

public class Program {
    protected Method entryPoint;
    protected PackageElement rootPackage;

    public Method getEntryPoint() {
        return entryPoint;
    }

    public Program() {
        rootPackage = PackageElement.getRootPackage();
    }

    public void setEntryPoint(Method entryPoint) {
        this.entryPoint = entryPoint;
    }

    public PackageElement getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(PackageElement rootPackage) {
        this.rootPackage = rootPackage;
    }
}
