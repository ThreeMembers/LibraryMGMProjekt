package controller.addStocks;

import Model.StockBook;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class AddMultiStocksItemController {

    @FXML
    Label lbName, lbQlt, lbYear;

    @FXML
    CheckBox checkBox;

    @FXML
    StackPane pane;

    private StockBook stockBook;

    public void setStock(StockBook stockBook) {
        this.stockBook = stockBook;
        this.lbName.setText(stockBook.getBook().getName());
        this.lbQlt.setText(stockBook.getQuality().getSituation().toUpperCase());
        this.lbYear.setText(String.valueOf(stockBook.getReleaseYear()));
        this.pane.setAccessibleText("node");
    }

    public void checked(){
        if(this.checkBox.isSelected()){
            this.pane.setAccessibleText("checked");
        }
        else {
            this.pane.setAccessibleText("node");
        }
    }
}
