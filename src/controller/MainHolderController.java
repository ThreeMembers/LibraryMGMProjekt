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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
    private boolean isMenuOpen = false;

    public void openMenu(){
        if(isMenuOpen == false){
            menuSide.open();
            isMenuOpen = true;
        }else {
            menuSide.close();
            isMenuOpen = false;
        }
    }
    public void openAccount(){

    }

    @FXML
    private ComboBox<String> choices;
    ObservableList<String> Listchoice = FXCollections.observableArrayList("Book", "Author", "Category", "Account");

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
                                default:
                                    //about
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

        choices.setItems(Listchoice);
        choices.setValue("Name");
    }
}
