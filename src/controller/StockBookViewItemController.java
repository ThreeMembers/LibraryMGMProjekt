package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StockBookViewItemController implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbBook, lbQuality, lbRelease, lbIsBorrow;
    @FXML
    Button detailButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.container.setMinHeight(40);

        this.lbBook.prefWidthProperty().bind(this.container.widthProperty().divide(1.9));
        this.lbQuality.prefWidthProperty().bind(this.container.widthProperty().divide(6.2));
        this.lbRelease.prefWidthProperty().bind(this.container.widthProperty().divide(6.2));
        this.lbIsBorrow.prefWidthProperty().bind(this.container.widthProperty().divide(6.2));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());
    }
}
