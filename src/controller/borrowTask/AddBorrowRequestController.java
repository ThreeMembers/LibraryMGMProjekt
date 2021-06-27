package controller.borrowTask;

import Model.*;
import com.jfoenix.controls.JFXComboBox;
import controller.accountDetailController;
import controller.addStocks.AddMultiStocksItemController;
import controller.addStocks.AddSingleStocksController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
import org.w3c.dom.Text;
import sample.others.ConnectionAPIOption;
import sample.others.JFXAutoCompleteComboBoxListener;
import sample.others.MediaTypeCollection;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddBorrowRequestController implements Initializable {
    @FXML
    private JFXComboBox<String> readerchoice;
    @FXML
    private JFXComboBox<String> bookchoice;

    @FXML
    TextField tfQuantity;
    @FXML
    Label lbNote;

    @FXML
    VBox container;

    List<Book> bookList;
    List<Account> readerList;

    List<DetailBorrowRequest> detailBorrowRequestList;

    @FXML
    Button btnCreate, btnAdd, btnDel, btnDelAll;

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void delSelected(ActionEvent event){
        if(container.getChildren().isEmpty()){
            return;
        }
        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("Comfirmation");
        dg.setHeaderText("Comfirmation");
        dg.setContentText("Are you sure you want to delete the selected items?");

        Optional<ButtonType> result = dg.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.CANCEL){
            return;
        }

        List<Node> temp = new ArrayList<>();
        List<DetailBorrowRequest> tempStocks = new ArrayList<>();
        int c = 0;
        for (Node i : this.container.getChildren()) {
            if (i.getAccessibleText().equals("checked")) {
                temp.add(i);
                DetailBorrowRequest s = detailBorrowRequestList.get(c);
                tempStocks.add(s);
            }
            c++;
        }
        this.container.getChildren().removeAll(temp);
        detailBorrowRequestList.removeAll(tempStocks);
    }
    public void delAll(ActionEvent event){
        if(container.getChildren().isEmpty()){
           return;
        }
        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("Comfirmation");
        dg.setHeaderText("Comfirmation");
        dg.setContentText("Are you sure you want to delete all items?");

        Optional<ButtonType> result = dg.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.CANCEL){
            return;
        }
        this.detailBorrowRequestList.clear();
        this.container.getChildren().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookList = new ArrayList<>();
        readerList = new ArrayList<>();
        detailBorrowRequestList = new ArrayList<>();

        readerchoice.setItems(FXCollections.observableList(loadAccount(readerList)));
        new JFXAutoCompleteComboBoxListener<>(readerchoice);
        bookchoice.setItems(FXCollections.observableList(AddSingleStocksController.loadBook(bookList)));
        new JFXAutoCompleteComboBoxListener<>(bookchoice);
    }

    public static List<String> loadAccount(List<Account> readerList){
        List<String> list = new ArrayList<>();
        String url = ConnectionAPIOption.accountURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                Account reader = ModelParse.getAccount(bookObject.toString());
                //System.out.println(reader.toString());
                if(reader.getIdPermission().getId() == 2 || reader.getIdPermission().getId() == 7){
                    if(reader.getDateLeft() > 0){
                        readerList.add(reader);
                        list.add(String.valueOf(reader.getId()));
                    }
                }

            }
            int code = response.code();
            response.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void addItemToList(){
        Book book = new Book();
        int quantity = Integer.parseInt(this.tfQuantity.getText());
        this.tfQuantity.clear();
        for (Book item : bookList) {
            if (item.getId() == Integer.parseInt(this.bookchoice.getValue())) {
                book.setId(item.getId());
                book.setName(item.getName());
            }
        }
        this.bookchoice.getEditor().clear();
        DetailBorrowRequest detailBorrowRequest = new DetailBorrowRequest();
        detailBorrowRequest.setBook(book.getId());
        detailBorrowRequest.setQuantity(quantity);
        detailBorrowRequestList.add(detailBorrowRequest);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/borrowTask/addBorrowRequestItem.fxml"));
            Node stockNode = loader.load();
            AddBorrowRequestItemController controller = loader.getController();
            controller.setDetail(book, quantity);
            this.container.getChildren().add(stockNode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public int createBorrowRequest(Account reader){
        String url = ConnectionAPIOption.borrowRequestURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        try {
            JSONObject jsonObject = new JSONObject();
            Date sendDate = new Date(LocalDate.now().toString());
            BorrowRequest borrowRequest = new BorrowRequest(reader, sendDate, false);
            jsonObject.put("borrowrequest", borrowRequest.toJSON());
            RequestBody body = RequestBody.create(String.valueOf(jsonObject), MediaTypeCollection.jsonType);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            int newID = Integer.parseInt(response.body().string());
            int code = response.code();
            System.out.println("Create borrow request: " + code);
//            response.close();
            if(code == 201){
                return newID;
            } else if(code == 304){
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean createDetail(int idRequest){
        for(DetailBorrowRequest item : detailBorrowRequestList){
            item.setRequest(idRequest);
        }
        String url = ConnectionAPIOption.detailBorrowRequestURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        try{
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            for(DetailBorrowRequest item : detailBorrowRequestList){
                array.add(item.toJSON());
            }
            object.put("detailborrowrequest", array);
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
    public void create() throws InterruptedException {
        this.btnAdd.setDisable(true);
        this.btnDelAll.setDisable(true);
        this.btnDel.setDisable(true);
        this.btnCreate.setDisable(true);
        Account reader = new Account();
        for (Account item : readerList) {
            if (item.getId() == Integer.parseInt(this.readerchoice.getValue())) {
                reader = item;
                break;
            }
        }
        int idRequest = createBorrowRequest(reader);
        if(idRequest > 0){
            if(createDetail(idRequest)){
                this.lbNote.setText("DONE");
                Thread.sleep(1500);
                Stage stage = (Stage) this.btnCreate.getScene().getWindow();
                stage.close();
            }
        }
        else {
            this.lbNote.setText("THIS READER CAN NOT CREATE MORE REQUESTS IN TODAY!");
        }
    }
}
