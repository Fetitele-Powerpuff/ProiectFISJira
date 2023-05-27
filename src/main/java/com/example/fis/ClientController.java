package com.example.fis;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private AnchorPane anchorVisible1;

    @FXML
    private JFXButton bookMovieButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableColumn<UserData, String> cardNumber;

    @FXML
    private JFXButton closeButton;

    @FXML
    private TableColumn<DataClass, String> dateT;

    @FXML
    private TableColumn<UserData, String> email;

    @FXML
    private TableColumn<UserData, String> fName;

    @FXML
    private TableColumn<DataClass, Integer> finish;

    @FXML
    private TableColumn<UserData, String> lName;

    @FXML
    private TableColumn<FilmsData, String> name;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<UserData, String> phoneNumber;

    @FXML
    private JFXButton profileButton;

    @FXML
    private TableColumn<DataClass, String> roomT;

    @FXML
    private AnchorPane seatPane;

    @FXML
    private JFXButton seeMoviesButton;

    @FXML
    private TableColumn<DataClass, Integer> startT;

    @FXML
    private TableView<DataClass> tabel;

    @FXML
    private TableView<FilmsData> tableMovies;

    @FXML
    private TableView<UserData> tableUserInfo;

    @FXML
    private TableColumn<DataClass, String> title;

    @FXML
    private TableColumn<UserData, String> username;

    @FXML
    private JFXButton b1;

    @FXML
    private JFXButton b10;

    @FXML
    private JFXButton b11;

    @FXML
    private JFXButton b12;

    @FXML
    private JFXButton b2;

    @FXML
    private JFXButton b3;

    @FXML
    private JFXButton b4;

    @FXML
    private JFXButton b5;

    @FXML
    private JFXButton b6;

    @FXML
    private JFXButton b7;

    @FXML
    private JFXButton b8;

    @FXML
    private JFXButton b9;

    private ArrayList<JFXButton> buttons=new ArrayList<>();

    @FXML
    void closeWindow(ActionEvent event) {
        stage.close();
    }

    ObservableList<DataClass> data = FXCollections.observableArrayList();
    private int index=0;
    @FXML
    void showMoviesInit(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

            // create a new JDBC statement
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select start,stop,type_room,date,name,id_reservation from Reservations join Film F on Reservations.id_film = F.id_film");


            // Loop through the result set and add each row to the observable list
            while (rs.next()) {
                String name = rs.getString("name");
                int stop = rs.getInt("stop");
                int start = rs.getInt("start");
                int id_reservation = rs.getInt("id_reservation");
                String type_room = rs.getString("type_room");
                String date = rs.getString("date");
                Statement statement1=connection.createStatement();
                ResultSet rs1=statement1.executeQuery("SELECT account_id from UserInfo where username='"+User.getUsername()+"'");
                if(rs1.next())
                    data.add(new DataClass(name,start,stop,date,type_room, rs1.getInt(1),id_reservation ));
                //System.out.println(data.get(index).getTitle()+data.get(index).getRoomT()+data.get(index).getDateT()+data.get(index).getId_user());
                index++;
            }


            // Define the columns for the TableView

            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            startT.setCellValueFactory(new PropertyValueFactory<>("startT"));
            finish.setCellValueFactory(new PropertyValueFactory<>("finish"));
            roomT.setCellValueFactory(new PropertyValueFactory<>("roomT"));
            dateT.setCellValueFactory(new PropertyValueFactory<>("dateT"));
            statement.close();
            connection.close();
            tabel.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    ObservableList<UserData> dataProfile = FXCollections.observableArrayList();
    ObservableList<FilmsData> dataFilms = FXCollections.observableArrayList();
    @FXML
    void showProfileInit() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

            // create a new JDBC statement
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT first_name, last_name, e_mail, username, card_number, phone_number FROM UserInfo WHERE username = '" + User.getUsername() + "'");


            // Loop through the result set and add each row to the observable list
            while (rs.next()) {
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String email = rs.getString("e_mail");
                String username = rs.getString("username");
                String cardNumber= rs.getString("card_number");
                String phoneNumber= rs.getString("phone_number");


                dataProfile.add(new UserData(fName,lName,email,username,cardNumber,phoneNumber));


            }


            // Define the columns for the TableView


            email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
            username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            cardNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCardNumber()));
            phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
            fName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFName()));
            lName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLName()));
            tableUserInfo.setItems(dataProfile);

            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

            // create a new JDBC statement
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT Film.name from Film join Reservations R on Film.id_film = R.id_film join ReservationsUser RU on R.id_reservation = RU.id_reservation join UserInfo UI on RU.id_user = UI.account_id where UI.username='"+User.getUsername()+"'");
            ResultSet rs2 = statement2.executeQuery("SELECT DeletedFilms.name from DeletedFilms  join UserInfo UI on UI.account_id = DeletedFilms.id_user where UI.username='"+User.getUsername()+"'");

            // Loop through the result set and add each row to the observable list
            while (rs.next()) {
                String name = rs.getString("name");
                dataFilms.add(new FilmsData(name));
                System.out.println(name);

            }

            while (rs2.next()) {
                String name = rs2.getString("name");
                dataFilms.add(new FilmsData(name));
                System.out.println(name);
            }


            // Define the columns for the TableView


            name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

            tableMovies.setItems(dataFilms);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void showSeatChoice(ActionEvent event) {
        borderPane.setVisible(false);
        seatPane.setVisible(true);
        pane.setBackground(Background.EMPTY);

        // Retrieve the selected movie's id_reservation
        DataClass selectedFilm = tabel.getSelectionModel().getSelectedItem();
        int selectedReservationId = selectedFilm.getId_reservation();

        // Disable occupied seats
        disableOccupiedSeats(selectedReservationId);
    }

    @FXML
    private JFXButton closeButtonSeats;

    private Stage stage;
    private double x=0,y=0;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private AnchorPane centerPane;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane upPane;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        pane.setBackground(Background.EMPTY);
        borderPane.setBackground(Background.EMPTY);
        seatPane.setBackground(Background.EMPTY);
        closeButton.setRipplerFill(Color.TRANSPARENT);
        seatPane.setBackground(Background.EMPTY);
        leftPane.setBackground(Background.EMPTY);
        upPane.setBackground(Background.EMPTY);
        centerPane.setBackground(Background.EMPTY);

        tabel.setVisible(true);
        anchorVisible1.setVisible(false);

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);
        buttons.add(b10);
        buttons.add(b11);
        buttons.add(b12);

        pane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        pane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        showProfileInit();
        showMoviesInit();

    }

    public void showProfile(ActionEvent event) {
        tabel.setVisible(false);
        anchorVisible1.setVisible(true);
    }

    public void showMovies(ActionEvent event) {
        tabel.setVisible(true);
        anchorVisible1.setVisible(false);


    }

    private void disableOccupiedSeats(int reservationId) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             PreparedStatement statement = connection.prepareStatement("SELECT seat FROM ReservationsUser WHERE id_reservation = ?")) {
            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();

            // Get the list of occupied seat numbers
            List<Integer> occupiedSeats = new ArrayList<>();
            while (resultSet.next()) {
                occupiedSeats.add(resultSet.getInt("seat"));
            }

            // Disable buttons (seats) that are occupied
            for (JFXButton button : buttons) {

                JFXButton seatButton = button;
                String seatNumber = seatButton.getText();
                int seat = Integer.parseInt(seatNumber);

                if (occupiedSeats.contains(seat)) {

                    seatButton.setDisable(true);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleSeatButtonClick(ActionEvent event) {
        JFXButton clickedButton = (JFXButton) event.getSource();
        clickedButton.setDisable(true);
        String seatNumber = clickedButton.getText();
        System.out.println(clickedButton.getText());
        // Add the seatNumber to the SQLite table
        addSeatToTable(seatNumber);
    }

    void addSeatToTable(String seatNumber) {
        DataClass selectedFilm = tabel.getSelectionModel().getSelectedItem();
        if (selectedFilm != null) {
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO ReservationsUser (id_reservation, seat, id_user) VALUES (?, ?, ?)")) {
                statement.setInt(1, selectedFilm.getId_reservation());
                statement.setInt(2, Integer.parseInt(seatNumber));
                statement.setInt(3, selectedFilm.getId_user());

                statement.executeUpdate();
                System.out.println("Reservation inserted successfully.");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    private JFXButton loginButton;
    public void goToLoginPage(ActionEvent event) throws IOException {
        User.setPassword(null);
        User.setUsername(null);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stageSign = new Stage();
        HelloController controller = fxmlLoader.getController(); // Use HelloController instead of ClientController
        scene.setFill(Color.TRANSPARENT);
        stageSign.setScene(scene);
        stageSign.initStyle(StageStyle.TRANSPARENT);
        controller.setStage(stageSign);
        stageSign.show();
        stage.close();
    }

    public void closeWindowSeats(ActionEvent event) {
        seatPane.setVisible(false);
        borderPane.setVisible(true);
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, null, null);
        Background background = new Background(backgroundFill);
        centerPane.setBackground(background);
    }
}
