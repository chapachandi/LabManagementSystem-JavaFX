package controller;

import bo.BoFactory;
import bo.custom.PatientBo;
import bo.custom.RequestBo;
import bo.custom.SampleUnitBo;
import dto.PatientDTO;
import dto.RequestDTO;
import dto.SampleUnitDTO;
import entity.Patient;
import entity.SampleUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DashBoardPaneFormController {

    public AnchorPane root;
    public Label lblPatientCount;
    public Label lblSamplesCount;
    public Label lblRequestCount;
    public LineChart lineChartPatient;
    public NumberAxis y;
    public CategoryAxis x;
    public PieChart pieChart;

    PatientBo bo;
    RequestBo requestBo;
    SampleUnitBo sampleUnitBo;

    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.PATIENT);
        requestBo = BoFactory.getInstance().getBo(BoFactory.BOType.REQUEST);
        sampleUnitBo = BoFactory.getInstance().getBo(BoFactory.BOType.SAMPLEUNIT);

        try{
            ArrayList<PatientDTO> allPatients = bo.getAllPatients();
            lblPatientCount.setText(String.valueOf(allPatients.size()));

            ArrayList<RequestDTO> allRequests = requestBo.getAllRequests();
            lblRequestCount.setText(String.valueOf(allRequests.size()));

            ArrayList<SampleUnitDTO> allSamples = sampleUnitBo.getAllSampleUnits();
            lblSamplesCount.setText(String.valueOf(allSamples.size()));


        } catch (Exception e) {
            e.printStackTrace();
        }


        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jly", 22));
        series.getData().add(new XYChart.Data("Ags", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));


        lineChartPatient.getData().addAll(series);


        PieChart.Data data1 = new PieChart.Data("Blood",8.2);
        PieChart.Data data2 = new PieChart.Data("Urine",5.2);
        PieChart.Data data3 = new PieChart.Data("Stool",1.2);
        PieChart.Data data4 = new PieChart.Data("Nasal swab",3.2);
        PieChart.Data data5 = new PieChart.Data("Wound swab",3.2);

        pieChart.getData().addAll(data1,data2,data3,data4,data5);


    }

    public void lineChartOnAction(MouseEvent mouseEvent) {
      System.exit(0);
    }
}
