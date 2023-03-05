package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private Button closeW;
    @FXML
    private Pane background;

    @FXML
    void outBtn(MouseEvent event) throws IOException, InterruptedException {
        Thread.sleep(400);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void closeW(MouseEvent event) throws InterruptedException {
        Thread.sleep(400);
        Stage stage = (Stage) closeW.getScene().getWindow();
        stage.close();
    }

    @FXML
    void mapAction(MouseEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    void flightsAction(MouseEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/Flight.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    void hotelsAction(MouseEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/Hotel.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    void carsAction(MouseEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/Rent.fxml"));
        background.getChildren().setAll(pane);
    }
    @FXML
    void goAdmin(MouseEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/Admin.fxml"));
        background.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
