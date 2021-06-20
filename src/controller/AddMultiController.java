package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class AddMultiController {
    @FXML
    private ImageView imageAddMulti;
    @FXML
    final FileChooser fc = new FileChooser();
    @FXML
    private void openFileChooser(ActionEvent event){
        fc.setTitle("My File Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.PNG", "*.jpg", "*.JPG"));
        File file = fc.showOpenDialog(null);

        if(file != null){
            imageAddMulti.setImage(new Image(String.valueOf(file.toURI())));
        }else{
            System.out.println("A file is invalid");
        }
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
