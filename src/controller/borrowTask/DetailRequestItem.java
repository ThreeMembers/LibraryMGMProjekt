package controller.borrowTask;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class DetailRequestItem {
    @FXML
    Label lbName, lbQuantity;

    public void setElement(String name, int quantity){
        this.lbName.setText(name);
        this.lbQuantity.setText(String.valueOf(quantity));
    }

    public void setFill(String s) {
    }
}
