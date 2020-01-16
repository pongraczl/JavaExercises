package gui_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.exerciseElements.LessonDescription;
import res.lessons.BuiltInForTest;

import java.io.IOException;
import java.util.List;

public class LessonsPanelController {

    @FXML
    private Button btnStartLesson;

    @FXML
    private ListView<LessonDescription> lessonList;

    @FXML
    public void initialize() {
        initLessons(BuiltInForTest.BUILTIN_LESSONS);
    }

    private void initLessons(List<LessonDescription> lessons) {
        lessonList.getItems().addAll(lessons);
    }


    public void startLesson(ActionEvent event) throws IOException {
        LessonDescription selectedLesson = lessonList.getSelectionModel().getSelectedItem();
        if (selectedLesson == null) {
            new Alert(Alert.AlertType.INFORMATION, "Select a lesson to start!").show();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("exercisePanel.fxml"));
            Parent exerciseSceneContent = loader.load();
            ExercisePanelController controller = loader.getController();
            Scene currentScene = btnStartLesson.getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            controller.initLesson(selectedLesson, currentScene);
            currentStage.setScene(new Scene(exerciseSceneContent, 1000, 600));

        }
    }
}
