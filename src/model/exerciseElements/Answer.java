package model.exerciseElements;

public class Answer {
    protected String answerText;

    public Answer(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isTheSame(Answer answer) {
        return answer.answerText.equals(answerText);
    }
}
