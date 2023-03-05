/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.ConUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PC-2
 */
public class AdminController implements Initializable {

    @FXML
    private Pane hide;
    @FXML
    private TextField ffrom;
    @FXML
    private TextField fid;
    @FXML
    private TextField fto;
    @FXML
    private TextField fdate;
    @FXML
    private TextField fclass;
    @FXML
    private TextField fseats;
    @FXML
    private PasswordField fcode;

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet res = null;

    public AdminController() {
        con = ConUtil.vacDB();
    }

    @FXML
    void checkBtn(MouseEvent event) {
        String code = fcode.getText();

        if (code.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the code to gain access!");
        } else {
            try {
                stm = con.prepareStatement("select * from admin where adminid=?");

                stm.setString(1, code);

                res = stm.executeQuery();
                if (res.next()) {
                    hide.setVisible(true);
                } else {
                    hide.setVisible(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Access denied!");
                    alert.setContentText("Add the correct admin code!");
                    alert.showAndWait();
                    fcode.setText("");
                    fcode.requestFocus();
                }

            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void bookBtn(MouseEvent event) {
        if (fid.getText().isEmpty() || ffrom.getText().isEmpty() || fto.getText().isEmpty() || fdate.getText().isEmpty() || fseats.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error!");
            alert.setContentText("Please fill all the fields properly!");
            alert.showAndWait();
        } else {
            String id = fid.getText();
            String from = ffrom.getText();
            String to = fto.getText();
            String date = fdate.getText();
            String cll = fclass.getText();
            String seats = fseats.getText();

            String sql2 = " insert into flight (fid, ffrom, fto, fdate, fclass, fseats)" + " values (?, ?, ?, ?, ?, ?)";
            try {
                stm = con.prepareStatement(sql2);
                stm.setString(1, id);
                stm.setString(2, from);
                stm.setString(3, to);
                stm.setString(4, date);
                stm.setString(5, cll);
                stm.setString(6, seats);
                stm.execute();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Flight Added!");
            alert.setHeaderText("Flight successfully registered!");
            alert.showAndWait();
        }
    }

    @FXML
    void clearBtn(MouseEvent event) {
        fid.setText("");
        ffrom.setText("");
        fto.setText("");
        fdate.setText("");
        fclass.setText("");
        fseats.setText("");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fseats.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) {
                return null;
            } else {
                return c;
            }
        }
        ));
        fid.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));
    }

}
