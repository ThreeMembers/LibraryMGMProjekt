package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class borrowRecordItemController implements Initializable {
    @FXML
    StackPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        container.setMinHeight(40);
    }
}
