package controller.bookDetail;

import Model.StockBook;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;


public class BookDetailItemController {
    private StockBook stockBook;

    @FXML
    Label lbID, lbName, lbQuality, lbYear, lbBorrow;

    @FXML
    Pane container;

    public void setStockBook(StockBook list){
        this.stockBook = list;
        this.lbID.setText(String.valueOf(stockBook.getId()));
        this.lbName.setText(stockBook.getBook().getName());
        this.lbQuality.setText(stockBook.getQuality().getSituation());
        this.lbYear.setText(String.valueOf(stockBook.getReleaseYear()));
        this.lbBorrow.setText(String.valueOf(stockBook.isBorrow()));
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
}
