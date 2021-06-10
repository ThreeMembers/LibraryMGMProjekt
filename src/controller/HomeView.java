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
    private JFXComboBox<String> catechoices;
    ObservableList<String> cateListchoices = FXCollections.observableArrayList("New", "New", "New", "New");
    @FXML
    private JFXComboBox<String> authorchoices;
    ObservableList<String> authorListchoices = FXCollections.observableArrayList("New", "New", "New", "New");

    @FXML
    HBox title;

    @FXML
    Label lbID, lbName, lbAuthor, lbCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catechoices.setItems(cateListchoices);
        authorchoices.setItems(authorListchoices);
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
    }
}
