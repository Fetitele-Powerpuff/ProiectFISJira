package com.example.fis;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.concurrent.TimeoutException;

public class AdminOptionsControllerAddTest extends ApplicationTest {

    private AdminOptionsController controller;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminOptions.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        // Reset the JavaFX application state after each test
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    @Test
    public void testAddChangesWithValidInput() {
        Platform.runLater(() -> {
            // Set up the input values
            controller.startAddR.setValue(14);
            controller.stopAddR.setValue(17);
            controller.roomAddR.setValue("2D");
            controller.datePickerAddR.setValue(LocalDate.now());
            controller.nameAddR.setValue("The Hunger Games");

            // Redirect standard output to a ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            // Call the method under test
            controller.addChanges(new ActionEvent());

            // Restore standard output
            System.setOut(System.out);

            // Convert the output to a string
            String output = outputStream.toString();

            // Assert the expected results
            assertTrue(output.contains("Reservation inserted successfully."));
            assertFalse(output.contains("Film not found."));
            assertFalse(controller.collisionDetected.isVisible());
            assertFalse(controller.pickAnotherHour1.isVisible());
            assertFalse(controller.pickAnotherHour2.isVisible());
        });
    }

    @Test
    public void testAddChangesWithInvalidInput() {
        Platform.runLater(() -> {
            // Set up the input values
            controller.startAddR.setValue(10);
            controller.stopAddR.setValue(8);
            controller.roomAddR.setValue("Room B");
            controller.datePickerAddR.setValue(LocalDate.now());
            controller.nameAddR.setValue("Movie 2");

            // Redirect standard output to a ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            // Call the method under test
            controller.addChanges(new ActionEvent());

            // Restore standard output
            System.setOut(System.out);

            // Convert the output to a string
            String output = outputStream.toString();

            // Assert the expected results
            assertFalse(output.contains("Reservation inserted successfully."));
            assertFalse(controller.collisionDetected.isVisible());
            assertFalse(controller.pickAnotherHour1.isVisible());
            assertFalse(controller.pickAnotherHour2.isVisible());
        });
    }
}
