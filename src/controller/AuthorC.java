package controller;

import Model.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorC implements Initializable  {
    @FXML
    private TableView<Author> table;

    private TableColumn<Author , Integer> IDColumn ;

    private TableColumn<Author , Integer> AgeColumn ;

    private TableColumn<Author , Integer> NumColumn ;

    private TableColumn<Author , Boolean> GenderColumn ;

    private TableColumn<Author, String> NameColumn;

    private ObservableList<Author> AuthorList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AuthorList = FXCollections.observableArrayList(
                new Author(1,"A",12,1,true),
                new Author(2,"B",20,2,false)

        );
        IDColumn.setCellValueFactory(new PropertyValueFactory<Author,Integer>("ID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("Name"));
        AgeColumn.setCellValueFactory(new PropertyValueFactory<Author,Integer>("Age"));
        NumColumn.setCellValueFactory(new PropertyValueFactory<Author,Integer>("Number"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<Author,Boolean>("Gender"));
        table.setItems(AuthorList);
    }


}
