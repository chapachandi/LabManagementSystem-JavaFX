package controller;


import bo.BoFactory;
import bo.custom.RequestBo;
import com.jfoenix.controls.JFXButton;
import dto.RequestDTO;
import dto.SampleTypeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.RequestTM;
import view.tm.SampleTypeTM;

import java.util.List;
import java.util.Optional;

public class RequestFormController {

    public TableView tblReceipt;
    public TableColumn clmReceiptNo;
    public TableColumn clmDate;
    public TableColumn clmTime;
    public TableColumn clmPatientId;

    public TableColumn clmOpareter;
    public TableColumn clmRequestId;
    public TableView tblRequest;
    public JFXButton btnPrintRequest;

    RequestBo bo;

    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.REQUEST);

        clmRequestId.setCellValueFactory(new PropertyValueFactory("requestId"));
        clmDate.setCellValueFactory(new PropertyValueFactory("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory("time"));
        clmPatientId.setCellValueFactory(new PropertyValueFactory("patientId"));
        clmOpareter.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllRequests();

        tblRequest.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((SampleTypeTM) newValue);
                });


    }

    private void setData(SampleTypeTM newValue) {


    }
    private void loadAllRequests() throws Exception {
        ObservableList<RequestTM> tmList = FXCollections.observableArrayList();
        List<RequestDTO> allRequests = bo.getAllRequests();
        for (RequestDTO dto : allRequests) {
            Button btn = new Button("Delete");
            RequestTM tm = new RequestTM(dto.getRequestId(), dto.getDate(), dto.getTime(), dto.getPatientId(), btn);
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
                        if (bo.deleteRequest(tm.getRequestId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllRequests();
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
        tblRequest.setItems(tmList);
    }



    public void txtSearchOnAction(ActionEvent actionEvent) {



    }

    public void btnPrintRequestOnAction(ActionEvent actionEvent) {
    }
}
