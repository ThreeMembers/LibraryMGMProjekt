package controller.borrowTask;

import Model.Book;
import Model.Borrow;
import Model.BorrowRequest;
import Model.Date;
import com.jfoenix.controls.JFXComboBox;
import controller.BookViewItemController;
import controller.NotFoundController;
import javafx.collections.FXCollections;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.AuthenticationClient;
import sample.others.ConnectionAPIOption;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
    @FXML
    JFXComboBox<String> createMode;

    List<BorrowRequest> borrowRequestList;
    List<Borrow> borrowList;

    int idBorrow, idRequest;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> modes = new ArrayList<>();
        modes.add("Borrow");
        modes.add("Request");
        createMode.setItems(FXCollections.observableList(modes));
        createMode.setValue("Borrow");
        createMode.getEditor().setStyle("-fx-text-fill: white");

        //Add borrow request items
        loadBorrowRequest();

        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbReader.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbDate.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        //Add borrow record items
        loadBorrows();

        this.lbID1.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbReader1.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));
        this.lbEmployee.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));
        this.lbDateCheck.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));
        this.lbDateReturn.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));

        tooltip();
    }
    public void refresh(){
        loadBorrowRequest();
        loadBorrows();

    }

    //Tooltip btnadd
    public void tooltip(){
        Tooltip btnaddToolTip = new Tooltip("Add new borrow record");
        btnadd.setTooltip(btnaddToolTip);
        btnaddToolTip.setStyle("-fx-background-color:white; -fx-text-fill:black");
    }

    //Open add borrow record
    public FXMLLoader openForm(String locate){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource(locate));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add Record");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            scene.setFill(Color.TRANSPARENT);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loader;
    }
    public void loadBorrows() {
        this.borrowRecordContent.getChildren().clear();
        String url = ConnectionAPIOption.borrowURL;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() == 204){
                System.out.println("Borrow code: " + response.code());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/NotFoundLabel.fxml"));
                Node node = loader.load();
                NotFoundController controller = loader.getController();
                controller.setContent("NO RECORDS YET!");
                this.borrowRecordContent.getChildren().add(node);
                return;
            }
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            borrowList = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());

            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                Borrow borrow = ModelParse.getBorrowRecord(bookObject.toString());
                borrowList.add(borrow);
            }
            int code = response.code();
            response.close();

            List<Node> nodeList = new ArrayList<>();
            int i = 0;
            for (Borrow borrow : borrowList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/borrowTask/borrowRecordItem.fxml"));
                Node node = loader.load();
                borrowRecordItemController itemController = loader.getController();
                itemController.setBorrow(borrow);
                if(i % 2 != 0){
                    itemController.setFill("#74b9ff");
                }
                if(borrow.getReturnDate().compareTo(new Date(LocalDate.now().toString())) == -1){
                    itemController.setFill("#ff4d4d");
                }
                nodeList.add(node);
                i++;
            }
            this.borrowRecordContent.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void loadBorrowRequest() {
        this.borrowRequestContent.getChildren().clear();
        String url = ConnectionAPIOption.borrowRequestURL;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() == 204){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/NotFoundLabel.fxml"));
                Node node = loader.load();
                NotFoundController controller = loader.getController();
                controller.setContent("NO RECORDS YET!");
                this.borrowRequestContent.getChildren().add(node);
                return;
            }
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            borrowRequestList = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());

            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                BorrowRequest record = ModelParse.getBorrowRequest(bookObject.toString());
                if(!record.isAuthen()){
                    borrowRequestList.add(record);
                }
            }
            int code = response.code();
            response.close();

            List<Node> nodeList = new ArrayList<>();
            int i = 0;
            for (BorrowRequest item : borrowRequestList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/borrowTask/borrowRequestItem.fxml"));
                Node node = loader.load();
                borrowRequestItemController itemController = loader.getController();
                itemController.setRequest(item);
                if(i % 2 != 0){
                    itemController.setFill("#74b9ff");
                }
                nodeList.add(node);
                i++;
            }
            this.borrowRequestContent.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void openAddForm() {
        if(this.createMode.getValue().equals("Borrow")){
            openForm("/view/borrowTask/addBorrowRecord.fxml");
        }
        else{
            openForm("/view/borrowTask/addBorrowRequest.fxml");
        }
    }
}
