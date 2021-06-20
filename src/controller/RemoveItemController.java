package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveItemController implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbEmployee, lbDate,lbMessager;

    @FXML
    Button detailButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbEmployee.prefWidthProperty().bind(this.container.widthProperty().divide(2));
        this.lbDate.prefWidthProperty().bind(this.container.widthProperty().divide(4));
        this.lbMessager.prefWidthProperty().bind(this.container.widthProperty().divide(4));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());

    }
}
