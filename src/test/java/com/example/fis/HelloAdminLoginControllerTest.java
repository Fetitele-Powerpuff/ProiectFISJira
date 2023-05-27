package com.example.fis;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.WindowMatchers.isNotFocused;

import javafx.scene.input.MouseButton;

import java.io.IOException;

public class HelloAdminLoginControllerTest extends ApplicationTest {

    AdminLoginController controller=new AdminLoginController();
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        controller.setStage(stage);
    }

    @Test
    public void testAdminLoginMethodValidateLogin() throws IOException {
        TextField usernameTextField = lookup("#username").query();
        TextField passwordTextField = lookup("#password").query();
        JFXButton exitButton = lookup("#exitButton").query();

        assertNotNull(usernameTextField);
        assertNotNull(passwordTextField);
        assertNotNull(exitButton);

        FxRobot robot = new FxRobot();


        // Scenario 1: Successful login
        robot.clickOn(usernameTextField).write("cristi.andron");
        robot.clickOn(passwordTextField).write("cfthn56");
        controller.validateLogin();

        verifyThat("#invalidLoginText", NodeMatchers.isInvisible());

        // Scenario 2: Empty fields
        robot.clickOn(usernameTextField).eraseText(5);
        robot.clickOn(passwordTextField).eraseText(8);
        controller.validateLogin();
        verifyThat("#invalidLoginText", NodeMatchers.isInvisible());

        // Scenario 3: Invalid login
        robot.clickOn(usernameTextField).write("admin");
        robot.clickOn(passwordTextField).write("wrong_password");
        controller.validateLogin(); // Call the validateLogin method directly

        verifyThat("#invalidLoginText", NodeMatchers.isInvisible());

        robot.clickOn(exitButton);
        Window stage = robot.robotContext().getWindowFinder().targetWindow();
        assertTrue(stage.isShowing());

    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{}); // Release all keys
        release(new MouseButton[]{}); // Release all buttons
    }
}