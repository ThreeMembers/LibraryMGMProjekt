package controller.addCate;

import Model.Author;
import Model.Category;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONObject;
import sample.others.ConnectionAPIOption;
import sample.others.MediaTypeCollection;

import java.io.IOException;

public class AddSingleCateController {
    @FXML
    TextField tfName;
    @FXML
    TextArea taDes;
    @FXML
    Button btnAdd;
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private void addCate() {
        String name = this.tfName.getText();
        String des = this.taDes.getText();
        Category cate = new Category(name, des);
        String url = ConnectionAPIOption.categoryURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject object = new JSONObject();
        object.put("category", cate.toJSON());
        RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url).post(body).build();
        try{
            Response response = client.newCall(request).execute();
            int code = response.code();
            response.close();
            if(code == 201){
                Stage stage = (Stage) this.btnAdd.getScene().getWindow();
                stage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
