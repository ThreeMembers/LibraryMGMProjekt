package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.others.AuthenticationClient;
import sample.others.FileWorking;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

public class accountDetailController implements Initializable {
    private final String baseURL = "http://localhost:8080/LibraryRestAPI/webapi/accounts";

    @FXML
    TextField tfuser, tfpwd, tfreal, tfage, tfgender, tfcode, tfregister;

    @FXML
    Button btnHidePassword;

    boolean isHide = true;

    private double x, y;
    @FXML
    void dragged(MouseEvent event) {
        Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - 0);
        stage.setY(event.getScreenY() - 0);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getScreenX();
        y = event.getScreenY();
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = getID();
        if(id.equals("")){
            System.out.println("no id has been recorded");
        }
        String idURL = baseURL + "/" + id;
        System.out.println(idURL);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
        Request request = new Request.Builder().url(idURL).build();
        try{
            Response response = client.newCall(request).execute();
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(response.body().string());
            tfuser.setText(object.get("username").toString());
            tfpwd.setText(object.get("userpassword").toString());
            tfage.setText(object.get("age").toString());
            tfgender.setText(Boolean.parseBoolean(object.get("gender").toString()) == false ? "Male" : "Female");
            JSONObject registerDate = (JSONObject) object.get("register");
            String day, month, year;
            day = registerDate.get("day").toString();
            month = registerDate.get("month").toString();
            year = registerDate.get("year").toString();

            tfregister.setText(day + "/" + month + "/" + year);
            tfcode.setText(object.get("secretcode").toString());
            tfreal.setText(object.get("realname").toString());

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static String getID(){
        try{
            String saveData = FileWorking.readFromFile();
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(saveData);
            String id = object.get("id").toString();
            return id;
        } catch (ParseException e) {
            System.out.println("Error in get id from User: " + e.getMessage());
        }
        return "";
    }

    public void unHidePassword(){
        if(isHide){
            btnHidePassword.setStyle("-fx-background-color: black; ");
            btnHidePassword.setTextFill(Color.WHITE);
            byte[] pwd = Base64.getDecoder().decode(this.tfpwd.getText());
            this.tfpwd.setText(new String(pwd));
            isHide = false;
        }
        else{
            btnHidePassword.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-radius: 5;");
            btnHidePassword.setTextFill(Color.BLACK);
            String pwd = Base64.getEncoder().encodeToString(this.tfpwd.getText().getBytes(StandardCharsets.UTF_8));
            this.tfpwd.setText(pwd);
            isHide = true;
        }
    }

}
