package controller.InputandRemove;

import Model.BorrowRequest;
import Model.Input;
import controller.NotFoundController;
import controller.borrowTask.borrowRequestItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.ConnectionAPIOption;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InputRemoveController implements Initializable {
    @FXML
    VBox InputContainer = new VBox();
    @FXML
    VBox RemoveContainer = new VBox();
    @FXML
    HBox title;

    @FXML
    Label lbID,lbEmployee,lbDate,lbEmployee2,lbDate2,lbMessager;
    @FXML
    private Button btnadd;

    List<Input> inputList;
    @FXML
    VBox inputContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        //Add Input
        loadInputRecord();

        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbEmployee.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbDate.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));

        //Add Remove
        List<Node> recordItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/InputandRemove/RemoveItem.fxml"));
                Node e = loader.load();
//                      BookViewItemController bookViewItemController = loader.getController();
//                      bookViewItemController.setBook(temp);
                recordItems.add(e);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.RemoveContainer.getChildren().addAll(recordItems);
        this.lbEmployee2.prefWidthProperty().bind(this.title.widthProperty().divide(2));
        this.lbDate2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbMessager.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        //this.lbColumn.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));


        tooltip();
    }

    private void tooltip() {
        Tooltip btnaddToolTip = new Tooltip("Add");
        btnadd.setTooltip(btnaddToolTip);
        btnaddToolTip.setStyle("-fx-background-color:white; -fx-text-fill:black");
    }
    public void loadInputRecord(){
        String url = ConnectionAPIOption.inputURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            if(response.code() == 204){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/NotFoundLabel.fxml"));
                Node node = loader.load();
                NotFoundController controller = loader.getController();
                controller.setContent("NO RECORDS YET!");
                this.inputContainer.getChildren().add(node);
                return;
            }

            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            inputList = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());

            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                Input record = ModelParse.getInput(bookObject.toString());
                inputList.add(record);
            }

            List<Node> nodeList = new ArrayList<>();
            int i = 0;
            for (Input item : inputList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/InputandRemove/InputItem.fxml"));
                Node node = loader.load();
                InputItemController itemController = loader.getController();
                itemController.setRequest(item);
                if(i % 2 != 0){
                    itemController.setFill("#74b9ff");
                }
                nodeList.add(node);
                i++;
            }
            this.inputContainer.getChildren().addAll(nodeList);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}