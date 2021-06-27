package controller.bookDetail;

import Model.StockBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;


public class BookDetailItemController {
    private StockBook stockBook;

    @FXML
    Label lbID, lbName, lbQuality, lbYear, lbBorrow;

    @FXML
    Pane container;

    int count = 0;

    public void setStockBook(StockBook list){
        this.stockBook = list;
        this.lbID.setText(String.valueOf(stockBook.getId()));
        this.lbName.setText(stockBook.getBook().getName());
        this.lbQuality.setText(stockBook.getQuality().getSituation());
        this.lbYear.setText(String.valueOf(stockBook.getReleaseYear()));
        this.lbBorrow.setText(String.valueOf(stockBook.isBorrow()));
    }

    public void setFill(String s) {
        this.container.setStyle("-fx-background-color: " + s);
    }
    public void openDetail(){
        count++;
        if(count == 2){
            count = 0;
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/bookDetail/editStock.fxml"));
                Parent parent = loader.load();
                EditStock controller = loader.getController();
                controller.setStockBook(this.stockBook);
                Scene scene = new Scene(parent);
                scene.setFill(Color.TRANSPARENT);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public void resetClick(MouseEvent mouseEvent){
        count = 0;
    }
}
