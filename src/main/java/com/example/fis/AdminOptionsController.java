package com.example.fis;

import com.jfoenix.controls.JFXButton;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static javafx.application.Application.launch;


public class AdminOptionsController implements Initializable {
    public JFXButton editScreening;
    public JFXButton deleteMovieButton;
    @FXML
    private AnchorPane anchorVisible;

    @FXML
    private JFXButton applyAdd;


    @FXML
    private DatePicker dateAdd;


    @FXML
    private TextField genreAdd;

    @FXML
    private TextField lengthAdd;

    @FXML
    private TextField nameAdd;

    @FXML
    private ComboBox<String> roomAdd;

    @FXML
    private ComboBox<Integer> startAdd;

    @FXML
    private ComboBox<Integer> stopAdd;




    @FXML
    private Text fillFields;

    @FXML
    private JFXButton addButton;

    @FXML
    private AnchorPane anchorVisible1;


    @FXML
    private TableColumn<DataClass, String> dateT;


    @FXML
    private JFXButton closeButton;

    @FXML
    private TableColumn<DataClass, Integer> finish;

    @FXML
    private TableColumn<DataClass, String> roomT;

    @FXML
    private TextField search;
    @FXML
    private AnchorPane pane;

    @FXML
    TableColumn<DataClass, Integer> startT;

    ObservableList<DataClass> data = FXCollections.observableArrayList();
    @FXML
    TableView<DataClass> tabel = new TableView<DataClass>(data);

    @FXML
    TableColumn<DataClass, String> title;

    @FXML
    DatePicker datePickerAddR;
    @FXML
    ChoiceBox<Integer> startAddR;

    @FXML
    ChoiceBox<Integer> stopAddR;

    @FXML
    ChoiceBox<String> roomAddR;

    @FXML
    ChoiceBox<String> nameAddR;
    @FXML
    JFXButton applyAddR;

