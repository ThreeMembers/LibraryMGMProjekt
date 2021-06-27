package controller.AccountMGM;

import Model.Account;
import Model.Date;
import Model.Permission;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Text;
import sample.others.ConnectionAPIOption;
import sample.others.Cryptography;
import sample.others.MediaTypeCollection;
import sample.others.ModelParse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class NewAccount implements Initializable {
    @FXML
    Button btnCreateAccount;
    @FXML
    TextField tfUser, tfRealname, tfAge, tfcode;
    @FXML
    PasswordField tfPwd;
    @FXML
    JFXComboBox<String> cbPer;
    @FXML
    CheckBox cbGender;

    private List<Permission> permissionList;
    @FXML
    private Label lbSuccess;

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public List<String> loadPermission(List<Permission> list){
        String url = ConnectionAPIOption.permissionURL;

        List<String> per = new ArrayList<>();
        OkHttpClient client = ConnectionAPIOption.getClient();
        Request request = new Request.Builder().url(url).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(response.body().string());
            for(Object item : array){
                JSONObject object = (JSONObject) item;
                Permission permission = ModelParse.getPermissionModel(object.toString());
                list.add(permission);
                per.add(permission.getPosition());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return per;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        permissionList = new ArrayList<>();
        cbPer.setItems(FXCollections.observableList(loadPermission(permissionList)));
    }
    public void create(){
        String username = this.tfUser.getText();
        String password = this.tfPwd.getText();
        String real = this.tfRealname.getText();
        String code = this.tfcode.getText();
        int age = Integer.parseInt(this.tfAge.getText());
        boolean gender = this.cbGender.isSelected();
        Permission permission = new Permission();
        for (Permission item : permissionList){
            if (item.getPosition().equals(this.cbPer.getValue())){
                permission = item;
            }
        }
        password = Cryptography.encode(password);
        Account account = new Account(
                permission,
                username,
                password,
                real,
                age,
                gender,
                code,
                new Date(LocalDate.now().toString())
        );
        String url = ConnectionAPIOption.accountURL;
        OkHttpClient client = ConnectionAPIOption.getClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", account.toJSONFull());
        RequestBody body = RequestBody.create(jsonObject.toString(), MediaTypeCollection.jsonType);
        Request request = new Request.Builder().url(url).post(body).build();
        try{
            Response response = client.newCall(request).execute();
            if(response.code() == 201){
                this.lbSuccess.setDisable(false);
                response.close();
                Stage stage = (Stage) this.lbSuccess.getScene().getWindow();
                stage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
