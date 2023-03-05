package controllers;

import db.ConUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HotelController {

    @FXML
    private Label closeBtn;
    @FXML
    private Label price;
    @FXML
    private TextField hotel;
    @FXML
    private TextField city;
    @FXML
    private TextField clientid;
    @FXML
    private TextField name;
    @FXML
    private TextField number;
    @FXML
    private TextField email;
    @FXML
    private DatePicker checkinDate;
    @FXML
    private DatePicker checkoutDate;
    @FXML
    private ComboBox<String> room;
    @FXML
    private ComboBox<String> extras;
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet res = null;
    
    public HotelController() {
        con = ConUtil.vacDB();
    }

    @FXML
    void btn1(MouseEvent event) {
        hotel.setText("Premium Beach Hotel");
        city.setText("Durres");
        price.setText("00180$");
    }

    @FXML
    void btn2(MouseEvent event) {
        hotel.setText("Regina Blu Hotel");
        city.setText("Vlore");
        price.setText("00200$");
    }

    @FXML
    void btn3(MouseEvent event) {
        hotel.setText("Splendid Spa Resort");
        city.setText("Budva");
        price.setText("00210$");
    }

    @FXML
    void btn4(MouseEvent event) {
        hotel.setText("Hotel Budva");
        city.setText("Budva");
        price.setText("00260$");
    }

    @FXML
    void btn5(MouseEvent event) {
        hotel.setText("Hotel Lone");
        city.setText("Rovinj");
        price.setText("00300$");
    }

    @FXML
    void btn6(MouseEvent event) {
        hotel.setText("Hotel Bellevue");
        city.setText("Mali Lošinj");
        price.setText("00290$");
    }

    @FXML
    void clearBtn(MouseEvent event) {
        hotel.setText("");
        city.setText("");
        clientid.setText("");
        name.setText("");
        number.setText("");
        email.setText("");
        price.setText("000000");
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
    void rentBtn(MouseEvent event) {
        if (hotel.getText().isEmpty() || city.getText().isEmpty() || clientid.getText().isEmpty() || name.getText().isEmpty() || number.getText().isEmpty() || email.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Please fill all the fields properly!");
            alert.showAndWait();
        } else {
            String hid = hotel.getText();
            String cname = name.getText();
            String cemail = email.getText();
            String cnr = number.getText();
            
            String sql = " insert into shoplist (shopid, cname, cemail, cnumber)" + " values (?, ?, ?, ?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, hid);
                stm.setString(2, cname);
                stm.setString(3, cemail);
                stm.setString(4, cnr);
                stm.execute();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hotel Booking!");
            alert.setHeaderText("Hotel room was booked near the beach :D!");
            alert.setContentText("Enjoy your vacations!");
            alert.showAndWait();
        }
    }

    public void initialize() {
        number.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return c;
            }
        }
        ));

        clientid.setTextFormatter(new TextFormatter<>((change) -> {
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

        extras.setDisable(true);
        room.getItems().removeAll(room.getItems());
        room.getItems().addAll("Single", "Double", "King", "Queen");
        room.setOnAction((event) -> {
            Object selectedItem = room.getSelectionModel().getSelectedItem();
            price.setText("00380$");
            extras.setDisable(false);
        });
        extras.getItems().removeAll(extras.getItems());
        extras.getItems().addAll("Beach View", "Pool", "Room Service");
        extras.setOnAction((event) -> {
            Object selectedItem2 = extras.getSelectionModel().getSelectedItem();
            price.setText("00450$");
        });
    }

}
