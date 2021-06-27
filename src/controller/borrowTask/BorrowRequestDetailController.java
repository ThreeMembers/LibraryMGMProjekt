package controller.borrowTask;

import Model.*;
import controller.InputandRemove.InputItemController;
import controller.accountDetailController;
import controller.addStocks.AddSingleStocksController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import sample.others.FileWorking;
import sample.others.MediaTypeCollection;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowRequestDetailController {
    @FXML
    Button btnAccept, btnDelete;
    @FXML
    Label lbReader;
    @FXML
    VBox container;

    private int idRequest;
    private int idReader;

    Account readerA = new Account();

    List<DetailBorrowRequest> list;
    List<DetailBorrow> detailBorrowList;
    List<StockBook> stockBookList;

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accpetRequest() {
        stockBookList = new ArrayList<>();
        AddBorrowRecordController.loadStock(stockBookList);
        List<StockBook> stockSelectedList = new ArrayList<>();

        for(DetailBorrowRequest item : list){
            int count = item.getQuantity();
            for(StockBook stockBook : stockBookList){
                if(item.getBook() == stockBook.getBook().getId()){
                    if(!stockBook.isBorrow() && count > 0){
                        stockSelectedList.add(stockBook);
                        count --;
                    }
                }
            }
        }

        List<DetailBorrow> detailBorrowList = new ArrayList<>();
        for (StockBook stockBook : stockSelectedList){
            DetailBorrow detailBorrow = new DetailBorrow();
            detailBorrow.setStockBook(stockBook.getId());
            detailBorrowList.add(detailBorrow);
        }



        int idRecord = createBorrowRecord(readerA);
        if (idRecord > 0) {
            if (createDetail(idRecord, detailBorrowList)) {
                if (updateStock(changeStateStock(stockBookList, detailBorrowList))) {
                    if(deleteDetail()){
                        if (deleteThisRequest()){
                            Stage stage = (Stage) this.btnAccept.getScene().getWindow();
                            stage.close();
                        }
                    }
                }
            }
        }
    }

    public void setElement(int id, int idRequest) throws IOException {
        this.idRequest = idRequest;
        this.idReader = id;
        list = new ArrayList<>();

        detailBorrowList = new ArrayList<>();

        List<Account> reader = new ArrayList<>();
        AddBorrowRequestController.loadAccount(reader);
        for (Account item : reader){
            if(item.getId() == id){
                this.lbReader.setText(item.getUsername());
                readerA = item;
                break;
            }
        }
        load();
    }
    public void load() throws IOException {
        List<Book> bookList = new ArrayList<>();
        AddSingleStocksController.loadBook(bookList);
        list = loadDetail();
        List<String> temp = new ArrayList<>();
        for (DetailBorrowRequest item : list){
            for(Book item2 : bookList){
                if(item.getBook() == item2.getId()){
                    temp.add(item2.getName());
                    break;
                }
            }
        }
        List<Node> nodeList = new ArrayList<>();
        int i = 0;
        for (DetailBorrowRequest item : list) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/borrowTask/DetailRequestItem.fxml"));
            Node node = loader.load();
            DetailRequestItem itemController = loader.getController();
            itemController.setElement(temp.get(i), item.getQuantity());
            if(i % 2 != 0){
                itemController.setFill("#74b9ff");
            }
            nodeList.add(node);
            i++;
        }
        this.container.getChildren().addAll(nodeList);
    }
    public List<DetailBorrowRequest> loadDetail(){
        List<DetailBorrowRequest> list = new ArrayList<>();
        String url = ConnectionAPIOption.detailBorrowRequestURL + this.idRequest;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(response.body().string());

            for (Object item : array) {
                JSONObject object = (JSONObject) item;
                DetailBorrowRequest detailBorrowRequest = new DetailBorrowRequest(
                        Integer.parseInt(object.get("request").toString()),
                        Integer.parseInt(object.get("book").toString()),
                        Integer.parseInt(object.get("quantity").toString())
                );
                list.add(detailBorrowRequest);
            }
            int code = response.code();
            response.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Account getEmp() throws ParseException {
        String dataInFile = FileWorking.readFromFile();
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(dataInFile);
        object = (JSONObject) parser.parse(object.get("permission").toString());
        Permission permission = new Permission(
                Integer.parseInt(object.get("id").toString()),
                object.get("position").toString()
        );
        String idString = accountDetailController.getID();
        int idEmp = Integer.parseInt(idString);
        Account emp = new Account();
        emp.setId(idEmp);
        emp.setIdPermission(permission);
        return emp;
    }
    public int createBorrowRecord(Account reader) {
        String url = ConnectionAPIOption.borrowURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            JSONObject jsonObject = new JSONObject();
            Date checkDate = new Date(LocalDate.now().toString());
            Borrow borrowRecord = new Borrow(reader, getEmp(), checkDate);
            jsonObject.put("borrow", borrowRecord.toJSON());
            RequestBody body = RequestBody.create(String.valueOf(jsonObject), MediaTypeCollection.jsonType);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            int newID = Integer.parseInt(response.body().string());
            System.out.println(response.code() + "---" + newID);

            System.out.println(newID);
            int code = response.code();
//            response.close();
            if(code == 201){
                return newID;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean createDetail(int idRecord, List<DetailBorrow> detailBorrowList) {
        for (DetailBorrow item : detailBorrowList) {
            item.setRecord(idRecord);
        }
        String url = ConnectionAPIOption.detailBorrowsURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            for (DetailBorrow item : detailBorrowList) {
                array.add(item.toJSON());
            }
            object.put("detailborrow", array);
            RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            int code = response.code();
            response.close();
            if(code == 201){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<StockBook> changeStateStock(List<StockBook> stockList, List<DetailBorrow> detailBorrowList) {
        List<StockBook> temp = new ArrayList<>();
        for (DetailBorrow item : detailBorrowList) {
            for (StockBook item2 : stockList) {
                if (item.getStockBook() == item2.getId()) {
                    temp.add(item2);
                    break;
                }
            }
        }
        for (StockBook item : temp) {
            item.setBorrow(true);
        }
        return temp;
    }

    public boolean updateStock(List<StockBook> list) {
        StringBuilder url = new StringBuilder(ConnectionAPIOption.stocksURL);
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            boolean flag = true;
            for (StockBook item : list) {
                JSONObject object = new JSONObject();
                object.put("stockbook", item.toJSON());
                RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
                url.append(item.getId());
                Request request = new Request.Builder().url(url.toString()).put(body).build();
                Response response = client.newCall(request).execute();
                int code = response.code();
                response.close();
                if(code != 200){
                    flag = false;
                    break;
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

    public boolean deleteDetail(){
        StringBuilder url = new StringBuilder(ConnectionAPIOption.detailBorrowRequestURL);
        url.append(this.idRequest);
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject object = new JSONObject();
        object.put("clone", 1);
        RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url.toString()).delete(body).build();
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
        String url = ConnectionAPIOption.borrowRequestURL + this.idRequest;
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
//    @FXML
//    public void delete(){
//        if(deleteDetail()){
//            if(deleteThisRequest()){
//                if(updateStock(changeStateStock(stockBookList))){
//                    Stage stage = (Stage) this.btnAccept.getScene().getWindow();
//                    stage.close();
//                }
//            }
//        }
//    }
}
