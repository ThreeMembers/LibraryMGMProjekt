package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class NotFoundController {
    @FXML
    Label content;

    public void setContent(String content){
        this.content.setText(content);
    }
}
