package exerciseTypes.TestExercises;

import model.exerciseElements.Exercise;
import model.exerciseElements.Figure;
import model.exerciseElements.Solution;
import model.langElements.projectStructure.Program;
import model.langElements.projectStructure.PackageElement;

public class BeautifulPackagesTestExercise extends Exercise {

    public BeautifulPackagesTestExercise() {
        super("Testing: CodeFigure with many packages", "These packages are beautiful.",
                "Aren't they?", null,
                new Solution.BoolSolution("Yes, they are", "No, they are not",
                        true));
        Program program = new Program();
        PackageElement defaultPackage = program.getRootPackage();
            defaultPackage.addChildFile("ClassInDefaultPackage");
            PackageElement l1p01 = defaultPackage.addChildPackage("level1Package01");
                l1p01.addChildFile("Class01InPackage01");
                l1p01.addChildFile("Class02InPackage01");
            PackageElement l1p02 = defaultPackage.addChildPackage("level1Package02Empty");
            PackageElement l1p03 = defaultPackage.addChildPackage("level1Package03");
                l1p03.addChildFile("Class03InPackage03");
                PackageElement l2p05 = l1p03.addChildPackage("level2Package05");
                    PackageElement l3p09 = l2p05.addChildPackage("level3Package09");
                        l3p09.addChildFile("File08InPackage09");
                    PackageElement l3p10 = l2p05.addChildPackage("level3Package10");
                        l3p10.addChildFile("File05InPackage10");
                        l3p10.addChildFile("File06InPackage10");
                        l3p10.addChildFile("File07InPackage10");
            PackageElement l1p04 = defaultPackage.addChildPackage("level1Package04");
                PackageElement l2p06 = l1p04.addChildPackage("level2Package06");
                    PackageElement l3p07 = l2p06.addChildPackage("level3Package07");
                        PackageElement l4p08 = l3p07.addChildPackage("level4Package08");
                            l4p08.addChildFile("File04InPackage08");
        this.exerciseFigure = new Figure.CodeFigure(program);
    }
}
