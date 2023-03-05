package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import db.ConUtil;
import db.Flight;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class FlightController implements Initializable {

    @FXML
    private TextField flightField;
    @FXML
    private TextField seatsField;
    @FXML
    private TextField name;
    @FXML
    private TextField number;
    @FXML
    private TextField email;
    @FXML
    private TextField country;
    @FXML
    private Label priceLbl;
    @FXML
    private ComboBox<String> luggages;
    @FXML
    private ComboBox<String> seats;
    @FXML
    private ComboBox<String> extras;

    @FXML
    private TableView<Flight> flights;
    @FXML
    private TableColumn<Flight, String> fid;
    @FXML
    private TableColumn<Flight, String> ffrom;
    @FXML
    private TableColumn<Flight, String> fto;
    @FXML
    private TableColumn<Flight, String> fdate;
    @FXML
    private TableColumn<Flight, String> fclass;
    @FXML
    private TableColumn<Flight, String> fseats;

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet res = null;

    public FlightController() {
        con = ConUtil.vacDB();
    }

    ObservableList<Flight> oblist = FXCollections.observableArrayList();

    public void Textfields() {
        if (flights.getSelectionModel().getSelectedItem() != null) {
            Flight fields = flights.getSelectionModel().getSelectedItem();
            flightField.setText(fields.getFid());
            seatsField.setText(fields.getFseats());
            priceLbl.setText("00200$");
        }
    }
    

    @FXML
    void closeW(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void bookBtn(MouseEvent event) {
        if (flightField.getText().isEmpty() || seatsField.getText().isEmpty() || name.getText().isEmpty() || number.getText().isEmpty() || email.getText().isEmpty() || country.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Please fill all the fields properly!");
            alert.showAndWait();
        } else {
            String fid = flightField.getText();
            String cname = name.getText();
            String cemail = email.getText();
            String cnr = number.getText();
            
            String sql = " insert into shoplist (shopid, cname, cemail, cnumber)" + " values (?, ?, ?, ?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, fid);
                stm.setString(2, cname);
                stm.setString(3, cemail);
                stm.setString(4, cnr);
                stm.execute();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Airplane Ticket!");
            alert.setHeaderText("Airplane ticket bought!");
            alert.setContentText("Enjoy your flight!");
            alert.showAndWait();
        }
    }

    @FXML
    void clearBtn(MouseEvent event) {
        flightField.setText("");
        seatsField.setText("");
        country.setText("");
        name.setText("");
        number.setText("");
        email.setText("");
        priceLbl.setText("000000");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from flight");

            while (rs.next()) {
                oblist.add(new Flight(rs.getString("fid"), rs.getString("ffrom"), rs.getString("fto"), rs.getString("fdate"), rs.getString("fclass"), rs.getString("fseats")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
        }

        fid.setCellValueFactory(new PropertyValueFactory<>("fid"));
        ffrom.setCellValueFactory(new PropertyValueFactory<>("ffrom"));
        fto.setCellValueFactory(new PropertyValueFactory<>("fto"));
        fdate.setCellValueFactory(new PropertyValueFactory<>("fdate"));
        fclass.setCellValueFactory(new PropertyValueFactory<>("fclass"));
        fseats.setCellValueFactory(new PropertyValueFactory<>("fseats"));

        flights.setItems(oblist);

        flights.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() < 2) {
                Textfields();
            }
        });

        number.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return c;
            }
        }
        ));

        country.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));

        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        name.setTextFormatter(formatter);

        seats.setDisable(true);
        extras.setDisable(true);
        
        luggages.getItems().removeAll(luggages.getItems());
        luggages.getItems().addAll("10kg", "20kg", "30kg", "50kg");
        luggages.setOnAction((event) -> {
            Object selectedItem = luggages.getSelectionModel().getSelectedItem();
            priceLbl.setText("00330$");
            seats.setDisable(false);
        });
        seats.getItems().removeAll(seats.getItems());
        seats.getItems().addAll("Near Windows", "Comfy", "Private");
        seats.setOnAction((event) -> {
            Object selectedItem2 = seats.getSelectionModel().getSelectedItem();
            priceLbl.setText("00375$");
            extras.setDisable(false);
        });
        extras.getItems().removeAll(extras.getItems());
        extras.getItems().addAll("Taxi Shuttle", "Parking");
        extras.setOnAction((event) -> {
            Object selectedItem3 = extras.getSelectionModel().getSelectedItem();
            priceLbl.setText("00450$");
        });
    }
}
