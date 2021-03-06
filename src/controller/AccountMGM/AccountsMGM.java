package controller.AccountMGM;

import Model.Account;
import Model.Book;
import controller.BookViewItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import sample.others.ConnectionAPIOption;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountsMGM implements Initializable {
    @FXML
    VBox Account = new VBox();
    @FXML
    VBox AccountApproval = new VBox();

    @FXML
    HBox title;

    @FXML
    Label lbID, lbPermission, lbUserName,lbRealName,lbAge,lbGender,lbExpirationDate,lbDateLeft,lbPermission2,lbUserName2,lbAge2,lbGender2,lbAccept ;
    @FXML
    private Stage stage;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnDel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add borrow request items
        loadAccounts();

        this.lbID.prefWidthProperty().bind(this.title.widthProperty().divide(11));
        this.lbPermission.prefWidthProperty().bind(this.title.widthProperty().divide(8.8));
        this.lbUserName.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        this.lbRealName.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        this.lbAge.prefWidthProperty().bind(this.title.widthProperty().divide(8.8));
        this.lbGender.prefWidthProperty().bind(this.title.widthProperty().divide(8.8));
        this.lbExpirationDate.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        this.lbDateLeft.prefWidthProperty().bind(this.title.widthProperty().divide(7.04));
        //Add borrow record items
        List<Node> recordItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/AccountMGM/AccountApprovalItem.fxml"));
                Node item = loader.load();
                AccountApprovalItem itemController = loader.getController();
                itemController.setAccessibleText(String.valueOf(i));
                itemController.setID(i);
//                BookViewItemController bookViewItemController = loader.getController();
//                bookViewItemController.setBook(temp);
                AccountApproval.getChildren().add(item);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        this.AccountApproval.getChildren().addAll(recordItems);
        this.lbPermission2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbUserName2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbAge2.prefWidthProperty().bind(this.title.widthProperty().divide(4));
        this.lbGender2.prefWidthProperty().bind(this.title.widthProperty().divide(8));
        this.lbAccept.prefWidthProperty().bind(this.title.widthProperty().divide(8));
        //this.lbColumn.prefWidthProperty().bind(this.title.widthProperty().divide(4.4));
        tooltip();
        tooltip1();
    }
    public void tooltip(){
        Tooltip btnaddToolTip = new Tooltip("Add new account");
        btnCreate.setTooltip(btnaddToolTip);
        btnaddToolTip.setStyle("-fx-background-color:white; -fx-text-fill:black");
    }
    public void tooltip1(){
        Tooltip btnaddToolTip = new Tooltip("Delete account selected");
        btnDel.setTooltip(btnaddToolTip);
        btnaddToolTip.setStyle("-fx-background-color:white; -fx-text-fill:black");
    }

    public void deleteChecked(){
        List<Node> tempList = new ArrayList<>();
        for( Node i: this.AccountApproval.getChildren()){
            if(i.getAccessibleText().equals("checked")){
                System.out.println("Checked");
                tempList.add(i);
            }
        }
        this.AccountApproval.getChildren().removeAll(tempList);
    }
    public void createAccount(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AccountMGM/NewAccount.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadAccounts(){
        String url = ConnectionAPIOption.accountURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            //JSONObject object = (JSONObject) parser.parse(response.body().string());

            List<Account> accountList = new ArrayList<>();

            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for (Object item : array) {
                JSONObject object = (JSONObject) item;
                Account account = ModelParse.getAccount(object.toString());
                accountList.add(account);
            }

            List<Node> nodeList = new ArrayList<>();
            int i = 0;
            for (Account account : accountList) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/AccountMGM/AccountItem.fxml"));
                Node node = loader.load();
                AccountItem itemController = loader.getController();
                itemController.setUser(account);
                if(i % 2 != 0){
                    itemController.setFill("#74b9ff");
                }
                if(account.getDateLeft() <= 0){
                    itemController.setFill("#ff4d4d");
                }
                nodeList.add(node);
                i++;
            }
            this.Account.getChildren().addAll(nodeList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
