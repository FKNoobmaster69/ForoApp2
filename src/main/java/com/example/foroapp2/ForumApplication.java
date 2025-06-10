package com.example.foroapp2;

import com.example.foroapp2.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class ForumApplication extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        SceneManager.cambiarEscena("login.fxml");
        stage.setTitle("Foro App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
