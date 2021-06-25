package controller.InputandRemove;

import Model.Input;
import controller.bookDetail.bookDetailController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class InputItemController  implements Initializable {
    @FXML
    StackPane container;
    @FXML
    Label lbID, lbEmployee, lbDate;

    @FXML
    Button detailButton;

    private Input record;

    private int clickCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        container.setMinHeight(40);
        this.lbID.prefWidthProperty().bind(this.container.widthProperty().divide(11));
        this.lbEmployee.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.lbDate.prefWidthProperty().bind(this.container.widthProperty().divide(2.2));
        this.detailButton.prefWidthProperty().bind(this.container.widthProperty());

    }

    public void setRequest(Input item) {
        this.record = item;
        this.lbID.setText(String.valueOf(item.getId()));
        this.lbEmployee.setText(item.getEmployee().getUsername());
        this.lbDate.setText(item.getInputDate().toString());
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
    public void openDetail(){
        clickCount++;
        if(clickCount == 2) {
            clickCount = 0;
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/InputandRemove/DetailinputRecord.fxml"));
                Parent parent = loader.load();
                DetailinputRecordController inputDetail = loader.getController();
                inputDetail.setInput(this.record);
                Scene scene = new Scene(parent);
                scene.setFill(Color.TRANSPARENT);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public void resetClick(MouseEvent mouseEvent){
        clickCount = 0;
    }

}
