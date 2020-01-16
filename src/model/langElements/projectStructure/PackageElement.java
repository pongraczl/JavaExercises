package model.langElements.projectStructure;

import java.util.ArrayList;
import java.util.List;

public class PackageElement {
    protected String name;
    protected PackageElement parentPackage;
    protected List<PackageElement> childPackages = new ArrayList<>();
    protected List<File> files = new ArrayList<>();

    protected PackageElement() {
        name = "(default)";
        parentPackage = null;
    }

    protected PackageElement(PackageElement parent, String name) {
        parentPackage = parent;
        this.name = name;
    }

    public static PackageElement getRootPackage() {
        return new PackageElement();
    }

    public PackageElement addChildPackage(String childPackageName) {
        PackageElement childPackage = new PackageElement(this, childPackageName);
        this.childPackages.add(childPackage);
        return childPackage;
    }

    public File addChildFile(String childFileName) {
        return addChildFile(childFileName, false);
    }

    public File addChildFile(String childFileName, boolean addPublicClassToo) {
        File childFile = new File(this, childFileName, addPublicClassToo);
        this.files.add(childFile);
        return childFile;
    }

    public String getName() {
        return name;
    }

    public PackageElement getParentPackage() {
        return parentPackage;
    }

    public List<PackageElement> getChildPackages() {
        return childPackages;
    }

    public List<File> getFiles() {
        return files;
    }
}
