package com.example.fis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloSignupControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testSignupFormCheckTextOk() {
        TextField firstNameTextField = lookup("#firstName").query();
        TextField lastNameTextField = lookup("#lastName").query();
        TextField usernameTextField = lookup("#username").query();
        TextField passwordTextField = lookup("#password").query();
        TextField cardNumberTextField = lookup("#cardNumber").query();
        TextField phoneNumberTextField = lookup("#phoneNumber").query();
        TextField emailTextField = lookup("#email").query();

        assertEquals("First Name", firstNameTextField.getPromptText());
        assertEquals("Last Name", lastNameTextField.getPromptText());
        assertEquals("Username", usernameTextField.getPromptText());
        assertEquals("Password", passwordTextField.getPromptText());
        assertEquals("Card Number", cardNumberTextField.getPromptText());
        assertEquals("Phone Number", phoneNumberTextField.getPromptText());
        assertEquals("E-mail", emailTextField.getPromptText());
    }
}
