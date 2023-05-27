package com.example.fis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AdminOptionsControllerGetIdTest extends ApplicationTest {

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
    void testGetId_ExistingName(){
        String name = "The Hunger Games";
        int expectedId = 10;
        int actualId = adminOptionsController.getId(name);
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetId_NonExistingName(){
        String name = "NonExistingName";
        int expectedId = -1;
        int actualId = adminOptionsController.getId(name);
        Assertions.assertEquals(expectedId, actualId);
    }

}
