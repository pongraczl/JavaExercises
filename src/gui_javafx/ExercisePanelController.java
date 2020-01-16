package gui_javafx;

import exerciseTypes.StaticNonStaticAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
//import model._deleteLater.StaticNonstaticAccessExercise_old;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.exerciseElements.*;
import res.lessons.BuiltInForTest;

import java.util.ArrayList;
import java.util.List;

public class ExercisePanelController {

    private static final String buttonBackgroundNormal = "white";
    private static final String buttonBackgroundCorrect = "green";
    private static final String buttonBackgroundFail = "red";
    private static final String buttonBackgroundCorrectWas = "#88ff88";

    private LessonState runningLesson;
    private Exercise currentExercise;
    private enum EXERCISE_STATE {START, ACTIVE, RESULT, END}
    private EXERCISE_STATE state = EXERCISE_STATE.START;

    private Scene returnToScene;

    @FXML
    private GridPane container;
    @FXML
    private Label lblExerciseTitle;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblQuestion;
    @FXML
    private VBox figureContainer;
    @FXML
    private FlowPane solutionExplanationContainer;
    @FXML
    private FlowPane answersPane;
    private List<AnswerButton> answerButtons = new ArrayList<>();
    @FXML
    private Button btnNext;
    @FXML
    private Button btnQuit;

    public void initLesson(LessonDescription lessonDescription, Scene returnToScene) {
        runningLesson = LessonState.createNewLesson(lessonDescription);
        this.returnToScene = returnToScene;
        showNewExercise();
    }

    @FXML
    public void initialize() {
        btnNext.setOnAction(e -> showNewExercise());
        btnQuit.setOnAction(e -> returnToPrevScene());
    }

    private void returnToPrevScene() {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(returnToScene);
    }

    private void showNewExercise() {
        //currentExercise = StaticNonstaticFromTheClassExercise.getRandomExercise(true);
        //currentExercise = StaticNonstaticAccessExercise.getRandomExercise(false);
        //currentExercise = new StaticNonStaticAccess().getExercise(new ExerciseSettings());
        //currentExercise = ExerciseLoader.getNewExercise("StaticNonStaticAccess", new ExerciseSettings());

        currentExercise = runningLesson.getNextExercise();
        if (currentExercise == null) {
            finishLesson(
                    "\"" + runningLesson.getLessonDescription().getName() + "\" is over"
            );
            return;
        }

        lblExerciseTitle.setText(currentExercise.getExerciseTitleText());
        lblDescription.setText(currentExercise.getExerciseDescription());
        lblQuestion.setText(currentExercise.getQuestionText());
        solutionExplanationContainer.getChildren().clear();

        setFigure(figureContainer, currentExercise.getExerciseFigure());
        setFigure(solutionExplanationContainer, null);

        answersPane.getChildren().clear();
        answerButtons.clear();
        for (Answer answer : currentExercise.getSolution().getPossibleAnswers()) {
            AnswerButton newButton = new AnswerButton(answer);
            newButton.setOnAction(actionEvent -> showCorrectAnswer((AnswerButton) actionEvent.getSource()));
            //setButtonBackground(newButton, buttonBackgroundNormal);
            //newButton.setDisable(false);
            answersPane.getChildren().add(newButton);
            answerButtons.add(newButton);
        }
        btnNext.setDisable(true);
        container.layout();

        if(runningLesson.getLessonDescription().isShowAnswersImmediately()) {
            showCorrectAnswer(null);
        }
    }

    private void showCorrectAnswer(AnswerButton clickedButton) {
        Answer correctAnswer = currentExercise.getSolution().getCorrectAnswer();
        boolean isTheCorrectAnswerClicked;
        if (clickedButton != null) {
            Answer givenAnswer = clickedButton.answer;
            isTheCorrectAnswerClicked = currentExercise.getSolution().isTheAnswerCorrect(givenAnswer);
        } else {
            isTheCorrectAnswerClicked = true;
        }
        Button correctButton = null;
        //Button clickedButton;
        for (AnswerButton button : answerButtons) {
            button.setDisable(true);
            if (button.answer.isTheSame(correctAnswer)) correctButton = button;
        }
        btnNext.setDisable(false);
        if (!isTheCorrectAnswerClicked) setButtonBackground(clickedButton, buttonBackgroundFail);
        if (correctButton != null) {
            setButtonBackground(correctButton, isTheCorrectAnswerClicked ? buttonBackgroundCorrect : buttonBackgroundCorrectWas);
        }
        if (runningLesson.getLessonDescription().isAllowSolutionExplanation()) {
            setFigure(solutionExplanationContainer, currentExercise.getSolutionExplanationFigure());
        }
    }

    private void finishLesson(String message) {
        new Alert(Alert.AlertType.INFORMATION, message).showAndWait();
        returnToPrevScene();
    }

    private void setFigure(Pane container, Figure content) {
        container.getChildren().clear();
        if (content == null) return;

        if (content instanceof Figure.SimpleTextFigure) {
            String figureText = ((Figure.SimpleTextFigure) content).getText();
            TextArea textArea = new TextArea(figureText);
            textArea.setWrapText(true);
            textArea.setEditable(false);
            textArea.setMinWidth(250.0);
            textArea.setPrefHeight(200.0);
            container.getChildren().add(textArea);
        } else if (content instanceof Figure.CodeFigure) {
            Figure.CodeFigure codeFigure = (Figure.CodeFigure) content;

            for (Figure.CodeFigure.FileNode file : codeFigure.getFileNodes()) {
                String fullPackagePath = file.fullPackagePath;
                Label filePathAndName = new Label(
                        (fullPackagePath == "" ? "" : fullPackagePath + "/")
                                + file.fileName
                );
                container.getChildren().add(filePathAndName);

                TextArea fileContent = new TextArea(file.codeText);
                fileContent.setEditable(false);
                fileContent.setMinHeight(100.0);
                fileContent.setPrefHeight(250.0);
                container.getChildren().add(fileContent);
                /*ScrollPane fileContentContainer = new ScrollPane(fileContent);
                        fileContentContainer.setMinViewportHeight(50.0);
                        fileContentContainer.s
                figureContainer.getChildren().add(fileContentContainer);*/
            }
        } else {
            //TODO
        }
    }

    private static void setButtonBackground(Button btn, String colorString) {
        btn.setStyle("-fx-background-color: " + colorString + ";");
    }

    protected static class AnswerButton extends Button {
        Answer answer;

        public AnswerButton(Answer answer) {
            super(answer.getAnswerText());
            this.answer = answer;
        }
    }
}
