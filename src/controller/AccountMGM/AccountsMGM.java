package controller.AccountMGM;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountsMGM implements Initializable {
    @FXML
    VBox Account = new VBox();
    @FXML
    VBox AccountApproval = new VBox();

    @FXML
    HBox title;

    @FXML
    Label lbID, lbPermission, lbUserName,lbRealName,lbAge,lbGender,lbExpirationDate,lbDateLeft,lbPermission2,lbUserName2,lbAge2,lbGender2,lbAccept ;

    @FXML
    private Button btnadd;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        List<Node> requestItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/AccountMGM/AccountItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                requestItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.Account.getChildren().addAll(requestItems);
        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbPermission.prefWidthProperty().bind(this.title.widthProperty().divide(8.8));
        this.lbUserName.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        this.lbRealName.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        this.lbAge.prefWidthProperty().bind(this.title.widthProperty().divide(8.8));
        this.lbGender.prefWidthProperty().bind(this.title.widthProperty().divide(8.8));
        this.lbExpirationDate.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        this.lbDateLeft.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        //Add borrow record items
        List<Node> recordItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/AccountMGM/AccountApprovalItem.fxml"));
                Node e = loader.load();
//                AccountApprovalItem itemController = loader.getController();
//                itemController.setList(recordItems);
//                itemController.setAccessibleText(String.valueOf(i));
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                recordItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.AccountApproval.getChildren().addAll(recordItems);
        this.lbPermission2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbUserName2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbAge2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbGender2.prefWidthProperty().bind(this.title.widthProperty().divide(8));
        this.lbAccept.prefWidthProperty().bind(this.title.widthProperty().divide(8));
        //this.lbColumn.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));

        tooltip();
    }
    //Tooltip btnadd
    public void tooltip(){
        Tooltip btnaddToolTip = new Tooltip("Add");
        btnadd.setTooltip(btnaddToolTip);
        btnaddToolTip.setStyle("-fx-background-color:white; -fx-text-fill:black");

    }
    public void removeApprovalItem(){

    }
}
