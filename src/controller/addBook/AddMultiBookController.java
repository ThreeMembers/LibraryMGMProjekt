package controller.addBook;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.others.JFXAutoCompleteComboBoxListener;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMultiBookController implements Initializable {
    @FXML
    private ImageView imageAdd;
    @FXML
    final FileChooser fc = new FileChooser();
    @FXML
    private JFXComboBox<String> catechoices;
    ObservableList<String> Listcatechoices = FXCollections.observableArrayList("New", "News", "Newss");
    @FXML
    private JFXComboBox<String> authorchoices;
    ObservableList<String> Listauthorchoices = FXCollections.observableArrayList("New", "News", "Newss");
    @FXML
    private void openFileChooser(ActionEvent event){
        fc.setTitle("My File Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.PNG", "*.jpg", "*.JPG"));
        File file = fc.showOpenDialog(null);

        if(file != null){
            imageAdd.setImage(new Image(String.valueOf(file.toURI())));
        }else{
            System.out.println("A file is invalid");
        }
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catechoices.setItems(Listcatechoices);
        authorchoices.setItems(Listauthorchoices);
        new JFXAutoCompleteComboBoxListener<>(catechoices);
        new JFXAutoCompleteComboBoxListener<>(authorchoices);
    }
}
