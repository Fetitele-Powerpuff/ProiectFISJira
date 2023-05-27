package com.example.fis;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerAddSeatTest extends ApplicationTest {

    ClientController controller;
    @Start
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testAddSeatToTable(){
            DataClass selectedFilm = new DataClass("The Hunger Games",10,13,"2023-06-02","3D",1,26);

        Platform.runLater(() -> controller.addSeatToTable("5"));

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM ReservationsUser WHERE seat = ? AND id_reservation=?")) {
                statement.setInt(1, 5);
                statement.setInt(1, selectedFilm.getId_user());

                try (ResultSet resultSet = statement.executeQuery()) {

                    assertNotNull(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

}