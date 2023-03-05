package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import db.ConUtil;

public class LoginController implements Initializable {
    
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Label lblErrors;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;
    @FXML
    private Button closeImg;

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public void handleButtonAction(MouseEvent event) throws InterruptedException {
        if (event.getSource() == btnSignin) {
            if (logIn().equals("Success")) {
                try {
                    Thread.sleep(400);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (con == null) {
            lblErrors.setTextFill(Color.YELLOW);
            lblErrors.setText("Server Disconnected");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server Connected");
        }
    }

    public LoginController() {
        con = ConUtil.vacDB();
    }

    private String logIn() {
        String status = "Success";
        String email = txtEmail.getText();
        String password = txtPass.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.RED, "Empty credentials!");
            status = "Error";
        } else {
            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    txtPass.setText("");
                    txtEmail.requestFocus();
                    setLblError(Color.RED, "Enter correct credentials!");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful!");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }

        return status;
    }

    @FXML
    public void handleButtonAction2(MouseEvent event) throws InterruptedException {
        if (event.getSource() == btnSignup) {
            try {
                Thread.sleep(200);
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Register.fxml"));
                loginPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
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
}
