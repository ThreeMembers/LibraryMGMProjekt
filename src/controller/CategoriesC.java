package controller;

import Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesC implements Initializable {
    @FXML
    private TableView<Category> table;

    private TableColumn<Category , Integer> IDColumn ;

    private TableColumn<Category, String>NameColumn;

    private TableColumn<Category,String>DescriptionColumn;

    private ObservableList <Category> CategoryList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryList = FXCollections.observableArrayList(
                new Category(1,"DragonBall","a"),
                new Category(2,"Naruto","b"),
                new Category(3,"OnePiece","c")
        );
        IDColumn.setCellValueFactory(new PropertyValueFactory<Category,Integer>("ID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Category,String>("Name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<Category,String>("Description"));
        table.setItems(CategoryList);
    }
}
