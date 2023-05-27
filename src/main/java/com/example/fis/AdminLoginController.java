package com.example.fis;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable {

    @FXML
    private JFXButton exitButton;

    @FXML
    private Text invalidLoginText;

    @FXML
    private AnchorPane mainpane;

    @FXML
    private AnchorPane paneleft;

    @FXML
    private AnchorPane paneright;

    @FXML
    private TextField password;


    @FXML
    private TextField username;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private double x=0,y=0;

    @FXML
    public void checkEmpty(ActionEvent event){
        if(username.getText().isBlank()==false && password.getText().isBlank()==false){
            try {
                validateLogin();
            }catch (Exception e){
                e.printStackTrace();
            }


        }else{
            invalidLoginText.setVisible(true);
        }
    }

    public void validateLogin() throws IOException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectiondb = connection.geConnection();
        if (username != null && password != null) {
            String verify = "SELECT COUNT(1) FROM AdminInfo WHERE adminName = ? AND password = ?";

            try (PreparedStatement statement = connectiondb.prepareStatement(verify)) {
                // Set the parameter values
                statement.setString(1, username.getText());
                statement.setString(2, password.getText());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        invalidLoginText.setVisible(false);
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminOptions.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        AdminOptionsController controller = fxmlLoader.getController();
                        scene.setFill(Color.TRANSPARENT);
                        if(stage!=null) {
                            stage.setScene(scene);
                            controller.setStage(stage);
                            stage.show();
                        }

                    } else {
                        invalidLoginText.setVisible(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void closeWindow(MouseEvent event) {
        if(stage!=null)
         stage.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainpane.setBackground(Background.EMPTY);
        exitButton.setRipplerFill(Color.TRANSPARENT);

        paneright.setOnMousePressed(mouseEvent -> {
            x= mouseEvent.getSceneX();
            y= mouseEvent.getSceneY();
        });

        paneleft.setOnMousePressed(mouseEvent -> {
            x= mouseEvent.getSceneX();
            y= mouseEvent.getSceneY();
        });

        mainpane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
    }

    public void setUsername(TextField usernameField) {
        username=usernameField;
    }

    public void setPassword(TextField passwordField) {
        password=passwordField;
    }

    public void setInvalidLoginText(Text invalidLoginText) {
        this.invalidLoginText=invalidLoginText;
    }
}