    boolean checkCollision(String room, DatePicker dp, int start, int stop, String name) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             PreparedStatement statement1 = connection.prepareStatement("SELECT length FROM Film WHERE name = ?")) {

            statement1.setString(1, name);
            ResultSet rs1 = statement1.executeQuery();

            if (rs1.next()) {
                int length = rs1.getInt(1);
                if ((stop - start) != ((length / 60) + 1)) {
                    System.out.println(name + " " + length);
                    System.out.println(stop - start + "!=" + (((rs1.getInt(1) / 60) + 1)));
                    pickAnotherHour1.setVisible(true);
                    pickAnotherHour2.setVisible(true);
                    return false;
                }
            }

            PreparedStatement statement = connection.prepareStatement("SELECT start, stop, type_room, date FROM Reservations");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int strt = rs.getInt(1);
                int stp = rs.getInt(2);
                String tRoom = rs.getString(3);
                String date = rs.getString(4);

                if (date.equals(dp.getValue().toString()) && tRoom.equals(room) && start <= stp && stop >= strt) {
                    collisionDetected.setVisible(true);
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    Integer getId(String name) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             PreparedStatement statement = connection.prepareStatement("SELECT id_film FROM Film WHERE name = ?")) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return -1;
            } else {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addChanges(ActionEvent event) {
        restartText();
        if (startAddR.getValue() != null && stopAddR.getValue() != null && roomAddR.getValue() != null && datePickerAddR.getValue() != null && nameAddR.getValue() != null) {
            int start = Integer.parseInt(startAddR.getValue().toString());
            int stop = Integer.parseInt(stopAddR.getValue().toString());

            if (checkCollision(roomAddR.getValue().toString(), datePickerAddR, start, stop, nameAddR.getValue().toString())) {
                try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                    Integer id = getId(nameAddR.getValue().toString());

                    if (id != null && id != -1) {
                        PreparedStatement statement1 = connection.prepareStatement("INSERT INTO Reservations (id_film, start, stop, type_room, date) VALUES (?, ?, ?,?,?)");
                        statement1.setInt(1, id);
                        statement1.setInt(2, start);
                        statement1.setInt(3, stop);
                        statement1.setString(4, roomAddR.getValue());
                        statement1.setString(5, datePickerAddR.getValue().toString());
                        statement1.executeUpdate();
                        statement1.close();

                        System.out.println("Reservation inserted successfully.");
                    } else {
                        System.out.println("Film not found.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        stage.close();
    }

    @FXML
    void deleteReservation(ActionEvent event) {
        anchorVisible1.setVisible(false);
        anchorVisible.setVisible(false);
        tabel.setVisible(true);
        DataClass selectedFilm = tabel.getSelectionModel().getSelectedItem();

        if (selectedFilm != null) {
            try {
                // Move the selected film to the DeletedFilms table in the database
                Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                String insertQuery = "INSERT INTO DeletedFilms (name,  id_user) VALUES (?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                String insertQuery2 = "delete from Reservations where Reservations.id_reservation=" + selectedFilm.getId_reservation();
                PreparedStatement insertStatement2 = connection.prepareStatement(insertQuery2);
                String insertQuery3 = "delete from ReservationsUser where ReservationsUser.id_reservation=" + selectedFilm.getId_reservation();
                PreparedStatement insertStatement3 = connection.prepareStatement(insertQuery3);

                // Set the values for the insert statement
                insertStatement.setString(1, selectedFilm.getTitle());
                insertStatement.setInt(2, selectedFilm.getId_user()); // Assuming getUserId() returns the appropriate id_user value

                // Execute the insert statement
                insertStatement.executeUpdate();
                insertStatement2.executeUpdate();
                insertStatement3.executeUpdate();

                // Remove the selected film from the TableView
                tabel.getItems().remove(selectedFilm);
                connection.close();
                insertStatement2.close();
                insertStatement3.close();
                insertStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any database errors
            }
        }
    }




    private double x = 0, y = 0;

    ObservableList<DataAdd> dataAdd = FXCollections.observableArrayList();
    @FXML
    JFXButton showReservationsButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            showMovies();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        pane.setBackground(Background.EMPTY);
        closeButton.setRipplerFill(Color.TRANSPARENT);


        pane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        pane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        insertButton.setOnAction(e -> insertPhoto());
        LocalDate today = LocalDate.now();
        LocalDate weekLater = today.plusDays(7);
        datePickerAddR.setValue(today);
        datePickerAddR.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(today) || date.isAfter(weekLater));
            }
        });
        try {
            setNameR();
        }catch (Exception e){
            e.printStackTrace();
        }
        roomAddR.getItems().addAll("2D", "3D", "IMAX", "4D");

        for(int i=10;i<20;i++){
            startAddR.getItems().add(i);
        }

        for(int i=12;i<=22;i++){
            stopAddR.getItems().add(i);
        }

        startAddR.setOnAction(event -> {
            Integer selectedStart = (Integer) startAddR.getValue();
            Integer selectedStop = (Integer) stopAddR.getValue();

            if (selectedStart != null && selectedStop != null && selectedStart > selectedStop) {
                stopAddR.setValue(null);
            }
        });

// Add event handler for stopAdd choice box
        stopAddR.setOnAction(event -> {
            Integer selectedStart = (Integer) startAddR.getValue();
            Integer selectedStop = (Integer) stopAddR.getValue();

            if (selectedStart != null && selectedStop != null && selectedStop < selectedStart) {
                startAddR.setValue(null);
            }
        });


    }



    private void setNameR() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

        // create a new JDBC statement
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT name FROM Film");

        while (rs.next()) {
            nameAddR.getItems().add(rs.getString(1)); // Use column index 1 for the name column
        }

        // Close the ResultSet, Statement, and Connection
        rs.close();
        statement.close();
        connection.close();
    }

    int index=0;
    public void showMovies() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

        // create a new JDBC statement
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT start,stop,type_room,date,name,F.image,Reservations.id_reservation,RU.id_user from Reservations join Film F on F.id_film = Reservations.id_film join ReservationsUser RU on Reservations.id_reservation = RU.id_reservation");


        // Loop through the result set and add each row to the observable list
        while (rs.next()) {
            String name = rs.getString("name");
            int stop = rs.getInt("stop");
            int start = rs.getInt("start");
            String type_room = rs.getString("type_room");
            String date=rs.getString("date");
            int id_user= rs.getInt("id_user");
            int id_reservation= rs.getInt("id_reservation");
            data.add(new DataClass(name,start,stop,date,type_room,id_user,id_reservation));
            //System.out.println(data.get(index).getTitle()+data.get(index).getRoomT()+data.get(index).getDateT()+data.get(index).getId_user());
            index++;
        }


        // Define the columns for the TableView

        title.setCellValueFactory(new PropertyValueFactory<DataClass, String>("title"));
        startT.setCellValueFactory(new PropertyValueFactory<DataClass,Integer>("startT"));
        finish.setCellValueFactory(new PropertyValueFactory<DataClass, Integer>("finish"));
        roomT.setCellValueFactory(new PropertyValueFactory<DataClass, String>("roomT"));
        dateT.setCellValueFactory(new PropertyValueFactory<DataClass, String>("dateT"));
        tabel.setItems(data);

        connection.close();
        statement.close();

    }


    private Stage stage=new Stage();
    public void setStage(Stage stage) {
        this.stage=stage;
    }


    public void showDelete(ActionEvent event) {

        anchorVisible1.setVisible(true);
        anchorVisible.setVisible(false);
        tabel.setVisible(false);
        anchorVisible2.setVisible(false);
    }

    @FXML
    private TextField nameDelete=new TextField();

    @FXML
    private ImageView imageDelete;

    public void showImage(KeyEvent event){
        nameDelete.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                    PreparedStatement statement = connection.prepareStatement("SELECT image FROM Film WHERE name LIKE ?");
                    statement.setString(1, newValue + "%");
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        System.out.println(rs.getString(1));
                        Image image = new Image(rs.getString(1), 150, 150, false, true);
                        imageDelete.setImage(image);
                    } else {
                        // Clear the image if no result is found
                        imageDelete.setImage(null);


                    }
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Clear the image if the text is empty
                imageViewAdd.setImage(null);
            }
        });
    }

    public void deleteMovies(ActionEvent event) {
        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

            // Prepare the delete statement
            String deleteQuery = "DELETE FROM Film WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, nameDelete.getText());


            // Execute the delete statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Film deleted successfully!");
            } else {
                System.out.println("No film found with the given information.");
            }

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showAdd(ActionEvent event) {

        tabel.setVisible(false);
        anchorVisible.setVisible(true);
        anchorVisible1.setVisible(false);
        anchorVisible2.setVisible(false);
    }

