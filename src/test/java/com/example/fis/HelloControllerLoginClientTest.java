package com.example.fis;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.jupiter.api.Assertions.*;

public class HelloControllerLoginClientTest extends ApplicationTest {

    private HelloController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    @Test
    public void testValidateLogin_SuccessfulLogin() {
        // Arrange
        TextField usernameTextField = lookup("#username").query();
        TextField passwordTextField = lookup("#password").query();

        // Act
        clickOn(usernameTextField).write("mihai_dancea");
        clickOn(passwordTextField).write("test");
        controller.validateLogin();

        // Assert
        verifyThat("#invalidLoginText", NodeMatchers.isInvisible());
        // Add additional assertions as needed
    }

    @Test
    public void testValidateLogin_EmptyFields() {
        // Arrange
        TextField usernameTextField = lookup("#username").query();
        TextField passwordTextField = lookup("#password").query();

        // Act
        clickOn(usernameTextField).eraseText(10);
        clickOn(passwordTextField).eraseText(10);
        controller.validateLogin();

        // Assert
        verifyThat("#invalidLoginText", NodeMatchers.isVisible());
    }

    @Test
    public void testValidateLogin_InvalidLogin() {
        // Arrange
        TextField usernameTextField = lookup("#username").query();
        TextField passwordTextField = lookup("#password").query();

        // Act
        clickOn(usernameTextField).write("admin");
        clickOn(passwordTextField).write("wrong_password");
        controller.validateLogin();

        // Assert
        verifyThat("#invalidLoginText", NodeMatchers.isVisible());
        // Add additional assertions as needed
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{}); // Release all keys
        release(new MouseButton[]{}); // Release all buttons
    }
}
