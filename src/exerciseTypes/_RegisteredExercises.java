package exerciseTypes;

import model.exerciseElements.ExerciseFactory;

import java.util.ArrayList;
import java.util.List;

public class _RegisteredExercises {
    private static List<Class<? extends ExerciseFactory>> exerciseFactories = new ArrayList<>();
    public static List<Class<? extends ExerciseFactory>> getExerciseFactories() { return exerciseFactories; }

    /*
     * New Exercises must be registered below
     */
    static {
        exerciseFactories.add(StaticNonStaticAccess.class);
    }
}
