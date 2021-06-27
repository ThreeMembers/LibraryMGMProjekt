package controller.borrowTask;

import Model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class AddBorrowRequestItemController {
    @FXML
    Label lbBook, lbQuantity;
    @FXML
    CheckBox checkbox;

    @FXML
    StackPane container;

    public void setDetail(Book book, int quantity) {
        this.lbBook.setText(book.getName());
        this.lbQuantity.setText(String.valueOf(quantity));
        this.container.setAccessibleText("node");
    }
    public void checked(){
        if(this.checkbox.isSelected()){
            this.container.setAccessibleText("checked");
        }
        else {
            this.container.setAccessibleText("node");
        }
    }
}
