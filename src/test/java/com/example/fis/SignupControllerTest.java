package com.example.fis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SignupControllerTest extends ApplicationTest {

    private SignupController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testSignupMethod_AllFieldsFilled_Success() {
        // Arrange
        TextField firstNameField = lookup("#firstName").query();
        TextField lastNameField = lookup("#lastName").query();
        TextField usernameField = lookup("#username").query();
        TextField emailField = lookup("#email").query();
        TextField phoneNumberField = lookup("#phoneNumber").query();
        TextField cardNumberField = lookup("#cardNumber").query();
        TextField passwordField = lookup("#password").query();
        Text warningText = lookup("#warningText").query();

        // Set field values
        firstNameField.setText("Jane");
        lastNameField.setText("Doe");
        usernameField.setText("janedoe");
        emailField.setText("janedoe@example.com");
        phoneNumberField.setText("1234567999");
        cardNumberField.setText("1234567890123555");
        passwordField.setText("password");

        // Act
        clickOn("#signup");

        // Assert
        assertFalse(warningText.isVisible());
        // Add additional assertions as needed
    }

    @Test
    public void testSignupMethod_MissingFields_DisplayWarning() {
        // Arrange
        TextField firstNameField = lookup("#firstName").query();
        TextField lastNameField = lookup("#lastName").query();
        TextField usernameField = lookup("#username").query();
        TextField emailField = lookup("#email").query();
        TextField phoneNumberField = lookup("#phoneNumber").query();
        TextField cardNumberField = lookup("#cardNumber").query();
        TextField passwordField = lookup("#password").query();
        Text warningText = lookup("#warningText").query();

        // Set some fields empty
        firstNameField.setText("John");
        lastNameField.setText("");
        usernameField.setText("");
        emailField.setText("johndoe@example.com");
        phoneNumberField.setText("");
        cardNumberField.setText("1234567890123456");
        passwordField.setText("");

        // Act
        clickOn("#signup");

        // Assert
        assertTrue(warningText.isVisible());

    }


}
