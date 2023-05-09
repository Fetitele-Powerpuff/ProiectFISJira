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

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private TextField cardNumber;

    @FXML
    private Text warningText;

    @FXML
    private JFXButton close;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private JFXButton signup;

    @FXML
    private AnchorPane topPane;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane mainpane;

    @FXML
    private TextField email;

    private Stage stage;
    private double x=0,y=0;
    @FXML
    void closeWindow(MouseEvent event) {
        stage.close();
    }

    @FXML
    void signupMethod(ActionEvent event) {
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectiondb=connection.geConnection();
        String frstName=firstName.getText();
        String lstName=lastName.getText();
        String usrName=username.getText();
        String mail=email.getText();
        String phone=phoneNumber.getText();
        String card=cardNumber.getText();
        String psw=password.getText();

        if(frstName.isBlank()==true||lstName.isBlank()==true||usrName.isBlank()==true||mail.isBlank()==true||
                phone.isBlank()==true||card.isBlank()==true||psw.isBlank()==true){
            warningText.setVisible(true);
        }else {
            if (phone.matches("[0-9]+") && card.matches("[0-9]+")) {
                warningText.setVisible(false);
                String insertFields = "insert into UserInfo(first_name, last_name, e_mail, username, card_number, phone_number, password) VALUES(";
                String insertValues = "'" + frstName + "','" + lstName + "','" + mail + "','" + usrName + "','" + card + "','" + phone + "','" + psw + "')";

                String insertToRegister = insertFields + insertValues;

                try {
                    Statement statement = connectiondb.createStatement();
                    statement.executeUpdate(insertToRegister);
                    Stage stageLogin=new Stage();
                    HelloApplication hp=new HelloApplication();
                    hp.start(stageLogin);
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                warningText.setVisible(true);
            }
        }

    }


    public void setStage(Stage stage) {
        this.stage=stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainpane.setBackground(Background.EMPTY);
        close.setRipplerFill(Color.TRANSPARENT);

        topPane.setOnMousePressed(mouseEvent -> {
            x= mouseEvent.getSceneX();
            y= mouseEvent.getSceneY();
        });

        bottomPane.setOnMousePressed(mouseEvent -> {
            x= mouseEvent.getSceneX();
            y= mouseEvent.getSceneY();
        });

        mainpane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
    }
}