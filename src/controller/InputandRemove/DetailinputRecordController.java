package controller.InputandRemove;

import Model.DetailInput;
import Model.Input;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

public class DetailinputRecordController implements Initializable {
    @FXML
    VBox Detailinput = new VBox();

    @FXML
    HBox title;

    @FXML
    Label lbStock, lbID;

    private Input input;
    private List<DetailInput> detailInputs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.lbStock.prefWidthProperty().bind(this.title.widthProperty());
    }

    public void setInput(Input record) {
        this.input = record;
        this.lbID.setText(String.valueOf(record.getId()));
        loadDetail();
    }
    public void loadDetail(){
        String url = ConnectionAPIOption.detailInputURL + input.getId();
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            detailInputs = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());

            for (Object item : array) {
                JSONObject object = (JSONObject) item;
                DetailInput detailInput = new DetailInput(
                        Integer.parseInt(object.get("record").toString()),
                        Integer.parseInt(object.get("stock").toString())
                );
                detailInputs.add(detailInput);
            }

            List<Node> nodeList = new ArrayList<>();
            int i = 0;
            for (DetailInput item : detailInputs) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/InputandRemove/DetailinputItem.fxml"));
                Node node = loader.load();
                DetailinputItemController itemController = loader.getController();
                itemController.setInput(item);
                if(i % 2 != 0){
                    itemController.setFill("tomato");
                }
                nodeList.add(node);
                i++;
            }
            this.Detailinput.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow() {
        Stage stage = (Stage) lbStock.getScene().getWindow();
        stage.close();
        return;
    }
}
