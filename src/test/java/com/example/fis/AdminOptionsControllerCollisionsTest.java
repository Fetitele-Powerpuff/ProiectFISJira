package com.example.fis;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdminOptionsControllerCollisionsTest extends ApplicationTest {

    private AdminOptionsController adminOptionsController;

    @Override
    public void start(Stage stage) throws Exception {
        adminOptionsController = new AdminOptionsController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminOptions.fxml"));
        //fxmlLoader.setController(adminOptionsController);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @Test
    public void testCheckCollision_NoCollision_ReturnsTrue() {
        // Arrange
        String room = "3D";
        DatePicker dp = new DatePicker(LocalDate.parse("2023-06-02"));
        int start = 16;
        int stop = 19;
        String name = "The Hunger Games";

        // Act
        boolean result = adminOptionsController.checkCollision(room, dp, start, stop, name);

        // Assert
        assertTrue(result);
    }
    @Test
    public void testCheckCollision_CollisionDetected_ReturnsFalse_SameInterval() {
        // Arrange
        String room = "3D";
        DatePicker dp = new DatePicker(LocalDate.parse("2023-06-02"));
        int start = 10;
        int stop = 13;
        String name = "The Hunger Games";

        // Act
        boolean result = adminOptionsController.checkCollision(room, dp, start, stop, name);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testCheckCollision_CollisionDetected_ReturnsFalse_LeftCollision() {
        // Arrange
        String room = "3D";
        DatePicker dp = new DatePicker(LocalDate.parse("2023-06-02"));
        int start = 9;
        int stop = 12;
        String name = "The Hunger Games";

        // Act
        boolean result = adminOptionsController.checkCollision(room, dp, start, stop, name);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testCheckCollision_CollisionDetected_ReturnsFalse_RightCollision() {
        // Arrange
        String room = "3D";
        DatePicker dp = new DatePicker(LocalDate.parse("2023-06-02"));
        int start = 12;
        int stop = 15;
        String name = "The Hunger Games";

        // Act
        boolean result = adminOptionsController.checkCollision(room, dp, start, stop, name);

        // Assert
        Assertions.assertFalse(result);
    }
}