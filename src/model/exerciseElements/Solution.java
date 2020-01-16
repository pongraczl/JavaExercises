package model.exerciseElements;

import java.util.ArrayList;
import java.util.List;

public abstract class Solution {
    protected List<Answer> possibleAnswers = new ArrayList<>();
    protected Answer correctAnswer;

    public static class BoolSolution extends Solution {
        //TODO: resources/text/exerciseElements
        public static final String DEFAULT_TRUE_ANSWER = "Yes";
        public static final String DEFAULT_FALSE_ANSWER = "No";

        public BoolSolution(boolean isTrueTheCorrectAnswer) {
            this(DEFAULT_TRUE_ANSWER, DEFAULT_FALSE_ANSWER, isTrueTheCorrectAnswer);
        }

        public BoolSolution(String trueAnswerText, String falseAnswerText, boolean isTrueTheCorrectAnswer) {
            Answer trueAnswer = new Answer(trueAnswerText);
            Answer falseAnswer = new Answer(falseAnswerText);
            possibleAnswers.add(trueAnswer);
            possibleAnswers.add(falseAnswer);
            correctAnswer = isTrueTheCorrectAnswer ? trueAnswer : falseAnswer;
        }
    }

    public List<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<Answer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isTheAnswerCorrect(Answer answer) {
        return correctAnswer.isTheSame(answer);
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
