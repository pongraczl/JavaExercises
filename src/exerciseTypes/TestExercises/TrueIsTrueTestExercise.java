package exerciseTypes.TestExercises;

import model.exerciseElements.Exercise;
import model.exerciseElements.Figure;
import model.exerciseElements.Solution;

public class TrueIsTrueTestExercise extends Exercise {
    public static int counter = 1;

    public TrueIsTrueTestExercise() {
        super("True is true Test Exercise", "No. " + counter++,
                "Is TRUE really true?", new Figure.SimpleTextFigure("Hint:\n \nThat's true!"),
                new Solution.BoolSolution(true));
    }
}
