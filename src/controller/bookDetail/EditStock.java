package controller.bookDetail;

import Model.Quality;
import Model.StockBook;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.simple.JSONObject;
import sample.others.ConnectionAPIOption;
import sample.others.MediaTypeCollection;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EditStock implements Initializable {
    @FXML
    Label lbID, lbBorrow;
    @FXML
    JFXComboBox choices;
    @FXML
    Button btnUpdate;
    private StockBook stockBook;
    private List<String> quality;
    public void update() throws IOException {
        int i = 0;
        for(String item : quality){
            if(this.choices.getValue().equals(item)){
                i = quality.indexOf(item) + 1;
            }
        }
        StockBook stockBook = this.stockBook;
        stockBook.setQuality(new Quality(i));
        StringBuilder url = new StringBuilder(ConnectionAPIOption.stocksURL);
        url.append(this.stockBook.getId());
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject object = new JSONObject();
        object.put("stockbook", stockBook.toJSON());
        RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url.toString()).put(body).build();
        if(client.newCall(request).execute().code() == 200){
            close();
        }
    }
    @FXML
    void close() {
        Stage stage = (Stage) this.btnUpdate.getScene().getWindow();
        stage.close();
    }
    public void setStockBook(StockBook stockBook){
        this.stockBook = stockBook;
        this.lbID.setText(String.valueOf(this.stockBook.getId()));
        this.lbBorrow.setText(String.valueOf(this.stockBook.isBorrow()));
        this.choices.setValue(this.stockBook.getQuality().getSituation().toUpperCase());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quality = new ArrayList<>();
        quality.add("NEW");
        quality.add("GOOD");
        quality.add("OLD");
        quality.add("MISSING");
        quality.add("TO BE DEFORMED");
        choices.setItems(FXCollections.observableList(quality));
    }
}
