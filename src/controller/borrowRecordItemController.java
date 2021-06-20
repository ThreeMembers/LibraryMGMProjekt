package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class borrowRecordItemController implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbID,lbReader,lbEmployee,lbDateCheck,lbDateReturn;

    @FXML
    Button detailButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbReader.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.lbEmployee.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.lbDateCheck.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.lbDateCheck.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());
    }
}
