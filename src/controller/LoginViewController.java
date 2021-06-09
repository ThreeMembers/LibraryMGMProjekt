package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    public void Login(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainHolder.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(0);
            stage.setY(0);
            stage.setMaximized(true);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
