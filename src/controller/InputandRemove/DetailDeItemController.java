package controller.InputandRemove;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailDeItemController implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbRecord,lbStock;

    @FXML
    Button detailButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbStock.prefWidthProperty().bind(this.container.widthProperty().divide(2));
        this.lbRecord.prefWidthProperty().bind(this.container.widthProperty().divide(2));

        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());

    }
}
