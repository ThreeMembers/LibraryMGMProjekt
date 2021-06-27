package controller.bookDetail;

import Model.Book;
import Model.StockBook;
import controller.BookViewItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.event.ActionEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.ConnectionAPIOption;
import sample.others.ModelParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class bookDetailController {

    public static String imageURL = "http://localhost:8080/libraryapi/webapi/images/";
//    public static String stockURL = "http://localhost:8080/libraryapi/webapi/stockbooks/";

    private Book book;

    @FXML
    Label lbName, lbAuthor, lbCate, lbStock;

    @FXML
    Button btnAvai;

    @FXML
    ImageView imgBook;

    @FXML
    VBox stockContainer;

    public void openEditBookDetail(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/bookDetail/editBookDetail.fxml"));
            Parent root = loader.load();
            EditBookDetailController controller = loader.getController();
            controller.setBook(this.book);
            Stage stage = (Stage) this.lbName.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setTitle("Edit Book Detail");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setBook(Book book) {
        this.book = book;
        this.lbName.setText(this.book.getName());
        this.lbCate.setText(this.book.getCategory().getName().toUpperCase());
        this.lbAuthor.setText(this.book.getAuthor().getName());
        Image image = new Image(imageURL + this.book.getImage());
        this.imgBook.setImage(image);
        loadStocks();
    }
    public void loadStocks(){
        String url = ConnectionAPIOption.stocksFilterBookURL + this.book.getId();
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try {
            //nhan response tu server
            Response response = client.newCall(request).execute();


            if(response.code() == 404){
                Label label = new Label();
                label.setText("NOT FOUND :<");
                label.setAlignment(Pos.CENTER);
                label.setPrefWidth(this.stockContainer.getPrefWidth());
                label.setPrefHeight(this.stockContainer.getPrefHeight() - 10);
                this.stockContainer.getChildren().add(label);
                return;
            }

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
            int code = response.code();
            response.close();

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
            this.stockContainer.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
