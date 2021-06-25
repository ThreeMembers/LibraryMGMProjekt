package controller;

import Model.Author;
import Model.Book;
import Model.Category;
import Model.StockBook;
import com.jfoenix.controls.JFXComboBox;
import controller.addAuthor.AddSingleAuthorController;
import controller.addBook.AddMultiBookController;
import controller.addBook.AddSingleBookController;
import controller.addStocks.AddMultiStocksController;
import controller.addStocks.AddSingleStocksController;
import controller.bookDetail.BookDetailItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import java.util.*;

public class HomeView implements Initializable {

    public static String bookURL = "http://localhost:8080/LibraryRestAPI/webapi/books/";
    public static String authorURL = "http://localhost:8080/LibraryRestAPI/webapi/authors/";


    @FXML
    VBox bookContent = new VBox();
    @FXML
    VBox stockBookContent = new VBox();

    @FXML
    TableView<Author> authorTable;

    @FXML
    TableView<Category> cateTable;

    @FXML
    TableColumn colIDAuthor, colNameAuthor, colAge, colGender, colNumber, colIDCate, colNameCate, colDescriptCate;

    @FXML
    private JFXComboBox<String> createModes;
    ObservableList<String> cateListchoices = FXCollections.observableArrayList("Single", "Multi");
    @FXML
    private JFXComboBox<String> createTargets;
    ObservableList<String> authorListchoices = FXCollections.observableArrayList("Book", "Author", "Category", "Stock");

    @FXML
    HBox title;

    @FXML
    Label lbID, lbIDS, lbName, lbAuthor, lbCategory, lbstocks;
    @FXML
    Label lbBook, lbQuality, lbRelease, lbIsBorrow;

    private List<Book> bookList;
    int idAuthor, idCate, idStock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createModes.setItems(cateListchoices);
        createModes.setValue("Single");
        createTargets.setItems(authorListchoices);
        createTargets.setValue("Book");

        loadBooks();
        loadStocks();
        loadAuthors();
        loadCategory();

        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbName.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbAuthor.prefWidthProperty().bind(this.title.widthProperty().divide(5));
        this.lbCategory.prefWidthProperty().bind(this.title.widthProperty().divide(5));
        this.lbstocks.prefWidthProperty().bind(this.title.widthProperty().divide(18.3));

        this.lbIDS.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbBook.prefWidthProperty().bind(this.title.widthProperty().divide(2.2));
        this.lbQuality.prefWidthProperty().bind(this.title.widthProperty().divide(5));
        this.lbRelease.prefWidthProperty().bind(this.title.widthProperty().divide(8));
        this.lbIsBorrow.prefWidthProperty().bind(this.title.widthProperty().divide(8));
    }

    public void loadBooks() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(bookURL).build();
        try {
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            bookList = new ArrayList<>();

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
        String url = ConnectionAPIOption.stocksURL;
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
                idStock = stockBook.getId();
            }

            Collections.sort(stockBookList, new Comparator<StockBook>() {
                @Override
                public int compare(StockBook o1, StockBook o2) {
                    return o2.getId() - o1.getId();
                }
            });

            List<Node> nodeList = new ArrayList<>();
            for (StockBook stockBook : stockBookList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/StockBookViewItem.fxml"));
                Node node = loader.load();
                StockBookViewItemController itemController = loader.getController();
                itemController.setStock(stockBook);
                if(stockBook.getBook().getId() % 2 != 0){
                    itemController.setFill("#74b9ff");
                }
                if(stockBook.isBorrow()){
                    itemController.setFill("#ff6b6b");
                }
                nodeList.add(node);
            }
            this.stockBookContent.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void loadAuthors(){
        String url = ConnectionAPIOption.authorURL;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(response.body().string());
            List<Author> authorList = new ArrayList<>();
            for (Object sub : array.toArray()){
                JSONObject object = (JSONObject) sub;
                Author author = ModelParse.getAuthor(object.toString());
                authorList.add(author);
                idAuthor = author.getId();
            }
            colIDAuthor.setCellValueFactory(new PropertyValueFactory<Integer, Author>("id"));
            colNameAuthor.setCellValueFactory(new PropertyValueFactory<String, Author>("name"));
            colAge.setCellValueFactory(new PropertyValueFactory<Integer, Author>("age"));
            colGender.setCellValueFactory(new PropertyValueFactory<Boolean, Author>("gender"));
            colNumber.setCellValueFactory(new PropertyValueFactory<Integer, Author>("numberBook"));
            ObservableList<Author> observableList = FXCollections.observableList(authorList);
            authorTable.setItems(observableList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void loadCategory(){
        String url = ConnectionAPIOption.categoryURL;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(response.body().string());
            List<Category> categoryList = new ArrayList<>();
            for (Object sub : array.toArray()){
                JSONObject object = (JSONObject) sub;
                Category category = ModelParse.getCategory(object.toString());
                categoryList.add(category);
                idCate = category.getId();
            }
            colIDCate.setCellValueFactory(new PropertyValueFactory<Integer, Category>("id"));
            colNameCate.setCellValueFactory(new PropertyValueFactory<String, Category>("name"));
            colDescriptCate.setCellValueFactory(new PropertyValueFactory<String, Category>("description"));
            ObservableList<Category> observableList = FXCollections.observableList(categoryList);
            cateTable.setItems(observableList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createNewObject(){
        if(createModes.getValue().toLowerCase().equals("single")){
            switch (createTargets.getValue().toLowerCase()){
                case "book":
                    AddSingleBookController singleBookController = openForm("/view/addBook/addSingleBook.fxml").getController();
                    singleBookController.setTheLastID(this.bookList.size() + 1);
                    break;
                case "author":
                    AddSingleAuthorController singleAuthorController = openForm("/view/addAuthor/addSingleAuthor.fxml").getController();
                    singleAuthorController.setLastID(this.idAuthor);
                    break;
                case "category":
                    openForm("/view/addCate/addSingleCate.fxml");
                    break;
                default:
                    AddSingleStocksController singleStocksController = openForm("/view/addStocks/addSingleStocks.fxml").getController();
                    singleStocksController.setLastID(this.idStock);
                    break;
            }
        }else {
            switch (createTargets.getValue().toLowerCase()){
                case "book":
                    AddMultiBookController multiBookController = openForm("/view/addBook/addMultiBook.fxml").getController();
                    multiBookController.setTheLastID(this.bookList.size() + 1);
                    break;
                case "author":
                    openForm("/view/addAuthor/addMultiAuthor.fxml");
                    break;
                case "category":
                    openForm("/view/addCate/addMultiCate.fxml");
                    break;
                default:
                    openForm("/view/addStocks/addMultiStocks.fxml").getController();
                    break;
            }
        }
    }
    public FXMLLoader openForm(String locate){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(locate));
            Parent form = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(form);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
