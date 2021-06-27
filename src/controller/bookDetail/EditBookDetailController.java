package controller.bookDetail;

import Model.Author;
import Model.Book;
import Model.Category;
import com.jfoenix.controls.JFXComboBox;
import controller.addBook.AddSingleBookController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.*;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditBookDetailController implements Initializable {
    @FXML
    private ImageView imageEditBookDetail;
    @FXML
    private Button btnOpenImage;
    // Create a FileChooser
    @FXML
    final FileChooser fc = new FileChooser();
    @FXML
    private JFXComboBox<String> choice;
    @FXML
    private JFXComboBox<String> choice1;
    @FXML
    private TextArea taName;
    @FXML
    CheckBox cbAvailable;

    private List<Category> categoryList;
    private List<Author> authorList;

    private Book book;
    private File file;

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void cancel(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void OK(ActionEvent event){
        String newName = this.taName.getText();
        Category newCate = new Category();
        for(Category item : categoryList){
            if(item.getName().equals(this.choice.getEditor().getText())){
                newCate = item;
                break;
            }
        }
        Author newAuthor = new Author();
        for(Author item : authorList){
            if(item.getName().equals(this.choice1.getEditor().getText())){
                newAuthor = item;
                break;
            }
        }
        boolean isAvai = this.cbAvailable.isSelected();
        Book book = new Book();
        book.setName(newName);
        book.setCategory(newCate);
        book.setAuthor(newAuthor);
        book.setAvailable(isAvai);
        AddSingleBookController.uploadFile(file, Integer.parseInt(book.getImage()));
        String url = ConnectionAPIOption.bookURL + this.book.getId();
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject object = new JSONObject();
        object.put("book", book.toJSON());
        RequestBody body = RequestBody.create(object.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url).put(body).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openFileChooser(ActionEvent event){
        fc.setTitle("My File Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.PNG", "*.jpg", "*.JPG"));
        file = fc.showOpenDialog(null);

        if(file != null){
            imageEditBookDetail.setImage(new Image(String.valueOf(file.toURI())));
        }else{
            System.out.println("A file is invalid");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authorList = new ArrayList<>();
        categoryList = new ArrayList<>();
        choice.setItems(FXCollections.observableList(loadCategory(categoryList)));
        choice1.setItems(FXCollections.observableList(loadAuthors(authorList)));
        new JFXAutoCompleteComboBoxListener<>(choice);
        new JFXAutoCompleteComboBoxListener<>(choice1);
    }
    public void setBook(Book book){
        this.book = book;
        this.taName.setText(book.getName());
        choice.setValue(book.getCategory().getName());
        choice1.setValue(book.getAuthor().getName());
        String imageString = ConnectionAPIOption.imageURL + book.getImage();
        imageEditBookDetail.setImage(new Image(imageString));
        this.cbAvailable.setSelected(book.isAvailable());
    }

    public List<String> loadAuthors(List<Author> authorList){
        List<String> temp = new ArrayList<>();
        String url = ConnectionAPIOption.authorURL;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object sub : array.toArray()){
                JSONObject object = (JSONObject) sub;
                Author author = ModelParse.getAuthor(object.toString());
                authorList.add(author);
                temp.add(author.getName());
            }
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public List<String> loadCategory(List<Category> categoryList){
        List<String> temp = new ArrayList<>();
        String url = ConnectionAPIOption.categoryURL;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object sub : array.toArray()){
                JSONObject object = (JSONObject) sub;
                Category category = ModelParse.getCategory(object.toString());
                categoryList.add(category);
                temp.add(category.getName());
            }
            int code = response.code();
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
