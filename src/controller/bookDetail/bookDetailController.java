package controller.bookDetail;

import Model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.event.ActionEvent;

public class bookDetailController {

    public static String imageURL = "http://localhost:8080/libraryapi/webapi/images/";

    private Book book;

    @FXML
    Label lbName, lbAuthor, lbCate, lbStock;

    @FXML
    Button btnAvai;

    @FXML
    ImageView imgBook;

    public void openEditBookDetail(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bookDetail/editBookDetail.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setTitle("Edit Book Detail");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setBook(Book book) {
        this.book = book;
        this.lbName.setText(this.book.getName());
        this.lbCate.setText(this.book.getCategory().getName().toUpperCase());
        this.lbAuthor.setText(this.book.getAuthor().getName());
        Image image = new Image(imageURL + this.book.getImage());
        this.imgBook.setImage(image);
    }
}
