package model.exerciseElements;

import java.util.ArrayList;
import java.util.List;

public class LessonDescription {
    private String name;
    private List<ExerciseSettings> possibleExercises = new ArrayList<>();
    private OrderType order;
    private boolean allowSolutionExplanation = true;
    private boolean showAnswersImmediately = false;
    private int maxExercises;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseSettings> getPossibleExercises() {
        return possibleExercises;
    }

    public void setPossibleExercises(List<ExerciseSettings> possibleExercises) {
        this.possibleExercises = possibleExercises;
    }

    public OrderType getOrder() {
        return order;
    }

    public void setOrder(OrderType order) {
        this.order = order;
    }

    public int getMaxExercises() {
        return maxExercises;
    }

    public void setMaxExercises(int maxExercises) {
        this.maxExercises = maxExercises;
    }

    public enum OrderType {
        LIST_FIX, RANDOM_ONCE, RANDOM_ANY
    }

    public enum LessonStyle {
        LEARNING, PRACTICE, EXAM
    }

    public void applyLessonStyle (LessonStyle lessonStyle) {
        switch (lessonStyle) {
            case LEARNING:
                order = OrderType.LIST_FIX;
                allowSolutionExplanation = true;
                showAnswersImmediately = true;
                break;
            case PRACTICE:
                order = OrderType.RANDOM_ANY;
                allowSolutionExplanation = true;
                showAnswersImmediately = false;
                break;
            case EXAM:
                order = OrderType.RANDOM_ONCE;
                allowSolutionExplanation = false;
                showAnswersImmediately = false;
                break;
        }
    }

    public boolean isAllowSolutionExplanation() {
        return allowSolutionExplanation;
    }

    public void setAllowSolutionExplanation(boolean allowSolutionExplanation) {
        this.allowSolutionExplanation = allowSolutionExplanation;
    }

    public boolean isShowAnswersImmediately() {
        return showAnswersImmediately;
    }

    public void setShowAnswersImmediately(boolean showAnswersImmediately) {
        this.showAnswersImmediately = showAnswersImmediately;
    }

    @Override
    public String toString() {
        return name;
    }
}
