package controller.addAuthor;

import Model.Author;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import sample.others.JFXAutoCompleteComboBoxListener;
import sample.others.MediaTypeCollection;

import javax.naming.spi.InitialContextFactory;
import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSingleAuthorController {

    @FXML
    TextArea taAuthor;
    @FXML
    TextField tfAge;
    @FXML
    CheckBox cbGender;
    @FXML
    Button btnAdd;

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private void addAuthor() {
        String name = this.taAuthor.getText();
        int age = Integer.parseInt(tfAge.getText());
        boolean gender = this.cbGender.isSelected();
        Author author = new Author(name, age, 0, gender);
        String url = ConnectionAPIOption.authorURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject object = new JSONObject();
        object.put("author", author.toJSON());
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
