package com.example.foroapp2;

import com.example.foroapp2.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        stage.setMaximized(true);
        SceneManager.cambiarEscena("login", "ForoApp – Iniciar Sesión");
    }

    public static void main(String[] args) {
        launch();
    }
}
