package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class borrowRequestItemController implements Initializable {
    @FXML
    StackPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.container.setMinHeight(40);
    }
}
