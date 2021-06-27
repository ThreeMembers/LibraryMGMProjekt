package controller.borrowTask;

import Model.*;
import controller.addStocks.AddSingleStocksController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
import sample.others.MediaTypeCollection;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowRecordDetailController {
    @FXML
    Button btnAccept;
    @FXML
    Label lbReader;
    @FXML
    VBox container;

    private int idRecord;
    private int idReader;

    private List<StockBook> stockList;
    private List<StockBook> stockBookList;
    private List<DetailBorrow> list;

    public void accpetRequest() {
        if(deleteDetail()){
            if(deleteThisRequest()){
                if(updateStock(changeStateStock())){
                    Stage stage = (Stage) this.btnAccept.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setElement(int i, int id) throws IOException {
        this.idRecord = id;
        this.idReader = i;
        stockList = new ArrayList<>();
        list = new ArrayList<>();


        List<Account> reader = new ArrayList<>();
        AddBorrowRequestController.loadAccount(reader);
        for (Account item : reader){
            if(item.getId() == i){
                this.lbReader.setText(item.getUsername());
                break;
            }
        }
        load();
    }
    public void load() throws IOException {
        stockBookList = new ArrayList<>();
        AddBorrowRecordController.loadStock(stockBookList);

        list = loadDetail();
        for (DetailBorrow item : list){
            for(StockBook item2 : stockBookList){
                if(item.getStockBook() == item2.getId()){
                    this.stockList.add(item2);
                    break;
                }
            }
        }
        List<Node> nodeList = new ArrayList<>();
        int i = 0;
        for (DetailBorrow item : list) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/borrowTask/DetailRecordItem.fxml"));
            Node node = loader.load();
            DetailRecordItem itemController = loader.getController();
            itemController.setElement(stockList.get(i).getBook().getName(), item.getStockBook());
            if(i % 2 != 0){
                itemController.setFill("#74b9ff");
            }
            nodeList.add(node);
            i++;
        }
        this.container.getChildren().addAll(nodeList);
    }
    public List<DetailBorrow> loadDetail(){
        List<DetailBorrow> list = new ArrayList<>();

        String url = ConnectionAPIOption.detailBorrowsURL + this.idRecord;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(response.body().string());

            for (Object item : array) {
                JSONObject object = (JSONObject) item;
                DetailBorrow detailBorrow = new DetailBorrow(
                        Integer.parseInt(object.get("record").toString()),
                        Integer.parseInt(object.get("stockbook").toString())
                );
                list.add(detailBorrow);
            }
            int code = response.code();
            response.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean deleteDetail(){
        String url = ConnectionAPIOption.detailBorrowsURL + this.idRecord;
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject object = new JSONObject();
        object.put("clone", 1);
        RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url).delete(body).build();
        try{
            Response response = client.newCall(request).execute();
            int code = response.code();
            response.close();
            if(code == 200){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteThisRequest(){
        String url = ConnectionAPIOption.borrowURL + this.idRecord;
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject object = new JSONObject();
        object.put("clone", 1);
        RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url).delete(body).build();
        try{
            Response response = client.newCall(request).execute();
            int code = response.code();
            response.close();
            if(code == 200){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<StockBook> changeStateStock() {
        List<StockBook> temp = new ArrayList<>();
        for (DetailBorrow item : this.list) {
            for (StockBook item2 : stockBookList) {
                if (item.getStockBook() == item2.getId()) {
                    temp.add(item2);
                    break;
                }
            }
        }
        for (StockBook item : temp) {
            item.setBorrow(false);
        }
        return temp;
    }

    public boolean updateStock(List<StockBook> list) {
        String url = ConnectionAPIOption.stocksURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            boolean flag = true;
            for (StockBook item : list) {
                JSONObject object = new JSONObject();
                object.put("stockbook", item.toJSON());
                RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
                Request request = new Request.Builder().url(url + item.getId()).put(body).build();
                Response response = client.newCall(request).execute();
                int code = response.code();
                response.close();
                if(code != 200){
                    flag = false;
                }
            }
            if (!flag) {
                System.out.println("CAN NOT UPDATE!");
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
