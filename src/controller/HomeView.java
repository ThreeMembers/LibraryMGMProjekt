package controller;

import Model.Author;
import Model.Book;
import Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeView implements Initializable {
    @FXML
    VBox bookContent = new VBox();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Book temp = new Book(
                1,
                "UI Design",
                new Author(
                        1,
                        "KAKAKA",
                        20,
                        20,
                        true),
                new Category(
                        1,
                        "Good",
                        "nothing break"),
                "",
                0,
                true
        );
        List<Node> bookItems = new ArrayList<>();
        for(int i = 0; i < 50; i++){

            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/BookViewItem.fxml"));
                Node e = loader.load();
                BookViewItemController bookViewItemController = loader.getController();
                bookViewItemController.setBook(temp);
                bookItems.add(e);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.bookContent.getChildren().addAll(bookItems);
    }
}
