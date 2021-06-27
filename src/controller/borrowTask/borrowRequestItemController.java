package controller.borrowTask;

import Model.BorrowRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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

    int count = 0;

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

    public void openDetail() throws IOException {
        count ++;
        if (count == 2){
            count = 0;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/borrowTask/BorrowRequestDetail.fxml"));
            Parent root = loader.load();
            BorrowRequestDetailController controller = loader.getController();
            controller.setElement(this.borrowRequest.getReader().getId(), this.borrowRequest.getId());
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        }
    }
    public void resetCount(){
        count = 0;
    }
}
