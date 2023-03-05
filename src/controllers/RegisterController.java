package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import db.ConUtil;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.control.TextFormatter;

public class RegisterController {

    @FXML
    private AnchorPane registerPane;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button Submit;
    @FXML
    private Label lblErrors;
    @FXML
    private Button closeImg;

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet res = null;

    @FXML
    public void handleButtonAction(MouseEvent event) throws IOException, InterruptedException {
        if (event.getSource() == Submit) {
            if (register().equals("Success")) {
                Thread.sleep(400);
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    private String register() {
        String status = "Success";
        String name = txtName.getText();
        String email = txtEmail.getText();
        String pass = txtPass.getText();
        if (name.equals("") && email.equals("") && pass.equals("")) {
            setLblError(Color.RED, "Please fill the text fields!");
            status = "Error";
        } else {
            String sql = " insert into users (name, email, password)" + " values (?, ?, ?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, email);
                stm.setString(3, pass);
                stm.execute();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return status;
    }

    public RegisterController() {
        con = ConUtil.vacDB();
    }

    @FXML
    void backW(MouseEvent event) throws IOException, InterruptedException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Thread.sleep(200);
        registerPane.getChildren().setAll(pane);
    }

    @FXML
    void closeW(MouseEvent event) throws InterruptedException {
        Thread.sleep(200);
        Stage stage = (Stage) closeImg.getScene().getWindow();
        stage.close();
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        txtName.setTextFormatter(formatter);
    }
}
