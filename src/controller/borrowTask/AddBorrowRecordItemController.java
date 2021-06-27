package controller.borrowTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AddBorrowRecordItemController {
    @FXML
    Label lbName, lbStock;
    @FXML
    CheckBox checkbox;

    @FXML
    Pane container;
    public void setElement(String name, int id) {
        this.lbName.setText(name);
        this.lbStock.setText(String.valueOf(id));
        this.container.setAccessibleText(String.valueOf(id));
    }

    public void checked(ActionEvent event) {
        if(this.checkbox.isSelected()){
            this.container.setAccessibleText("checked");
        }
        else {
            this.container.setAccessibleText(this.lbStock.getText());
        }
    }
}