//    @FXML
//    void addMovies(ActionEvent event) {
//        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
//             PreparedStatement statement = connection.prepareStatement("INSERT INTO Film (name, genre, length) VALUES (?, ?, ?)")) {
//            statement.setString(1, nameAdd.getText());
//            statement.setString(2, genreAdd.getText());
//            statement.setInt(3, Integer.parseInt(lengthAdd.getText()));
//            statement.executeUpdate();
//            System.out.println("Film inserted successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    @FXML
    ImageView imageViewAdd=new ImageView();

    @FXML
    JFXButton insertButton=new JFXButton();

    @FXML
    Text collisionDetected=new Text();

    @FXML
    Text pickAnotherHour1=new Text();
    @FXML
    Text pickAnotherHour2=new Text();
    @FXML
    private Text pickAnotherRoom;

    private String imagePath;
    public void addMovies(ActionEvent event) {

        if (nameAdd != null && genreAdd != null && lengthAdd != null && imageViewAdd != null)
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO Film (name, genre, length, image) VALUES (?, ?, ?,?)")) {
                statement.setString(1, nameAdd.getText());
                statement.setString(2, genreAdd.getText());
                statement.setInt(3, Integer.parseInt(lengthAdd.getText()));
                statement.setString(4, imagePath);
                statement.executeUpdate();
                System.out.println("Film inserted successfully.");
                statement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public String getConsoleOutput() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(outputStream));
       } finally {
            System.setOut(originalOut);
        }
        return outputStream.toString();
    }


    private void insertPhoto() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                imagePath = selectedFile.getAbsolutePath();
                Image image = new Image(imagePath, 150, 150, false, true);
                imageViewAdd.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    AnchorPane anchorVisible2;
    public void showReservation(ActionEvent event) {
        anchorVisible2.setVisible(true);
        anchorVisible1.setVisible(false);
        tabel.setVisible(false);
        anchorVisible.setVisible(false);
    }

    public void goToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stageSign = new Stage();
        HelloController controller = fxmlLoader.getController();
        scene.setFill(Color.TRANSPARENT);
        stageSign.setScene(scene);
        stageSign.initStyle(StageStyle.TRANSPARENT);
        controller.setStage(stageSign);
        stageSign.show();
        stage.close();
    }

    public void restartText() {
        collisionDetected.setVisible(false);
        pickAnotherHour1.setVisible(false);
        pickAnotherHour2.setVisible(false);
    }

    public Stage getStage() {
        return stage;
    }
}