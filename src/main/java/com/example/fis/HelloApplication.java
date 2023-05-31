package com.example.fis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        deleteReservations();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController controller=fxmlLoader.getController();
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        controller.setStage(stage);
        stage.show();
    }

    private void deleteReservations(){

        LocalDate localDate=LocalDate.now();
        String deleteQuery = "DELETE FROM Reservations WHERE date < ?";
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            ){
            statement.setString(1, localDate.toString());
            int rowAffected=statement.executeUpdate();
            if(rowAffected>0){
                System.out.println("The database has reservations out of date");
            }else{
                System.out.println("The database is up to date");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }

    Stage stage=new Stage();
    public void setStage(Stage stage) {
        this.stage=stage;
    }


}