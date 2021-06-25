package controller;

import Model.Book;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Text;
import sample.others.AuthenticationClient;
import sample.others.ConnectionAPIOption;
import sample.others.JFXAutoCompleteComboBoxListener;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private JFXComboBox<String> choice;
    ObservableList<String> choices = FXCollections.observableArrayList("Book", "Author", "Category");

    @FXML
    VBox resultContent;

    @FXML
    TextField searchField;

    private List<Book> bookList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice.setItems(choices);
        choice.setValue("Book");
        new JFXAutoCompleteComboBoxListener<>(choice);

        loadBooks();

    }
    public void loadBooks() {
        System.out.println("stat loading book");
        String url = ConnectionAPIOption.bookURL;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            this.bookList = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject bookObject = (JSONObject) item;
                Book book = ModelParse.getBook(bookObject.toString());
                bookList.add(book);
            }
            System.out.println("finish loading book");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void searching() throws IOException {
        this.resultContent.getChildren().clear();

        String option = choice.getValue();
        String searchContent = this.searchField.getText().toLowerCase();

        System.out.println(searchContent);

        List<Book> temp = new ArrayList<>();
        boolean find = false;
        for (Book item : this.bookList) {
            switch (option)
            {
                case "Book":
                    if(item.getName().toLowerCase().contains(searchContent)){
                        find = true;
                        temp.add(item);
                    }
                    break;
                case "Author":
                    if(item.getAuthor().getName().toLowerCase().contains(searchContent)){
                        find = true;
                        temp.add(item);
                    }
                    break;
                default:
                    if(item.getCategory().getName().toLowerCase().contains(searchContent)){
                        find = true;
                        temp.add(item);
                    }
                    break;
            }
        }

        if (!find){
            Node node = FXMLLoader.load(getClass().getResource("/view/NotFoundLabel.fxml"));
            this.resultContent.getChildren().add(node);
            return;
        }

        List<Node> nodeList = new ArrayList<>();
        int i = 0;
        for (Book book : temp) {
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
        this.resultContent.getChildren().addAll(nodeList);
    }
}
