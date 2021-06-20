package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private JFXComboBox<String> choice;
    ObservableList<String> choices = FXCollections.observableArrayList("Book", "Author", "Category");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice.setItems(choices);
        choice.setValue("Book");
    }
}
