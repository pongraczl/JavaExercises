package model.exerciseElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LessonState {
    private LessonDescription lessonDescription;
    private List<Exercise> actualExercises = new ArrayList<>();
    private int currentExerciseIndex = -1;
    private int score = 0;

    private LessonState() {}

    public static LessonState createNewLesson(LessonDescription lessonDescription) {
        if (lessonDescription == null) return null;
        LessonState ld = new LessonState();
        ld.lessonDescription = lessonDescription;
        //TODO: before starting the Lesson, check for ExerciseException by creating all Exercises from the lessonDescription

        switch (lessonDescription.getOrder()) {
            case LIST_FIX:
                for (ExerciseSettings settings : lessonDescription.getPossibleExercises()) {
                    ld.actualExercises.add(ld.generateExercise(settings));
                }
                break;
            case RANDOM_ONCE:
                Random random = new Random();
                List<ExerciseSettings> settings = new ArrayList<>();
                for (ExerciseSettings eSet : lessonDescription.getPossibleExercises()) {
                    settings.add(eSet);
                }
                while (settings.size() > 0) {
                    ExerciseSettings eSet = settings.remove(random.nextInt(settings.size()));
                    ld.actualExercises.add(
                            ld.generateExercise(eSet)
                    );
                }
                break;
            case RANDOM_ANY:

                break;
        }
        return ld;
    }

    public Exercise getCurrentExercise() {
        return actualExercises.get(currentExerciseIndex);
    }

    public Exercise getNextExercise() {
        currentExerciseIndex++;
        if (lessonDescription.getOrder() == LessonDescription.OrderType.RANDOM_ANY) {
            if (lessonDescription.getMaxExercises() != 0    //infinit
                && currentExerciseIndex >= lessonDescription.getMaxExercises()) return null;
            Random random = new Random();
            List<ExerciseSettings> eSettings = lessonDescription.getPossibleExercises();
            actualExercises.add(
                    generateExercise(eSettings.get(
                            random.nextInt(eSettings.size())
                    ))
            );
        }
        if (currentExerciseIndex < actualExercises.size()) {
            return actualExercises.get(currentExerciseIndex);
        } else {
            return null;
        }
    }

    public int getScore() {
        return score;
    }

    private Exercise generateExercise(ExerciseSettings exerciseSettings) {
        try{
            return ExerciseLoader.getNewExercise(exerciseSettings.getExerciseFactoryCode(), exerciseSettings);
        } catch (ExerciseLoader.ExerciseException err) {
            System.err.println(err.toString());
            return null;
        }
    }

    public LessonDescription getLessonDescription() {
        return lessonDescription;
    }

    public List<Exercise> getActualExercises() {
        return actualExercises;
    }

    public int getCurrentExerciseIndex() {
        return currentExerciseIndex;
    }


}
