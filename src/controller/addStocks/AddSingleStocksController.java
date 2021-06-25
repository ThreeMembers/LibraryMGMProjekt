package controller.addStocks;

import Model.*;
import com.jfoenix.controls.JFXComboBox;
import controller.accountDetailController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.ConnectionAPIOption;
import sample.others.JFXAutoCompleteComboBoxListener;
import sample.others.MediaTypeCollection;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddSingleStocksController implements Initializable {
    @FXML
    private JFXComboBox<String> choice;
    ObservableList<String> choices = FXCollections.observableArrayList("New", "Good", "Normal", "Old");
    @FXML
    JFXComboBox<String> bookChoices;
    @FXML
    TextField tfYear;
    @FXML
    Button btnAdd;

    private int lastID;
    private List<Book> bookList;
    private List<Quality> qualityList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookList = new ArrayList<>();
        qualityList = new ArrayList<>();
        choice.setItems(FXCollections.observableList(loadQuality(qualityList)));
        bookChoices.setItems(FXCollections.observableList(loadBook(bookList)));
        new JFXAutoCompleteComboBoxListener<>(choice);
        new JFXAutoCompleteComboBoxListener<>(bookChoices);
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setLastID(int idStock) {
        this.lastID = idStock;
    }

    public static List<String> loadBook(List<Book> bookList){
        List<String> list = new ArrayList<>();
        String url = ConnectionAPIOption.bookURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                Book book = ModelParse.getBook(bookObject.toString());
                bookList.add(book);
                list.add(book.getName());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<String> loadQuality(List<Quality> qualityList){
        List<String> list = new ArrayList<>();
        String url = ConnectionAPIOption.qualityURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                Quality quality = ModelParse.getQuality(bookObject.toString());
                qualityList.add(quality);
                list.add(quality.getSituation().toUpperCase());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void createStock(){
        this.btnAdd.setDisable(true);
        Quality quality = new Quality();
        Book book = new Book();
        for (Quality item: qualityList) {
            if(item.getSituation().toUpperCase().equals(this.choice.getValue())){
                quality.setId(item.getId());
            }
        }
        for (Book item : bookList){
            if(item.getName().equals(this.bookChoices.getValue())){
                book.setId(item.getId());
            }
        }
        StockBook stockBook = new StockBook(book, quality, Integer.parseInt(this.tfYear.getText()), false);
        JSONObject object = new JSONObject();
        object.put("stockbook", stockBook.toJSON());
        String url = ConnectionAPIOption.stocksURL;

//        createPostInputRequest();

        OkHttpClient client = ConnectionAPIOption.getClient();
        RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url).post(body).build();
        try{
            Response response = client.newCall(request).execute();
            if(response.code() == 201){
                int newIDStock = Integer.parseInt(response.body().string());
                int newIDInput = createPostInputRequest();
                if(newIDInput > 0){
                    if(createPostInputDetailRequest(newIDInput, newIDStock)){
                        this.btnAdd.setText("DONE");
                        Thread.sleep(1500);
                        Stage stage = (Stage) this.btnAdd.getScene().getWindow();
                        stage.close();
                    }
                    else {
                        this.btnAdd.setText("ERORR D");
                    }
                }
                else {
                    this.btnAdd.setText("ERORR I");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int createPostInputRequest(){
        String url = ConnectionAPIOption.inputURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            JSONObject jsonObject = new JSONObject();
            String id = accountDetailController.getID();
            int curUserID = Integer.parseInt(id);
//            System.out.println(curUserID + " ---- " + id);
            Date inputDate = new Date(LocalDate.now().toString());
            Account employee = new Account();
            employee.setId(curUserID);
            Input input = new Input(employee, inputDate);
            jsonObject.put("input", input.toJSON());
            RequestBody body = RequestBody.create(String.valueOf(jsonObject), MediaTypeCollection.jsonType);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            if(response.code() == 201){
                int newID = Integer.parseInt(response.body().string());
                return newID;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean createPostInputDetailRequest(int newInput, int newStock){
        String url = ConnectionAPIOption.detailInputURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            DetailInput detailInput = new DetailInput(newInput, newStock);
            array.add(detailInput.toJSON());
            jsonObject.put("detailinput", array);
            RequestBody body = RequestBody.create(String.valueOf(jsonObject), MediaTypeCollection.jsonType);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            if(response.code() == 201){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
