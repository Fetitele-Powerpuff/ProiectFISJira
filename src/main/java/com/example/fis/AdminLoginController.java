package com.example.fis;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
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
            validateLogin();

        }
    }

    public void validateLogin(){
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectiondb=connection.geConnection();
        String verify = "select count(1) from AdminInfo where adminName ='"+username.getText()+"' and password='"+password.getText()+"'";

        try{
            Statement statement=connectiondb.createStatement();
            ResultSet resultSet=statement.executeQuery(verify);

            while (resultSet.next()){
                if(resultSet.getInt(1)==1){
                    invalidLoginText.setVisible(false);

                }else {
                    invalidLoginText.setVisible(true);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void closeWindow(MouseEvent event) {
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

}
