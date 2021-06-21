package controller;

import Model.Book;
//import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class BookViewItemController implements Initializable {
    @FXML
    Label lbID, lbName, lbAuthor, lbCategory, lbStocks;
//    @FXML
//    JFXRadioButton circleAvailable;
    @FXML
    Button detailButton;

    @FXML
    StackPane container;

    private int clickCount = 0;

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
//        this.lbID.setText(String.valueOf(this.book.getId()));
//        this.lbName.setText(this.book.getName());
//        this.lbAuthor.setText(this.book.getAuthor().getName());
//        this.lbCategory.setText(this.book.getCategory().getName());
//        this.circleAvailable.setSelected(this.book.isAvailable());
    }
    public void openDetail(){
        clickCount++;
        if(clickCount == 2){
            clickCount = 0;
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/bookDetail.fxml"));
                Parent parent = loader.load();
                //book = loader.getController();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();

            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public void resetClick(MouseEvent mouseEvent){
        clickCount = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.container.setMinHeight(40);
//        //this.container.prefHeightProperty().bind(this.container.widthProperty().divide());
        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbName.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.lbAuthor.prefWidthProperty().bind(this.container.widthProperty().divide(5));
        this.lbCategory.prefWidthProperty().bind(this.container.widthProperty().divide(5));
        //this.lbStocks.prefWidthProperty().bind(this.container.widthProperty().subtract());
        detailButton.prefWidthProperty().bind(this.container.widthProperty());
    }
}
