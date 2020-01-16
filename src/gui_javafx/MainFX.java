package gui_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import model._deleteLater.Test;

public class MainFX extends Application {

    private static MainFX currentApp;

    @Override
    public void start(Stage primaryStage) throws Exception{
        currentApp = this;

        Parent root = FXMLLoader.load(getClass().getResource("lessonsPanel.fxml"));
        primaryStage.setTitle("Java Exercises");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

    }

    public static MainFX getInstance() {
        return currentApp;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
