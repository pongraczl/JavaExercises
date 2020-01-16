package model.exerciseElements;

import model.langElements.projectStructure.Program;

import java.util.List;
import java.util.Random;

public abstract class ExerciseFactory {
    protected Random random = new Random();
    protected ExerciseSettings finalSettings;
    protected Exercise exercise;
    protected Program program;

    public Exercise getExercise(ExerciseSettings exerciseSettings) {
        finalSettings = exerciseSettings.createCascaded( getDefaultSettings() );
        exercise = new Exercise(finalSettings);
        initParameters();
        if (finalSettings.isBalanced()) makeItBalanced();    // can overwrite some parameters
        program = createProgram();
        exercise.setSolution( getSolution() );
        exercise.setExerciseFigure( new Figure.CodeFigure(program) );
        return exercise;
    }

    public String getExerciseNameToView() {
        return getClass().getSimpleName();
    }

    public abstract List<ExerciseParameter> getPossibleParameters();

    protected abstract ExerciseSettings getDefaultSettings();
    protected abstract void initParameters();
    protected abstract void makeItBalanced();
    protected abstract Program createProgram();
    protected abstract Solution getSolution();
}
