package controller;

import Model.Book;
import Model.StockBook;
import com.jfoenix.controls.JFXComboBox;
import controller.bookDetail.BookDetailItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.AuthenticationClient;
import sample.others.ConnectionAPIOption;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeView implements Initializable {

    public static String bookURL = "http://localhost:8080/LibraryRestAPI/webapi/books/";
    public static String authorURL = "http://localhost:8080/LibraryRestAPI/webapi/authors/";


    @FXML
    VBox bookContent = new VBox();
    @FXML
    VBox stockBookContent = new VBox();
    @FXML
    private JFXComboBox<String> catechoices;
    ObservableList<String> cateListchoices = FXCollections.observableArrayList("Single", "Multi");
    @FXML
    private JFXComboBox<String> authorchoices;
    ObservableList<String> authorListchoices = FXCollections.observableArrayList("Book", "Author", "Category", "Stock");

    @FXML
    HBox title;

    @FXML
    Label lbID, lbName, lbAuthor, lbCategory, lbstocks;
    @FXML
    Label lbBook, lbQuality, lbRelease, lbIsBorrow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catechoices.setItems(cateListchoices);
        catechoices.setValue("Single");
        authorchoices.setItems(authorListchoices);
        authorchoices.setValue("Book");

        loadBooks();


        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbName.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbAuthor.prefWidthProperty().bind(this.title.widthProperty().divide(5));
        this.lbCategory.prefWidthProperty().bind(this.title.widthProperty().divide(5));
        this.lbstocks.prefWidthProperty().bind(this.title.widthProperty().divide(18.3));



        List<Node> stockBookItems = new ArrayList<>();
        for(int i = 0; i < 50; i++){

            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/StockBookViewItem.fxml"));
                Node e = loader.load();
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                stockBookItems.add(e);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.stockBookContent.getChildren().addAll(stockBookItems);

        this.lbBook.prefWidthProperty().bind(this.title.widthProperty().divide(1.9));
        this.lbQuality.prefWidthProperty().bind(this.title.widthProperty().divide(6.2));
        this.lbRelease.prefWidthProperty().bind(this.title.widthProperty().divide(6.2));
        this.lbIsBorrow.prefWidthProperty().bind(this.title.widthProperty().divide(6.2));
    }

    public void loadBooks() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(bookURL).build();
        try {
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            List<Book> bookList = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                Book book = ModelParse.getBook(bookObject.toString());
                bookList.add(book);
            }

            List<Node> nodeList = new ArrayList<>();
            int i = 0;
            for (Book book : bookList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/BookViewItem.fxml"));
                Node node = loader.load();
                BookViewItemController itemController = loader.getController();
                itemController.setBook(book);
                if(i % 2 != 0){
                    itemController.setFill("#74b9ff");
                }
                nodeList.add(node);
                i++;
            }
            this.bookContent.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void loadStocks(){
        String url = ConnectionAPIOption.stocksFilterBookURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try {
            //nhan response tu server
            Response response = client.newCall(request).execute();

            //bat dau phan tich body cua response
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            List<StockBook> stockBookList = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject stockBookObject = (JSONObject) item;
                StockBook stockBook = ModelParse.getStockBook(stockBookObject.toString());
                stockBookList.add(stockBook);
            }

            List<Node> nodeList = new ArrayList<>();
            int i = 0;
            for (StockBook stockBookbook : stockBookList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/bookDetail/bookDetailItem.fxml"));
                Node node = loader.load();
                BookDetailItemController itemController = loader.getController();
                itemController.setStockBook(stockBookbook);
                if(i % 2 != 0){
                    itemController.setFill("#74b9ff");
                }
                nodeList.add(node);
                i++;
            }
//            this.stockContainer.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void loadAuthors(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(bookURL).build();
    }
}
