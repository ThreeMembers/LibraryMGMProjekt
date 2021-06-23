package controller;

import Model.StockBook;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StockBookViewItemController implements Initializable {
    private StockBook stockBook;

    @FXML
    StackPane container;
    @FXML
    Label lbID, lbBook, lbQuality, lbRelease, lbIsBorrow;
    @FXML
    Button detailButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.container.setMinHeight(40);

        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbBook.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.lbQuality.prefWidthProperty().bind(this.container.widthProperty().divide(5));
        this.lbRelease.prefWidthProperty().bind(this.container.widthProperty().divide(8));
        this.lbIsBorrow.prefWidthProperty().bind(this.container.widthProperty().divide(8));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());
    }

    public void setStock(StockBook stockBook) {
        this.stockBook = stockBook;
        this.lbID.setText(String.valueOf(this.stockBook.getId()));
        this.lbBook.setText(this.stockBook.getBook().getName());
        this.lbQuality.setText(this.stockBook.getQuality().getSituation().toUpperCase());
        this.lbRelease.setText(String.valueOf(this.stockBook.getReleaseYear()));
        this.lbIsBorrow.setText(String.valueOf(this.stockBook.isBorrow()).toUpperCase());
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
}
