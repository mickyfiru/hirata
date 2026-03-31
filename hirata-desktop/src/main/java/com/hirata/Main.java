package com.hirata;

import com.hirata.util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        SceneManager.loadScene("/view/login.fxml", "Hirata - Login", 400, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
