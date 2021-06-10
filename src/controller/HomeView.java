package controller;

import Model.Author;
import Model.Book;
import Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeView implements Initializable {
    @FXML
    VBox bookContent = new VBox();
    @FXML
    private TableView<Author> table;
    @FXML
    private TableColumn<Author , Integer> IDColumn ;
    @FXML
    private TableColumn<Author , Integer> AgeColumn ;
    @FXML
    private TableColumn<Author , Integer> NumColumn ;
    @FXML
    private TableColumn<Author , Boolean> GenderColumn ;
    @FXML
    private TableColumn<Author, String> NameColumn;
    @FXML
    private ObservableList<Author> AuthorList;

    @FXML
    private TableView<Category> tb;
    @FXML
    private TableColumn<Category , Integer> IdColumn ;
    @FXML
    private TableColumn<Category, String>nameColumn;
    @FXML
    private TableColumn<Category,String>DescriptionColumn;
    @FXML
    private ObservableList <Category> CategoryList;

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
        AuthorList = FXCollections.observableArrayList(
                new Author(1,"A",12,1,true),
                new Author(2,"B",20,2,false)

        );
        IDColumn.setCellValueFactory(new PropertyValueFactory<Author,Integer>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("Name"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<Author,Integer>("Age"));
        NumColumn.setCellValueFactory(new PropertyValueFactory<Author,Integer>("Number"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<Author,Boolean>("Gender"));
        table.setItems(AuthorList);

        CategoryList = FXCollections.observableArrayList(
                new Category(1,"DragonBall","a"),
                new Category(2,"Naruto","b"),
                new Category(3,"OnePiece","c")
        );
        IdColumn.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Category,String>("Name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<Category,String>("Description"));
        tb.setItems(CategoryList);
    }
}
