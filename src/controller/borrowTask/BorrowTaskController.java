package controller.borrowTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowTaskController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    VBox borrowRequestContent = new VBox();
    @FXML
    VBox borrowRecordContent = new VBox();

    @FXML
    HBox title;

    @FXML
    Label lbID, lbReader, lbDate;
    @FXML
    Label lbID1, lbReader1, lbEmployee, lbDateCheck, lbDateReturn;
    @FXML
    private Button btnadd;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        List<Node> requestItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/borrowTask/borrowRequestItem.fxml"));
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
        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbReader.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbDate.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        //Add borrow record items
        List<Node> recordItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/borrowTask/borrowRecordItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                recordItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }

        //this.lbColumn.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));

        this.borrowRecordContent.getChildren().addAll(recordItems);
        this.lbID1.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbReader1.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));
        this.lbEmployee.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));
        this.lbDateCheck.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));
        this.lbDateReturn.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));

        tooltip();
        }
    //Tooltip btnadd
    public void tooltip(){
        Tooltip btnaddToolTip = new Tooltip("Add new borrow record");
        btnadd.setTooltip(btnaddToolTip);
        btnaddToolTip.setStyle("-fx-background-color:white; -fx-text-fill:black");
    }

    //Open add borrow record
    public void openAdd(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/borrowTask/addBorrowRecord.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add Borrow Record");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
