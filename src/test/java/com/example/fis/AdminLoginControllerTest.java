package com.example.fis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.*;

public class AdminLoginControllerTest extends ApplicationTest {

    private AdminLoginController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminLogin.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testCheckEmpty_ValidUsernameAndPassword_Success() {
        // Arrange
        TextField usernameField = lookup("#username").query();
        TextField passwordField = lookup("#password").query();
        Text invalidLoginText = lookup("#invalidLoginText").query();

        // Set username and password
        usernameField.setText("cristi.andron");
        passwordField.setText("cfthn56");

        // Act
        interact(() -> controller.checkEmpty(null));

        // Assert
        assertFalse(invalidLoginText.isVisible());
        // Add additional assertions as needed
    }

    @Test
    public void testCheckEmpty_MissingUsernameOrPassword_DisplayWarning() {
        // Arrange
        TextField usernameField = lookup("#username").query();
        TextField passwordField = lookup("#password").query();
        Text invalidLoginText = lookup("#invalidLoginText").query();

        // Set either username or password empty
        usernameField.setText("cristi.andron");
        passwordField.setText("");

        // Act
        interact(() -> controller.checkEmpty(null));

        // Assert
        assertTrue(invalidLoginText.isVisible());
        // Add additional assertions as needed
    }


    @Test
    public void testValidateLogin_InvalidCredentials_DisplayWarning() throws IOException {
        // Arrange
        TextField usernameField = new TextField("admin");
        TextField passwordField = new TextField("wrong_password");
        Text invalidLoginText = new Text();

        // Set up controller state
        controller.setUsername(usernameField);
        controller.setPassword(passwordField);
        controller.setInvalidLoginText(invalidLoginText);

        // Act
        controller.validateLogin();

        // Assert
        assertTrue(invalidLoginText.isVisible());
        // Add additional assertions as needed
    }

    // Add more test methods as needed

}