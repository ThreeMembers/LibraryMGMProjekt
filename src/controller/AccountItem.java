package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountItem implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbID,lbPermission, lbUserName,lbRealName,lbAge,lbGender,lbExpirationDate,lbDateLeft;

    @FXML
    Button detailButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbPermission.prefWidthProperty().bind(this.container.widthProperty().divide(8.8));
        this.lbUserName.prefWidthProperty().bind(this.container.widthProperty().divide(7.04));
        this.lbRealName.prefWidthProperty().bind(this.container.widthProperty().divide(7.04));
        this.lbAge.prefWidthProperty().bind(this.container.widthProperty().divide(8.8));
        this.lbGender.prefWidthProperty().bind(this.container.widthProperty().divide(8.8));
        this.lbExpirationDate.prefWidthProperty().bind(this.container.widthProperty().divide(7.04));
        this.lbDateLeft.prefWidthProperty().bind(this.container.widthProperty().divide(7.04));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());

    }
}
