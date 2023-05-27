package com.example.fis;

import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminOptionsControllerDeleteTest extends ApplicationTest {


    private AdminOptionsController controller;

    @Override
    public void start(Stage stage) throws Exception {
        controller = new AdminOptionsController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminOptions.fxml"));
        //fxmlLoader.setController(adminOptionsController);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @Test
    public void testDeleteMoviesWithExistingFilm() {
        TextField nameDelete = lookup("#nameDelete").query();
        nameDelete.setText("The Hunger Games");
        Platform.runLater(() -> controller.deleteMovies(new ActionEvent()));
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                String selectQuery = "SELECT * FROM Film WHERE name = ?";
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                selectStatement.setString(1, "The Hunger Games");
                boolean filmExists = selectStatement.executeQuery().next();
                Assertions.assertTrue(filmExists, "Film deleted successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                Assertions.fail("An exception occurred while accessing the database.");
            }

    }

    @Test
    public void testDeleteMoviesWithNonExistingFilm(){
        TextField nameDelete = lookup("#nameDelete").query();
        nameDelete.setText("Movie 1");

        Platform.runLater(() -> controller.deleteMovies(new ActionEvent()));
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            String selectQuery = "SELECT * FROM Film WHERE name = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, "Movie 1");
            boolean filmExists = selectStatement.executeQuery().next();

            Assertions.assertFalse(filmExists, "Film deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            Assertions.fail("An exception occurred while accessing the database.");
        }

    }
}
