package controller;

import Model.Permission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.others.AuthenticationClient;
import sample.others.Cryptography;
import sample.others.FileWorking;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML
    private Stage stage;

    @FXML
    TextField tfUserName, tfPassword;

    @FXML
    Label loginMess;

    private MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
    private final String loginURL = "http://localhost:8080/LibraryRestAPI/webapi/login";
    public void Login(ActionEvent event) {
        if(!confirmUser()){
            return;
        }
        openHomeScene(event);
    }
    private boolean confirmUser(){
        String username= tfUserName.getText();
        String password = tfPassword.getText();

        if(username.equals("") || password.equals("")){
            loginMess.setText("No username or password was filled");
            return false;
        }

        String encodePwd = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationClient())
                .build();
        JSONObject content = new JSONObject();
        content.put("username", username);
        content.put("userpassword", encodePwd);
        System.out.println(content.toJSONString());
        RequestBody body = RequestBody.create(content.toJSONString(), jsonType);
        Request request = new Request.Builder().url(loginURL).post(body).build();
        try{
            //receive response from server
            Response response = client.newCall(request).execute();
            //get response code: 200 - ok, 401 - unauthorized, 406 - not_acceptable
            int code = response.code();
            //get response body
            String result = response.body().string();
            System.out.println(code);
            if(code != 200){
                response.close();
                this.loginMess.setText(result);
                return false;
            }
            //decode result
            result = Cryptography.decode(result);
            //parse json to model
            //get token and username from response
            response.close();
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(result);
            int id = Integer.parseInt(object.get("id").toString());
            String token = object.get("token").toString();

            object = (JSONObject) object.get("idpermission");

            Permission permission = new Permission(
                    Integer.parseInt(object.get("id").toString()),
                    object.get("position").toString()
            );

            JSONObject writeToFile = new JSONObject();
            writeToFile.put("permission", permission.toJSONPositionOnly());
            writeToFile.put("id", id);
            writeToFile.put("token", token);
            writeToFile.put("username", username);

            //write permission token of user to file for reuse
            if(FileWorking.writeToFile(writeToFile.toJSONString())){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println("Error in log in: " + e.getMessage());
            this.loginMess.setText("Cannot connect to server! Try again!");
        }
        return false;
    }

    private void openHomeScene(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainHolder.fxml"));
            Parent root = loader.load();
            MainHolderController mainHolderController = loader.getController();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            stage = new Stage();
            stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            mainHolderController.setRoot(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tfUserName.setText("phunghung29");
        this.tfPassword.setText("01679751807Ph");
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void min(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
