package model.langElements.projectStructure;

import model.CodeText;
import model.CodingStyle;
import model.langElements.objectOriented.ClassElement;
import model.langElements.objectOriented.Modifiers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class File implements CodeText {
    protected String fileNameWithoutExtension;
    protected PackageElement parentPackage;
    protected List<Import> imports;
    protected List<ClassElement> classes = new ArrayList<>();

    protected File(PackageElement parentPackage, String fileNameWithoutExtension, boolean addPublicClassToo) {
        this.parentPackage = parentPackage;
        this.fileNameWithoutExtension = fileNameWithoutExtension;
        if (addPublicClassToo) {
            addClass(fileNameWithoutExtension)
                    .setPublic();
        }
    }

    public ClassElement addClass(String className) {
        ClassElement classElement = new ClassElement(this, className);
        classes.add(classElement);
        return classElement;
    }

    public String getFileNameWithoutExtension() {
        return fileNameWithoutExtension;
    }

    public PackageElement getParentPackage() {
        return parentPackage;
    }

    public List<Import> getImports() {
        return imports;
    }

    public List<ClassElement> getClasses() {
        return classes;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        if (style == null) style = new CodingStyle();
        String code = "";

        /* package information */
        if (parentPackage != null &&
                parentPackage.parentPackage != null) { //IMPORTANT: root/defaultPackage doesn't have a parent --> no package line
            String fullPackageName = parentPackage.name;
            parentPackage = parentPackage.parentPackage;
            if (parentPackage != null) {
                while (parentPackage.parentPackage != null) {   //IMPORTANT: root/defaultPackage doesn't have a parent and real name
                    fullPackageName = parentPackage.name + "." + fullPackageName;
                    parentPackage = parentPackage.parentPackage;
                }
            }
            code += "package " + fullPackageName + ";\n\n";
        }

        /* import information */
        //TODO

        /* classes */
        for (ClassElement classElement : classes) {
            code += classElement.getCodeText(style) + "\n";
        }

        return code;
    }

    public String getFullPackagePath() {
        List<String> packages = new ArrayList<>();
        PackageElement parent = parentPackage;
        if (parent == null) return "";    //Unusual case: file doesn't have a parent package, not even the default package
        while(parent.parentPackage != null) {   //IMPORTANT: root/defaultPackage doesn't have a parent and a real name
            packages.add(parent.name);
            parent = parent.parentPackage;
        }
        Collections.reverse(packages);
        return packages.stream().collect(Collectors.joining("/"));
    }

    //TODO: delete later
    public List<String> getPackagePath() {
        List<String> packages = new ArrayList<>();
        PackageElement parent = parentPackage;
        if (parent == null) return packages;    //Unusual case: file doesn't have a parent package, not even the default package
        while(parent.parentPackage != null) {   //IMPORTANT: root/defaultPackage doesn't have a parent and a real name
            packages.add(parentPackage.name);
        }
        return packages;
    }
}
