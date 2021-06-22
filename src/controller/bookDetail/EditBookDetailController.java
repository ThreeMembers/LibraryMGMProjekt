package controller.bookDetail;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import sample.others.JFXAutoCompleteComboBoxListener;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditBookDetailController implements Initializable {
    @FXML
    private ImageView imageEditBookDetail;
    @FXML
    private Button btnOpenImage;
    // Create a FileChooser
    @FXML
    final FileChooser fc = new FileChooser();
    @FXML
    private JFXComboBox<String> choice;
    ObservableList<String> Listchoices = FXCollections.observableArrayList("New", "News", "Newss");
    @FXML
    private JFXComboBox<String> choice1;
    ObservableList<String> Listchoices1 = FXCollections.observableArrayList("New", "News", "Newss");
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void cancel(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void OK(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openFileChooser(ActionEvent event){
        fc.setTitle("My File Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.PNG", "*.jpg", "*.JPG"));
        File file = fc.showOpenDialog(null);

        if(file != null){
            imageEditBookDetail.setImage(new Image(String.valueOf(file.toURI())));
        }else{
            System.out.println("A file is invalid");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice.setItems(Listchoices);
        choice1.setItems(Listchoices1);
        new JFXAutoCompleteComboBoxListener<>(choice);
        new JFXAutoCompleteComboBoxListener<>(choice1);
    }
}
