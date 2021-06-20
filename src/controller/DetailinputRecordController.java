package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailinputRecordController implements Initializable {
    @FXML
    VBox Detailinput = new VBox();

    @FXML
    HBox title;

    @FXML
    Label lbQuantity,lbStock,lbReleaseYear;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        List<Node> requestItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/DetailinputItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                requestItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.Detailinput.getChildren().addAll(requestItems);
        this.lbStock.prefWidthProperty().bind(this.title.widthProperty().divide(2));
        this.lbQuantity.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbReleaseYear.prefWidthProperty().bind(this.title.widthProperty().divide(4));


    }
}
