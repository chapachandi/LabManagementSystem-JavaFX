package controller;

import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardFormController {


    public AnchorPane root;
    public Label lblDate;
    public Label lblTime;

    public void initialize() throws IOException {
        initUi("DashBoardPaneForm.fxml");

    }

    private void initUi(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
        lblDate.setText((String.valueOf(LocalDate.now())));
        setLBLTime();


    }

    private void setLBLTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,event -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public void btnTestOnAction(ActionEvent actionEvent) throws IOException {
        initUi("TestForm.fxml");
    }

    public void btnSampleOnAction(ActionEvent actionEvent) throws IOException {
        initUi("SampleForm.fxml");

    }


    public void btnPatientOnAction(ActionEvent actionEvent) throws IOException {
        initUi("PatientForm.fxml");
    }



    public void btnTestResultsOnAction(ActionEvent actionEvent) throws IOException {
        initUi("TestReportForm.fxml");

    }

    public void btnBookingOnAction(ActionEvent actionEvent) throws IOException {
        initUi("BookingForm.fxml");
    }

    public void btnDashBoradOnAction(ActionEvent actionEvent) throws IOException {
        initUi("DashBoardPaneForm.fxml");
    }

    public void btnPlaceRequestOnAction(ActionEvent actionEvent) throws IOException {
        initUi("ReqestAccessForm.fxml");
    }

    public void btnHistoryOnAction(ActionEvent actionEvent) throws IOException {
        initUi("HistoryForm.fxml");
    }


    public void btnIncomeOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Income.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            JasperPrint jp = JasperFillManager.fillReport(
                    jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, true);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
