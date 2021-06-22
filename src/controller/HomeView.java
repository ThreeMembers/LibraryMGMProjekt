package controller;

import Model.Author;
import Model.Book;
import Model.Category;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeView implements Initializable {
    @FXML
    VBox bookContent = new VBox();
    @FXML
    VBox stockBookContent = new VBox();
    @FXML
    private JFXComboBox<String> catechoices;
    ObservableList<String> cateListchoices = FXCollections.observableArrayList("Single", "Multi");
    @FXML
    private JFXComboBox<String> authorchoices;
    ObservableList<String> authorListchoices = FXCollections.observableArrayList("Book", "Author", "Category", "Stock");

    @FXML
    HBox title;

    @FXML
    Label lbID, lbName, lbAuthor, lbCategory, lbstocks;
    @FXML
    Label lbBook, lbQuality, lbRelease, lbIsBorrow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catechoices.setItems(cateListchoices);
        catechoices.setValue("Single");
        authorchoices.setItems(authorListchoices);
        authorchoices.setValue("Book");
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

        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbName.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbAuthor.prefWidthProperty().bind(this.title.widthProperty().divide(5));
        this.lbCategory.prefWidthProperty().bind(this.title.widthProperty().divide(5));
        this.lbstocks.prefWidthProperty().bind(this.title.widthProperty().divide(18.3));



        List<Node> stockBookItems = new ArrayList<>();
        for(int i = 0; i < 50; i++){

            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/StockBookViewItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                stockBookItems.add(e);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.stockBookContent.getChildren().addAll(stockBookItems);

        this.lbBook.prefWidthProperty().bind(this.title.widthProperty().divide(1.9));
        this.lbQuality.prefWidthProperty().bind(this.title.widthProperty().divide(6.2));
        this.lbRelease.prefWidthProperty().bind(this.title.widthProperty().divide(6.2));
        this.lbIsBorrow.prefWidthProperty().bind(this.title.widthProperty().divide(6.2));
    }
}
