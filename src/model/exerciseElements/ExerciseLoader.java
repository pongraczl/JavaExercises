package model.exerciseElements;

import exerciseTypes._RegisteredExercises;

import java.util.*;

public class ExerciseLoader {
    private ExerciseLoader(){}
    private static Map<String, Class<? extends ExerciseFactory>> exerciseFactoryMap = new HashMap<>();
    static {
        for (Class<? extends ExerciseFactory> c : _RegisteredExercises.getExerciseFactories()) {
            exerciseFactoryMap.put(c.getSimpleName(), c);
        }
    }

    public static Exercise getNewExercise(String exerciseCode, ExerciseSettings exerciseSettings)
            throws ExerciseException {
        return getExerciseFactory(exerciseCode).getExercise(exerciseSettings);
    }

    public static List<ExerciseParameter> getPossibleParameters(String exerciseCode)
            throws ExerciseException {
        return getExerciseFactory(exerciseCode).getPossibleParameters();
    }

    private static ExerciseFactory getExerciseFactory(String exerciseCode)
            throws ExerciseException {

        Class exerciseFactoryClass = exerciseFactoryMap.get(exerciseCode);
        if (exerciseFactoryClass == null) throw new ExerciseFactoryNotFoundException(exerciseCode);

        try {
            ExerciseFactory exerciseFactory = (ExerciseFactory) exerciseFactoryClass.getConstructor().newInstance();
            return exerciseFactory;
        } catch (Throwable exception) {
            throw new IncorrectExerciseFactoryException(exerciseCode);
        }
    }

    public static List<String> getExerciseCodes() {
        return new ArrayList<>(exerciseFactoryMap.keySet());
    }

    public static abstract class ExerciseException extends Exception{
        public ExerciseException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class ExerciseFactoryNotFoundException extends ExerciseException {
        public ExerciseFactoryNotFoundException(String exerciseCode) {
            super("Exercise not found: '" + exerciseCode + "'");
        }
    }

    public static class IncorrectExerciseFactoryException extends ExerciseException {
        public IncorrectExerciseFactoryException(String exerciseCode) {
            super("Error in the exercise: '" + exerciseCode + "'");
        }
    }
}
