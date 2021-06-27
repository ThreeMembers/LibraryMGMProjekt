package controller.AccountMGM;

import Model.Account;
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

    public void setUser(Account account) {
        this.lbID.setText(String.valueOf(account.getId()));
        this.lbPermission.setText(account.getIdPermission().getPosition());
        this.lbUserName.setText(account.getUsername());
        this.lbRealName.setText(account.getRealname());
        this.lbAge.setText(String.valueOf(account.getAge()));
        this.lbGender.setText(!account.isGender() ? "Male" : "Female");
        this.lbExpirationDate.setText(account.getExpirationDate().toString());
        this.lbDateLeft.setText(String.valueOf(account.getDateLeft()));
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
}