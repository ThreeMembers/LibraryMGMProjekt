package controller.borrowTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class borrowRequestItemController implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbID,lbReader,lbDate;
    @FXML
    Button detailButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.container.setMinHeight(40);
        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbReader.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.lbDate.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());
    }
}
