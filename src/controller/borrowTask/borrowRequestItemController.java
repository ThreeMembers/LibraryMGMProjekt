package controller.borrowTask;

import Model.BorrowRequest;
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

    @FXML
    BorrowRequest borrowRequest;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.container.setMinHeight(40);
        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbReader.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.lbDate.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());
    }

    public void setRequest(BorrowRequest item) {
        this.borrowRequest = item;
        this.lbID.setText(String.valueOf(item.getId()));
        this.lbReader.setText(item.getReader().getUsername());
        this.lbDate.setText(item.getSendRequestDate().toString());
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
}
