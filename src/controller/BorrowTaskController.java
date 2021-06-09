package controller;

import Model.Account;
import Model.Book;
import Model.BorrowRequest;
import Model.Date;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowTaskController implements Initializable {
    @FXML
    VBox borrowRequestContent = new VBox();
    @FXML
    VBox borrowRecordContent = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        List<Node> requestItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/borrowRequestItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                requestItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.borrowRequestContent.getChildren().addAll(requestItems);
        //Add borrow record items
        List<Node> recordItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/borrowRecordItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                recordItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.borrowRecordContent.getChildren().addAll(recordItems);
    }
}
