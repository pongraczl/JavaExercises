package model.exerciseElements;


public class Exercise {
    protected String exerciseTitleText = "(Title)";
    protected String exerciseDescription = "(Description)";
    protected String questionText = "(question)";
    protected Figure exerciseFigure;
    protected Figure solutionExplanationFigure;
    protected Solution solution;
    protected boolean isBalanced;

    public Exercise(String exerciseTitleText, String exerciseDescription, String questionText, Figure exerciseFigure,
                    Solution solution) {
        this.exerciseTitleText = exerciseTitleText;
        this.exerciseDescription = exerciseDescription;
        this.questionText = questionText;
        this.exerciseFigure = exerciseFigure;
        this.solution = solution;
    }

    public Exercise(ExerciseSettings exerciseSettings) {
        if (exerciseSettings.getCustomTitle() != null) exerciseTitleText = exerciseSettings.getCustomTitle();
        if (exerciseSettings.getCustomDescription() != null) exerciseDescription = exerciseSettings.getCustomDescription();
        if (exerciseSettings.getCustomQuestion() != null) questionText = exerciseSettings.getCustomQuestion();
        if (exerciseSettings.getCustomSolutionExplanation() != null) {
            solutionExplanationFigure = new Figure.SimpleTextFigure(exerciseSettings.getCustomSolutionExplanation());
        }
        isBalanced = exerciseSettings.isBalanced();
    }

    public String getExerciseTitleText() {
        return exerciseTitleText;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Figure getExerciseFigure() {
        return exerciseFigure;
    }

    public void setExerciseFigure(Figure exerciseFigure) {
        this.exerciseFigure = exerciseFigure;
    }

    public Figure getSolutionExplanationFigure() {
        return solutionExplanationFigure;
    }

    public void setSolutionExplanationFigure(Figure solutionExplanationFigure) {
        this.solutionExplanationFigure = solutionExplanationFigure;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
