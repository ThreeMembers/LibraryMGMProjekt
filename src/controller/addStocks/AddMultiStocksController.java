package controller.addStocks;

import Model.Book;
import Model.DetailInput;
import Model.Quality;
import Model.StockBook;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.others.ConnectionAPIOption;
import sample.others.JFXAutoCompleteComboBoxListener;
import sample.others.MediaTypeCollection;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddMultiStocksController implements Initializable {
    @FXML
    JFXComboBox<String> bookChoices;
    @FXML
    TextField tfYear;
    @FXML
    Button btnCreate;

    @FXML
    VBox container;

    private List<Book> bookList;
    private List<Quality> qualityList;

    private List<StockBook> stockBookList;
    private List<DetailInput> detailInputList;

    @FXML
    private JFXComboBox<String> choice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stockBookList = new ArrayList<>();
        bookList = new ArrayList<>();
        qualityList = new ArrayList<>();
        choice.setItems(FXCollections.observableList(AddSingleStocksController.loadQuality(qualityList)));
        bookChoices.setItems(FXCollections.observableList(AddSingleStocksController.loadBook(bookList)));
        new JFXAutoCompleteComboBoxListener<>(choice);
        new JFXAutoCompleteComboBoxListener<>(bookChoices);
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addItemToList() {
        Quality quality = new Quality();
        Book book = new Book();
        for (Quality item : qualityList) {
            if (item.getSituation().toUpperCase().equals(this.choice.getValue())) {
                quality.setId(item.getId());
                quality.setSituation(item.getSituation());
            }
        }
        for (Book item : bookList) {
            if (item.getName().equals(this.bookChoices.getValue())) {
                book.setId(item.getId());
                book.setName(item.getName());
            }
        }
        StockBook stockBook = new StockBook(book, quality, Integer.parseInt(this.tfYear.getText()), false);
        stockBookList.add(stockBook);
        this.choice.getEditor().clear();
        this.bookChoices.getEditor().clear();
        this.tfYear.clear();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/addStocks/addMultiStocksItem.fxml"));
            Node stockNode = loader.load();
            AddMultiStocksItemController controller = loader.getController();
            controller.setStock(stockBook);
            this.container.getChildren().add(stockNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteChecked() {
        List<Node> temp = new ArrayList<>();
        List<StockBook> tempStocks = new ArrayList<>();
        int c = 0;
        for (Node i : this.container.getChildren()) {
            if (i.getAccessibleText().equals("checked")) {
                temp.add(i);
                StockBook s = stockBookList.get(c);
                tempStocks.add(s);
            }
            c++;
        }
        this.container.getChildren().removeAll(temp);
        stockBookList.removeAll(tempStocks);
    }

    public void deleteAll() {
        this.container.getChildren().clear();
        this.stockBookList.clear();
    }

    public void createRecord() {
        String url = ConnectionAPIOption.stocksURL;
        String detailurl = ConnectionAPIOption.detailInputURL;
        int newIDInput = AddSingleStocksController.createPostInputRequest();
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            for(StockBook item : stockBookList){
                JSONObject object = new JSONObject();
                object.put("stockbook", item.toJSON());
                RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
                Request request = new Request.Builder().url(url).post(body).build();
                Response response = client.newCall(request).execute();
                if(response.code() == 201){
                    int newIDStock = Integer.parseInt(response.body().string());
                    DetailInput detailInput = new DetailInput(newIDInput, newIDStock);
                    detailInputList.add(detailInput);
                }
            }
            JSONObject detailObject = new JSONObject();
            JSONArray array = new JSONArray();
            for(DetailInput item : detailInputList){
                array.add(item.toJSON());
            }
            detailObject.put("detailinput", array);
            RequestBody body = RequestBody.create(String.valueOf(detailObject), MediaTypeCollection.jsonType);
            Request request = new Request.Builder().url(detailurl).post(body).build();
            Response response = client.newCall(request).execute();
            if(response.code() == 201){
                this.btnCreate.setText("DONE");
                Thread.sleep(1500);
                Stage stage = (Stage) this.btnCreate.getScene().getWindow();
                stage.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
