package controller.InputandRemove;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InputRemoveController implements Initializable {
    @FXML
    VBox InputContainer = new VBox();
    @FXML
    VBox RemoveContainer = new VBox();
    @FXML
    HBox title;

    @FXML
    Label lbID,lbEmployee,lbDate,lbEmployee2,lbDate2,lbMessager;
    @FXML
    private Button btnadd;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        //Add Input
        List<Node> requestItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/InputandRemove/InputItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                requestItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.InputContainer.getChildren().addAll(requestItems);
        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbEmployee.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbDate.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));

        //Add Remove
        List<Node> recordItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/InputandRemove/RemoveItem.fxml"));
                Node e = loader.load();
//                      BookViewItemController bookViewItemController = loader.getController();
//                      bookViewItemController.setBook(temp);
                recordItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.RemoveContainer.getChildren().addAll(recordItems);
        this.lbEmployee2.prefWidthProperty().bind(this.title.widthProperty().divide(2));
        this.lbDate2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbMessager.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        //this.lbColumn.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));


        tooltip();
    }

    private void tooltip() {
        Tooltip btnaddToolTip = new Tooltip("Add");
        btnadd.setTooltip(btnaddToolTip);
        btnaddToolTip.setStyle("-fx-background-color:white; -fx-text-fill:black");
    }
}