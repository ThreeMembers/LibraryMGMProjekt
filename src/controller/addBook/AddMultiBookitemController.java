package controller.addBook;

import Model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class AddMultiBookitemController {
    @FXML
    Pane container;
    @FXML
    Label lbName, lbAuthor, lbCate;
    @FXML
    CheckBox checkBox;

    Book book;

    public void setBook(Book book) {
        this.book = book;
        this.lbName.setText(book.getName());
        this.lbAuthor.setText(book.getAuthor().getName());
        this.lbCate.setText(book.getCategory().getName());
        this.container.setAccessibleText(String.valueOf(this.book.getId()));
    }
    public void checked(){
        if(this.checkBox.isSelected()){
            this.container.setAccessibleText("checked");
        }
        else {
            this.container.setAccessibleText(String.valueOf(this.book.getId()));
        }
    }
}
