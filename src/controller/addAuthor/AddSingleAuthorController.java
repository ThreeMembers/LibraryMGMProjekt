package controller.addAuthor;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.others.JFXAutoCompleteComboBoxListener;

import javax.naming.spi.InitialContextFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSingleAuthorController implements Initializable {
    @FXML
    private JFXComboBox<String> choice;
    ObservableList<String> choices = FXCollections.observableArrayList("Male", "Female", "Gay-lee");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice.setItems(choices);
        new JFXAutoCompleteComboBoxListener<>(choice);
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
