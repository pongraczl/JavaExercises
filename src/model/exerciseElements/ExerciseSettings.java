package model.exerciseElements;

import model.langElements.general.Value;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExerciseSettings {
    private String currentExerciseName;
    private String customTitle;
    private String customDescription;
    private String customQuestion;
    private String customSolutionExplanation;
    private String exerciseFactoryCode;
    private Map<String, Value> exerciseParameterValues = new LinkedHashMap<>();
    private boolean isBalanced;

    public String getCurrentExerciseName() {
        return currentExerciseName;
    }

    public void setCurrentExerciseName(String currentExerciseName) {
        this.currentExerciseName = currentExerciseName;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public String getCustomDescription() {
        return customDescription;
    }

    public void setCustomDescription(String customDescription) {
        this.customDescription = customDescription;
    }

    public String getCustomQuestion() {
        return customQuestion;
    }

    public void setCustomQuestion(String customQuestion) {
        this.customQuestion = customQuestion;
    }

    public String getCustomSolutionExplanation() {
        return customSolutionExplanation;
    }

    public void setCustomSolutionExplanation(String customSolutionExplanation) {
        this.customSolutionExplanation = customSolutionExplanation;
    }

    public String getExerciseFactoryCode() {
        return exerciseFactoryCode;
    }

    public void setExerciseFactoryCode(String exerciseFactoryCode) {
        this.exerciseFactoryCode = exerciseFactoryCode;
    }

    public Map<String, Value> getExerciseParameterValues() {
        return exerciseParameterValues;
    }

    public void addExerciseParameter(String parameterCode, Value parameterValue) {
        exerciseParameterValues.put(parameterCode, parameterValue);
    }

    public Value getParam(String paramId) { return exerciseParameterValues.get(paramId); }

    public Value getParamOrDefault(String paramId, Value defaultValue) {
        Value value = exerciseParameterValues.get(paramId);
        if (value == null) return defaultValue;
        else return value;
    }

    public String getStringParamOrDefault(String paramId, String defaultValue) {
        Value value = getParam(paramId);
        if (value instanceof Value.StringValue) {
            return ((Value.StringValue) value).value;
        } else {
            if (defaultValue != null) {
                return defaultValue;
            } else {
                return "";
            }
        }
    }

    public int getIntParamOrDefault(String paramId, int defaultValue) {
        Value value = getParam(paramId);
        if (value instanceof Value.IntegerValue) {
            return (int) ((Value.IntegerValue) value).value;
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolParamOrDefault(String paramId, boolean defaultValue) {
        Value value = getParam(paramId);
        if (value instanceof Value.BooleanValue) {
            return ((Value.BooleanValue) value).value;
        } else {
            return defaultValue;
        }
    }

    public boolean isBalanced() {
        return isBalanced;
    }

    public void setBalanced(boolean balanced) {
        isBalanced = balanced;
    }

    public ExerciseSettings getCopy() {
        ExerciseSettings eS = new ExerciseSettings();
        eS.currentExerciseName = currentExerciseName;
        eS.customTitle = customTitle;
        eS.customDescription = customDescription;
        eS.customQuestion = customQuestion;
        eS.customSolutionExplanation = customSolutionExplanation;
        eS.exerciseFactoryCode = exerciseFactoryCode;
        for (Map.Entry<String, Value> epv : exerciseParameterValues.entrySet()) {
            eS.exerciseParameterValues.put(epv.getKey(), epv.getValue());
        }
        eS.isBalanced = isBalanced;
        return eS;
    }

    public ExerciseSettings createCascaded(ExerciseSettings secondary) {
        ExerciseSettings newES = secondary.getCopy();
        if (currentExerciseName != null) newES.currentExerciseName = currentExerciseName;
        if (customTitle != null) newES.customTitle = customTitle;
        if (customDescription != null) newES.customDescription = customDescription;
        if (customQuestion != null) newES.customQuestion = customQuestion;
        if (customSolutionExplanation != null) newES.customSolutionExplanation = customSolutionExplanation;
        for (Map.Entry<String, Value> epv : exerciseParameterValues.entrySet()) {
            newES.exerciseParameterValues.put(epv.getKey(), epv.getValue());
        }
        newES.isBalanced = isBalanced;
        return newES;
    }

}
