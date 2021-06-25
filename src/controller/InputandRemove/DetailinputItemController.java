package controller.InputandRemove;

import Model.DetailInput;
import Model.Input;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailinputItemController implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbStock;

    @FXML
    Button detailButton;

    private DetailInput detailInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbStock.prefWidthProperty().bind(this.container.widthProperty());
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());

    }

    public void setInput(DetailInput record) {
        this.detailInput = record;
        this.lbStock.setText(String.valueOf(record.getIdStock()));
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
}
