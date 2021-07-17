package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HistoryFormController {

    public AnchorPane root1;
    public AnchorPane root;

    public void initialize() throws IOException {
        initUi("PaymentForm.fxml");

    }

    private void initUi(String location) throws IOException {
        this.root1.getChildren().clear();
        this.root1.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+location)));
    }


    public void lblPaymentOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("PaymentForm.fxml");
    }

    public void lblRequestOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("RequestForm.fxml");
    }

    public void lblSampleOnAction(MouseEvent mouseEvent) throws IOException {
        initUi("SampleUnitForm.fxml");
    }
}
