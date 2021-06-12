package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainHolderController implements Initializable {
    @FXML
    BorderPane frameHolder;
    @FXML
    JFXDrawer menuSide;
    @FXML
    JFXButton btnOpenMenu;
    @FXML
    Button btnOpenAccount;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    private boolean isOpen = false;

    public void openMenu(){
        if(isOpen == false){
            menuSide.open();
            isOpen = true;
        }else {
            menuSide.close();
            isOpen = false;
        }
    }
    public void openAccount(ActionEvent event){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/accountDetail.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            VBox menuSideBar = FXMLLoader.load(getClass().getResource("/view/MenuSide.fxml"));
            this.menuSide.setSidePane(menuSideBar);
            for (Node btn : menuSideBar.getChildren()){
                btn.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                    if(null != btn.getAccessibleText()){
                        try{
                            FXMLLoader loader;
                            switch (btn.getAccessibleText()){
                                case "home":
                                    loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("/view/homeView.fxml"));
                                    Node home = loader.load();
                                    if(this.frameHolder.getCenter() != null){
                                        if(this.frameHolder.getCenter().getAccessibleText().compareTo(home.getAccessibleText()) == 0 ){
                                            System.out.println("home page");
                                            break;
                                        }
                                    }
                                    System.out.println("add home page");
                                    this.frameHolder.setCenter(home);
                                    openMenu();
                                    break;
                                case "borrow":
                                    loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("/view/borrowTasks.fxml"));
                                    Node borrow = loader.load();
                                    if(this.frameHolder.getCenter() != null){
                                        if(this.frameHolder.getCenter().getAccessibleText().compareTo(borrow.getAccessibleText()) == 0 ){
                                            System.out.println("borrow page");
                                            break;
                                        }
                                    }
                                    System.out.println("add borrow page");
                                    this.frameHolder.setCenter(borrow);
                                    openMenu();
                                    break;
                                default:
                                    //about
                                    loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("/view/about.fxml"));
                                    Node aboutpage = loader.load();
                                    System.out.println("add about page");
                                    this.frameHolder.setCenter(aboutpage);
                                    openMenu();
                                    break;
                            }
                        }catch (Exception ex){
                            ex.printStackTrace();
                            System.out.println(ex.getMessage());
                        }
                    }
                });
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/homeView.fxml"));
            Node home = loader.load();

            this.frameHolder.setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
