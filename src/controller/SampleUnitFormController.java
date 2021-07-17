package controller;

import bo.BoFactory;
import bo.custom.PatientBo;
import bo.custom.SampleUnitBo;
import dto.RequestDTO;
import dto.SampleUnitDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.RequestTM;
import view.tm.SampleUnitTM;

import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SampleUnitFormController {

    public TableView tblSampleUnit;
    public TextField txtUnitCode;
    public ComboBox cmbPatientId;
    public ComboBox cmbSampleType;
    public TextField txtTime;
    public DatePicker txtDate;
    public TableColumn clmUnitCode;
    public TableColumn clmSampleIdUnitTbl;
    public TableColumn clmDate;
    public TableColumn clmTime;
    public TableColumn clmPatientId;
    public TableColumn clmOparetorUnitTbl;
    public ComboBox cmbSampleId;
    public TableColumn clmSampleId;
    public TableColumn clmTestName;
    public TextField txtSearch;
    public ComboBox cmbTestName;
    public AnchorPane root1;

    SampleUnitBo bo;

    ObservableList<String> observableList = FXCollections.observableArrayList();
    ObservableList<TableModel> obtable = FXCollections.observableArrayList();

    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.SAMPLEUNIT);

        clmUnitCode.setCellValueFactory(new PropertyValueFactory("unitCode"));
        clmSampleId.setCellValueFactory(new PropertyValueFactory("sampleId"));
        clmTestName.setCellValueFactory(new PropertyValueFactory("testName"));
        clmDate.setCellValueFactory(new PropertyValueFactory("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory("time"));
        clmPatientId.setCellValueFactory(new PropertyValueFactory("patientId"));
        clmOparetorUnitTbl.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllSampleUnits();


    }

    public void getPatientId(){

    }
    private void loadAllSampleUnits() throws Exception {
        ObservableList<SampleUnitTM> tmList = FXCollections.observableArrayList();
        List<SampleUnitDTO> allSamoleUnits = bo.getAllSampleUnits();
        for (SampleUnitDTO dto : allSamoleUnits) {
            Button btn = new Button("Delete");
            SampleUnitTM tm = new SampleUnitTM(dto.getUnitCode(),dto.getSampleId(),
                    dto.getTestName(),dto.getDate(),dto.getTime(),dto.getPatientId(), btn);
            tmList.add(tm);
            btn.setOnAction((e) -> {
                try {

                    ButtonType ok = new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        if (bo.deleteSampleUnit(tm.getUnitCode())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllSampleUnits();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again", ButtonType.OK).show();
                    } else {
                        //no
                    }


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }
        tblSampleUnit.setItems(tmList);
    }




    public void btnSaveUnitOnAction (ActionEvent actionEvent){

    }

    public void btnUpdateUnitOnAction (ActionEvent actionEvent){

    }

    public void btnClearUnitOnAction (ActionEvent actionEvent){

    }

    public void txtSearchUnitCodeOnAction(ActionEvent actionEvent) {
    }
}
