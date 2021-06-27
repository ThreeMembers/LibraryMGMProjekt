package controller.borrowTask;

import Model.Borrow;
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
import java.util.Stack;

public class borrowRecordItemController implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbID,lbReader,lbEmployee,lbDateCheck,lbDateReturn;

    @FXML
    Button detailButton;

    Borrow borrow;
    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbReader.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.lbEmployee.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.lbDateCheck.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.lbDateReturn.prefWidthProperty().bind(this.container.widthProperty().divide(4.4));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
        this.lbID.setText(String.valueOf(borrow.getId()));
        this.lbReader.setText(borrow.getReader().getUsername());
        this.lbEmployee.setText(borrow.getEmployee().getUsername());
        this.lbDateCheck.setText(borrow.getCheckDate().toString());
        this.lbDateReturn.setText(borrow.getReturnDate().toString());
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
    public void openDetail() throws IOException {
        count ++;
        if (count == 2){
            count = 0;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/borrowTask/BorrowRecordDetail.fxml"));
            Parent root = loader.load();
            BorrowRecordDetailController controller = loader.getController();
            controller.setElement(this.borrow.getReader().getId(), this.borrow.getId());
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
