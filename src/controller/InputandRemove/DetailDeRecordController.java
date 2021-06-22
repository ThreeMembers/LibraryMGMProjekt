package controller.InputandRemove;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailDeRecordController implements Initializable {
    @FXML
    VBox DetailDelete = new VBox();

    @FXML
    HBox title;

    @FXML
    Label lbRecord,lbStock;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        List<Node> requestItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/DetailDeItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                requestItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.DetailDelete.getChildren().addAll(requestItems);
        this.lbStock.prefWidthProperty().bind(this.title.widthProperty().divide(2));
        this.lbRecord.prefWidthProperty().bind(this.title.widthProperty().divide(2));

    }
}