package controller.borrowTask;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.others.JFXAutoCompleteComboBoxListener;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBorrowRecordController implements Initializable {
    @FXML
    private JFXComboBox<String> readerchoice;
    ObservableList<String> Listreaderchoice = FXCollections.observableArrayList("New", "News", "Newss");
    @FXML
    private JFXComboBox<String> bookchoice;
    ObservableList<String> Listbookchoices= FXCollections.observableArrayList("New", "News", "Newss");
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void delSelected(ActionEvent event){
        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("Comfirmation");
        dg.setHeaderText("Comfirmation");
        dg.setContentText("Are you sure you want to delete the selected items?");
        dg.show();
    }
    public void delAll(ActionEvent event){
        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("Comfirmation");
        dg.setHeaderText("Comfirmation");
        dg.setContentText("Are you sure you want to delete all items?");
        dg.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readerchoice.setItems(Listreaderchoice);
        new JFXAutoCompleteComboBoxListener<>(readerchoice);
        bookchoice.setItems(Listbookchoices);
        new JFXAutoCompleteComboBoxListener<>(bookchoice);
    }
}
