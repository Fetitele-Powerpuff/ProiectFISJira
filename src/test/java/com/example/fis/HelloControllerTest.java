package com.example.fis;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.PointQuery;
import org.testfx.util.NodeQueryUtils;
import sun.util.locale.LocaleMatcher;


import java.io.IOException;
import java.security.MessageDigest;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

class HelloControllerTest extends ApplicationTest {


    private HelloController controller;
    private Stage stage;
    private Stage stageSignUp;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller=fxmlLoader.getController();
        this.stage = stage;
        controller.setStage(stage);
    }


    @Test
    void testSetStage() {
        //Stage testStage = new Stage();
        controller.setStage(stage);
        assertEquals(stage, controller.getStage());
    }
    @Test
    public void testGetMessageDigest() {
        // Test if SHA-512 MessageDigest is returned
        MessageDigest md = HelloController.getMessageDigest();
        Assertions.assertEquals("SHA-512", md.getAlgorithm());
    }

    @Test
    public void testEncodePassword() {
        String salt = "somesalt";
        String password = "password123";

        // Expected hashed password generated externally
        String expectedHashedPassword = "3��^K\u000E^�|��i\u0010&�O%��u6��Ӟ��OL���\u0005����\u0005\u000E������x�%�rl7Fv�K�E�a\u0012\u0006h";

        String encodedPassword = HelloController.encodePassword(salt, password);
        Assertions.assertEquals(expectedHashedPassword, encodedPassword);
    }




}