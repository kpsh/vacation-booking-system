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
import javafx.fxml.Initializable;
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

public class RentController implements Initializable {
    
    @FXML
    private Label price;
    @FXML
    private TextField model;
    @FXML
    private TextField type;
    @FXML
    private TextField driver;
    @FXML
    private TextField name;
    @FXML
    private TextField number;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> country;
    @FXML
    private DatePicker pickupDate;
    @FXML
    private DatePicker returnDate;
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet res = null;
    
    public RentController() {
        con = ConUtil.vacDB();
    }

    @FXML
    void btn1(MouseEvent event) {
        model.setText("Citroen C3 2017");
        type.setText("Hatchback");
        price.setText("035$ / day");
    }

    @FXML
    void btn2(MouseEvent event) {
        model.setText("Mercedes-Benz CLA 2017");
        type.setText("Sedan");
        price.setText("055$ / day");
    }

    @FXML
    void btn3(MouseEvent event) {
        model.setText("Range Rover Velar 2019");
        type.setText("SUV");
        price.setText("080$ / day");
    }

    @FXML
    void btn4(MouseEvent event) {
        model.setText("Hyundai Accent 2019");
        type.setText("Sedan");
        price.setText("045$ / day");
    }

    @FXML
    void btn5(MouseEvent event) {
        model.setText("Mercedes-Benz Vito 2020");
        type.setText("Van");
        price.setText("110$ / day");
    }

    @FXML
    void btn6(MouseEvent event) {
        model.setText("Audi A6 2015");
        type.setText("Sedan");
        price.setText("065$ / day");
    }

    @FXML
    void btn7(MouseEvent event) {
        model.setText("Jeep Compass 2018");
        type.setText("SUV");
        price.setText("090$ / day");
    }

    @FXML
    void btn8(MouseEvent event) {
        model.setText("Jaguar XF 2018");
        type.setText("Sedan");
        price.setText("090$ / day");
    }

    @FXML
    void btn9(MouseEvent event) {
        model.setText("Volvo XC60 2018");
        type.setText("SUV");
        price.setText("075$ / day");
    }

    @FXML
    void btn10(MouseEvent event) {
        model.setText("Nissan Qashqai 2018");
        type.setText("SUV");
        price.setText("050$ / day");
    }

    @FXML
    void btn11(MouseEvent event) {
        model.setText("Volkswagen Polo 2015");
        type.setText("Hatchback");
        price.setText("040$ / day");
    }

    @FXML
    void btn12(MouseEvent event) {
        model.setText("Ford Fiesta 2017");
        type.setText("Hatchback");
        price.setText("035$ / day");
    }

    @FXML
    void btn13(MouseEvent event) {
        model.setText("Lexus LX 2019");
        type.setText("SUV");
        price.setText("150$ / day");
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
    void clearBtn(MouseEvent event) {
        model.setText("");
        type.setText("");
        driver.setText("");
        name.setText("");
        number.setText("");
        email.setText("");
        age.setText("");
        price.setText("000$ / day");
    }

    @FXML
    void rentBtn(MouseEvent event) {
        if (model.getText().isEmpty() || type.getText().isEmpty() || driver.getText().isEmpty() || name.getText().isEmpty() || number.getText().isEmpty() || email.getText().isEmpty() || age.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Please fill all the fields properly!");
            alert.showAndWait();
        } else {
            String rid = model.getText();
            String cname = name.getText();
            String cemail = email.getText();
            String cnr = number.getText();
            
            String sql = " insert into shoplist (shopid, cname, cemail, cnumber)" + " values (?, ?, ?, ?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, rid);
                stm.setString(2, cname);
                stm.setString(3, cemail);
                stm.setString(4, cnr);
                stm.execute();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(RentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Car Renting!");
            alert.setHeaderText("Car rented successfully!");
            alert.setContentText("Enjoy your road trips!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        number.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return c;
            }
        }
        ));
        age.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return c;
            }
        }
        ));

        driver.setTextFormatter(new TextFormatter<>((change) -> {
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

        country.getItems().removeAll(country.getItems());
        country.getItems().addAll("Albania", "Montenegro", "Croatia");
    }

}
