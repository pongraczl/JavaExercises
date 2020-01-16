package model.exerciseElements;

import model.langElements.projectStructure.File;
import model.langElements.projectStructure.PackageElement;
import model.langElements.projectStructure.Program;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {

    public static class SimpleTextFigure extends Figure {
        protected String text;
        public SimpleTextFigure(String text) {
            this.text = text;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
    }

    public static class CodeFigure extends Figure {
        List<FileNode> fileNodes = new ArrayList<>();
        PackageNode rootPackageNode;

        public CodeFigure(Program javaProgram) {
            rootPackageNode = new PackageNode(null, javaProgram.getRootPackage());
        }

        public CodeFigure(File javaFile) {
            //PackageNode parentPackage = new PackageNode();

        }

        public class FileNode {
            public PackageNode parentPackage;
            public String fullPackagePath;
            public String fileName;
            public String codeText;

            public FileNode(PackageNode parentPackageNode, File file) {
                this.parentPackage = parentPackageNode;
                this.fullPackagePath = file.getFullPackagePath();
                this.fileName = file.getFileNameWithoutExtension() + ".java";
                this.codeText = file.getCodeText(null); //TODO: apply coding styles
                CodeFigure.this.fileNodes.add(this);
            }
        }

        public class PackageNode {
            public PackageNode parentPackage;
            public String packageName;
            public List<PackageNode> childPackages = new ArrayList<>();
            public List<FileNode> childFiles = new ArrayList<>();

            PackageNode(PackageNode parentPackageNode, PackageElement actPackage) {
                this.parentPackage = parentPackageNode;
                this.packageName = actPackage.getName();
                for (PackageElement subPackage : actPackage.getChildPackages()) {
                    childPackages.add(new PackageNode(this, subPackage));
                }
                for (File file : actPackage.getFiles()) {
                    childFiles.add(new FileNode(this, file));
                }
            }
        }

        public List<FileNode> getFileNodes() {
            return fileNodes;
        }

        public PackageNode getRootPackageNode() {
            return rootPackageNode;
        }
    }

    public static class PictureFigure extends Figure {

    }
}
