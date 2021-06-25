package controller.addBook;

import Model.Author;
import Model.Book;
import Model.Category;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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
import sample.others.ConnectionAPIOption;
import sample.others.JFXAutoCompleteComboBoxListener;
import sample.others.ModelParse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddMultiBookController implements Initializable {
    @FXML
    private ImageView imageAdd;
    @FXML
    final FileChooser fc = new FileChooser();
    @FXML
    private JFXComboBox<String> catechoices;
    @FXML
    private JFXComboBox<String> authorchoices;
    @FXML
    TextField tfNumber;
    @FXML
    TextArea taName;
    @FXML
    VBox bookItemContainer;

    private List<Category> categoryList;
    private List<Author> authorList;

    List<File> fileList;
    List<Book> bookList;

    private int lastID, temp;
    @FXML
    private File openFileChooser(){
        fc.setTitle("My File Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.PNG", "*.jpg", "*.JPG"));
        File file = fc.showOpenDialog(null);

        if(file != null){
            imageAdd.setImage(new Image(String.valueOf(file.toURI())));
        }else{
            System.out.println("A file is invalid");
        }
        return file;
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
        fileList = new ArrayList<>();
        bookList = new ArrayList<>();
    }

    public void setTheLastID(int i) {
        this.lastID = temp = i;
    }

    private java.util.List<String> getCategoryData(){
        String url = ConnectionAPIOption.categoryURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        java.util.List<String> array = new ArrayList<>();
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
    private java.util.List<String> getAuthorData(){
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
    public Book createNewBook(){
        String image = null;
        if(imageAdd.getImage() != null){
            image = String.valueOf(this.lastID);
            this.fileList.add(new File(imageAdd.getImage().getUrl()));
        }
        System.out.println(this.lastID);
        Author author = new Author();
        for(Author item : this.authorList){
            if(this.authorchoices.getValue().equals(item.getName())){
                author.setId(item.getId());
                author.setName(item.getName());
            }
        }
        Category category = new Category();
        for(Category item : this.categoryList){
            if(this.catechoices.getValue().equals(item.getName())){
                category.setId(item.getId());
                category.setName(item.getName());
            }
        }
        Book book = new Book(this.taName.getText(), author, category, image, Integer.valueOf(this.tfNumber.getText()), true);
        Node node = openForm("/view/addBook/addMultiBookitem.fxml", book);
        this.bookList.add(book);
        this.bookItemContainer.getChildren().add(node);
        this.taName.clear();
        this.catechoices.setValue("");
        this.authorchoices.setValue("");
        this.tfNumber.clear();
        this.imageAdd.setImage(null);
        this.lastID += 1;
        return book;
    }
    public Node openForm(String locate, Book book){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(locate));
            Node node = loader.load();
            AddMultiBookitemController controller = loader.getController();
            controller.setBook(book);
            return node;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteChecked(){
        List<Node> tempList = new ArrayList<>();
        for(Node i : this.bookItemContainer.getChildren()){
            if(i.getAccessibleText().equals("checked")){
                tempList.add(i);
            }
        }
        this.lastID -= tempList.size();
        this.bookList.removeAll(tempList);
        this.fileList.removeAll(tempList);
        this.bookItemContainer.getChildren().removeAll(tempList);
    }
    public void deleteAll(){
        this.bookItemContainer.getChildren().clear();
        this.lastID = temp;
        this.fileList.clear();
        this.bookList.clear();
    }
    public void createItems(){
        for(int i = 0; i < bookList.size(); i ++){
            Book book = bookList.get(i);
            File file = fileList.get(i) == null ? null : fileList.get(i);
            boolean complete = AddSingleBookController.createPostBookRequest(book);
            if(complete && file != null){
                AddSingleBookController.uploadFile(file, Integer.parseInt(book.getImage()));
            }
        }
    }
}
