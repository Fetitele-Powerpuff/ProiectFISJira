package com.example.fis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.MessageDigest;

public class HelloApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController controller=fxmlLoader.getController();
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        controller.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    Stage stage=new Stage();
    public void setStage(Stage stage) {
        this.stage=stage;
    }


}