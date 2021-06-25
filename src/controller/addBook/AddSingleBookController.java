package controller.addBook;

import Model.*;
import com.jfoenix.controls.JFXComboBox;
import controller.accountDetailController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ResourceBundle;

public class AddSingleBookController implements Initializable {
    @FXML
    private ImageView imageAdd;
    @FXML
    final FileChooser fc = new FileChooser();
    @FXML
    private JFXComboBox<String> catechoices;
    @FXML
    private JFXComboBox<String> authorchoices;
    @FXML
    TextArea taName;

    @FXML
    TextField tfNumber;

    @FXML
    Label circleOk;

    private List<Category> categoryList;
    private List<Author> authorList;

    private Book book;
    private int lastID;
    File file;

    @FXML
    private void openFileChooser(ActionEvent event){
        fc.setTitle("My File Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.PNG", "*.jpg", "*.JPG"));
        file = fc.showOpenDialog(null);

        if(file != null){
            imageAdd.setImage(new Image(String.valueOf(file.toURI())));
        }else{
            System.out.println("A file is invalid");
        }
    }
    public static Boolean uploadFile(File file, int id) {
        Response response;
        String finalResponce;
        try {
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("image", String.valueOf(id), RequestBody.create(file, MediaType.parse("image/jpeg")))
                    .build();
            Request request = new Request.Builder()
                    .url("http://localhost:8080/libraryapi/webapi/images/upload/")
                    .post(body)
                    .build();
            response = new OkHttpClient().newCall(request).execute();
            finalResponce = response.body().string();
//            System.out.println(response.body().contentType());
//            System.out.println("Status: " + response.code() + " - " + finalResponce);
//            mainEventListing.setBackdropUrl(finalResponce);
            return true;
        } catch (Exception e) {
            // show error
            e.printStackTrace();
        }
        return false;
    }
    public void createNewBook() throws InterruptedException {
        String image = null;
        if(imageAdd.getImage() != null){
            image = String.valueOf(this.lastID);
            uploadFile(file, this.lastID);
        }
        System.out.println(this.lastID);
        Author author = new Author();
        for(Author item : this.authorList){
            if(this.authorchoices.getValue().equals(item.getName())){
                author.setId(item.getId());
            }
        }
        Category category = new Category();
        for(Category item : this.categoryList){
            if(this.catechoices.getValue().equals(item.getName())){
                category.setId(item.getId());
            }
        }
        book = new Book(this.taName.getText(), author, category, image, Integer.valueOf(this.tfNumber.getText()), true);
        if(createPostBookRequest(this.book)){
            this.circleOk.setStyle("-fx-background-color: #55efc4");
            Thread.sleep(1500);
            Stage stage = (Stage) this.circleOk.getScene().getWindow();
            stage.close();
            return;
        }
        this.circleOk.setStyle("-fx-background-color: #ff7675");
    }

    public static boolean createPostBookRequest(Book book){
        String url = ConnectionAPIOption.bookURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject jsonBook = new JSONObject();
        jsonBook.put("book", book.toJSON());
        RequestBody body = RequestBody.create(jsonBook.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url).post(body).build();
        try{
            Response response = client.newCall(request).execute();
            int code = response.code();
            if(code == 201){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> categoryObservableList = FXCollections.observableList(getCategoryData());
        catechoices.setItems(categoryObservableList);

        ObservableList<String> authorObservableList = FXCollections.observableList(getAuthorData());
        authorchoices.setItems(authorObservableList);

        new JFXAutoCompleteComboBoxListener<>(catechoices);
        new JFXAutoCompleteComboBoxListener<>(authorchoices);
    }
    private List<String> getCategoryData(){
        String url = ConnectionAPIOption.categoryURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        List<String> array = new ArrayList<>();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(response.body().string());
            categoryList = new ArrayList<>();
            for(Object sub : jsonArray.toArray()){
                JSONObject object = (JSONObject) sub;
                Category category = ModelParse.getCategory(object.toString());
                categoryList.add(category);
                array.add(category.getName());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return array;
    }
    private List<String> getAuthorData(){
        String url = ConnectionAPIOption.authorURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        List<String> array = new ArrayList<>();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(response.body().string());
            authorList = new ArrayList<>();
            for(Object sub : jsonArray.toArray()){
                JSONObject object = (JSONObject) sub;
                Author author = ModelParse.getAuthor(object.toString());
                authorList.add(author);
                array.add(author.getName());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return array;
    }
    public void setTheLastID(int id){
        this.lastID = id;
    }
}
