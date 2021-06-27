package controller.borrowTask;

import Model.*;
import com.jfoenix.controls.JFXComboBox;
import controller.accountDetailController;
import controller.addStocks.AddSingleStocksController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
import sample.others.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddBorrowRecordController implements Initializable {
    @FXML
    private JFXComboBox<String> readerchoice;
    @FXML
    private JFXComboBox<String> bookchoice;
    @FXML
    private JFXComboBox<String> stockchoice;
    @FXML
    Button btnAdd, btnCreate, btnDel, btnDelAll, btnFilter;

    @FXML
    VBox container;

    List<Book> bookList;
    List<Account> readerList;
    List<StockBook> stockBookList;
    List<String> stockIDs;

    List<DetailBorrow> detailBorrowList;

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void delSelected(ActionEvent event) {
        if (container.getChildren().isEmpty()) {
            return;
        }

        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("Comfirmation");
        dg.setHeaderText("Comfirmation");
        dg.setContentText("Are you sure you want to delete the selected items?");

        Optional<ButtonType> result = dg.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            return;
        }

        List<Node> temp = new ArrayList<>();
        List<DetailBorrow> tempStocks = new ArrayList<>();
        int c = 0;
        for (Node i : this.container.getChildren()) {
            if (i.getAccessibleText().equals("checked")) {
                temp.add(i);
                DetailBorrow s = detailBorrowList.get(c);
                tempStocks.add(s);
            }
            c++;
        }
        this.container.getChildren().removeAll(temp);
        detailBorrowList.removeAll(tempStocks);
    }

    public void delAll(ActionEvent event) {
        if (container.getChildren().isEmpty()) {
            return;
        }

        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("Comfirmation");
        dg.setHeaderText("Comfirmation");
        dg.setContentText("Are you sure you want to delete all items?");

        Optional<ButtonType> result = dg.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            return;
        }
        this.detailBorrowList.clear();
        this.container.getChildren().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readerList = new ArrayList<>();
        bookList = new ArrayList<>();
        stockBookList = new ArrayList<>();
        detailBorrowList = new ArrayList<>();

        stockIDs = new ArrayList<>();
        stockIDs = loadStock(stockBookList);

        readerchoice.setItems(FXCollections.observableList(AddBorrowRequestController.loadAccount(readerList)));
        new JFXAutoCompleteComboBoxListener<>(readerchoice);
        bookchoice.setItems(FXCollections.observableList(AddSingleStocksController.loadBook(bookList)));
        new JFXAutoCompleteComboBoxListener<>(bookchoice);
    }

    public static List<String> loadStock(List<StockBook> stockBookList) {

        List<String> temp = new ArrayList<>();

        String url = ConnectionAPIOption.stocksURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try {
            //nhan response tu server
            Response response = client.newCall(request).execute();

            //bat dau phan tich body cua response
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject stockBookObject = (JSONObject) item;
                StockBook stockBook = ModelParse.getStockBook(stockBookObject.toString());
                temp.add(String.valueOf(stockBook.getId()));
                stockBookList.add(stockBook);
            }
            int code = response.code();
            response.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void filter() {
        if (this.bookchoice.getValue() == null) {
            return;
        }
        int id = 0;
        for (Book item : bookList) {
            if (this.bookchoice.getValue().equals(item.getName())) {
                id = item.getId();
                break;
            }
        }
        stockIDs.clear();
        for (StockBook item : stockBookList)
            if (id == item.getBook().getId())
                if (!item.isBorrow())
                    stockIDs.add(String.valueOf(item.getId()));

        stockchoice.setItems(FXCollections.observableList(stockIDs));
        new JFXAutoCompleteComboBoxListener(stockchoice);
    }

    public void addItemToList() {
        String name = this.bookchoice.getValue();
        this.bookchoice.getEditor().clear();

        int id = Integer.parseInt(this.stockchoice.getValue());

        DetailBorrow detailBorrow = new DetailBorrow();
        detailBorrow.setStockBook(id);

        this.detailBorrowList.add(detailBorrow);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/borrowTask/AddBorrowRecordItem.fxml"));
            Node node = loader.load();
            AddBorrowRecordItemController controller = loader.getController();
            controller.setElement(name, id);
            this.container.getChildren().add(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            System.out.println(response.code());
            if (response.code() == 201) {
                int newID = Integer.parseInt(response.body().string());
                return newID;
            }
            int code = response.code();
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean createDetail(int idRequest) {
        for (DetailBorrow item : detailBorrowList) {
            item.setRecord(idRequest);
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
            if (code == 201) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void create() throws InterruptedException {
        this.btnAdd.setDisable(true);
        this.btnDelAll.setDisable(true);
        this.btnDel.setDisable(true);
        this.btnCreate.setDisable(true);
        Account reader = new Account();
        for (Account item : readerList) {
            if (item.getId() == Integer.parseInt(this.readerchoice.getValue())) {
                reader.setId(item.getId());
                reader.setUsername(item.getUsername());
            }
        }
        int idRequest = createBorrowRecord(reader);
        if (idRequest > 0) {
            if (createDetail(idRequest)) {
                if (updateStock(changeStateStock())) {
                    this.btnCreate.setText("DONE");
                    Thread.sleep(1500);
                    Stage stage = (Stage) this.btnCreate.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    public List<StockBook> changeStateStock() {
        List<StockBook> temp = new ArrayList<>();
        for (DetailBorrow item : detailBorrowList) {
            for (StockBook item2 : stockBookList) {
                if (item.getStockBook() == item2.getId()) {
                    temp.add(item2);
                }
            }
        }
        for (StockBook item : temp) {
            item.setBorrow(true);
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
                if (code != 200) {
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
