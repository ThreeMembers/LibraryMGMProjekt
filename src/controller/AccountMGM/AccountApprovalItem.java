package controller.AccountMGM;

import Model.AccountApproval;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccountApprovalItem implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbPermission, lbUserName, lbAge,lbGender;

    @FXML
    StackPane checkboxContainer;
    @FXML
    public CheckBox cbAccept;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbPermission.prefWidthProperty().bind(this.container.widthProperty().divide(4));
        this.lbUserName.prefWidthProperty().bind(this.container.widthProperty().divide(4));
        this.lbAge.prefWidthProperty().bind(this.container.widthProperty().divide(4));
        this.lbGender.prefWidthProperty().bind(this.container.widthProperty().divide(8));

        this.checkboxContainer.prefWidthProperty().bind(this.lbGender.widthProperty());

    }
    public void checked(){
        if(this.cbAccept.isSelected()){
            this.container.setAccessibleText("checked");
        }
        else{
            this.container.setAccessibleText("");
        }
    }
    public void setID(int id){
        this.lbPermission.setText(String.valueOf(id));

    }
    public String getAccessibleText(){
        return this.container.getAccessibleText();
    }
    public void setAccessibleText(String text){
        this.container.setAccessibleText(text);
    }
}