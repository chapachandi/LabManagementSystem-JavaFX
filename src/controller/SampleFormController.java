package controller;


import bo.BoFactory;
import bo.custom.SampleTypeBo;
import dto.SampleTypeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.SampleTypeTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SampleFormController {

    public TextField txtTime;
    public TableColumn clmSampleId;
    public TextField txtTypeName;
    public TextField txtUnit;
    public TableView tblSampleType;
    public TableColumn clmTypeName;
    public TableColumn clmSampleUnit;
    public TableColumn clmOparetor;
    public TextField txtSampleId;
    public AnchorPane root;
    public TextField txtSearchId;

    SampleTypeBo bo;

    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.SAMPLETYPE);

        clmSampleId.setCellValueFactory(new PropertyValueFactory("sampleId"));
        clmTypeName.setCellValueFactory(new PropertyValueFactory("typeName"));
        clmSampleUnit.setCellValueFactory(new PropertyValueFactory("unit"));
        clmOparetor.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllSampleTypes();
        genarateSampleId();

        tblSampleType.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((SampleTypeTM) newValue);
                });


    }
    private void genarateSampleId() {
        try {
            String lastId = bo.getSampleId();
            if (lastId != null) {
                lastId = lastId.split("[A-Z]")[1];
                lastId = "ST00" + (Integer.parseInt(lastId) + 1);
                txtSampleId.setText(lastId);
            } else {
                txtSampleId.setText("ST001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            txtSampleId.setText(bo.getSampleId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(SampleTypeTM tm) {
        txtSampleId.setText(tm.getSampleId());
        txtTypeName.setText(tm.getTypeName());
        txtUnit.setText(tm.getUnit()+"");
    }
    private void refreshId(){

    }


    public void txtSearchSampleIdOnAction(ActionEvent actionEvent) throws Exception {

    }

    public void btnSaveSampleTypeOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile(("(S)[0-9]{2}\\d")).matcher(txtSampleId.getText()).matches()) {
            txtSampleId.setStyle("-fx-border-color:blue;");
            txtTypeName.requestFocus();
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtTypeName.getText()).matches()) {
                txtTypeName.setStyle("-fx-border-color:blue;");
                txtUnit.requestFocus();
                if (Pattern.compile("^[0-9 .//a-z]*\\b$").matcher(txtUnit.getText()).matches()) {
                    txtUnit.setStyle("-fx-border-color:blue;");

                    boolean isSaved = bo.saveSampleType(
                            new SampleTypeDTO(txtSampleId.getText(), txtTypeName.getText(), txtUnit.getText())
                    );
                    System.out.println(isSaved);
                    loadAllSampleTypes();
                    clearMethode();
                } else {
                    txtUnit.setStyle("-fx-border-color:red;");
                    txtUnit.requestFocus();
                }
            } else {
                txtTypeName.setStyle("-fx-border-color:red;");
                txtTypeName.requestFocus();
            }
        }else {
            txtSampleId.setStyle("-fx-border-color:red;");
            txtSampleId.requestFocus();
        }

    }

    public void btnUpdateSampleTypeOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdate = bo.updateSampleType(
                new SampleTypeDTO(txtSampleId.getText(),txtTypeName.getText(),txtUnit.getText()));
        System.out.println(isUpdate);
        loadAllSampleTypes();

    }
    private void loadAllSampleTypes() throws Exception {
        ObservableList<SampleTypeTM> tmList = FXCollections.observableArrayList();
        List<SampleTypeDTO> allSampleTypes = bo.getAllSampleTypes();
        for (SampleTypeDTO dto : allSampleTypes) {
            Button btn = new Button("Delete");
            SampleTypeTM tm = new SampleTypeTM(dto.getSampleId(),dto.getTypeName(),dto.getUnit(),btn);
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
                        if (bo.deleteSampleType(tm.getSampleId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllSampleTypes();
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
        tblSampleType.setItems(tmList);

    }
    private void clearMethode(){
        txtSampleId.setText("");
        txtTypeName.setText("");
        txtUnit.setText("");
    }

//    public void btnClearSampleTypeOnAction(ActionEvent actionEvent) {
//        txtSampleId.setText("");
//        txtTypeName.setText("");
//        txtUnit.setText("");
//    }

    public void btnNextOnAction(ActionEvent actionEvent) {
    }

    public void btnNewSampleTypeOnAction(ActionEvent actionEvent) {
        this.root.getChildren().clear();
        try {
            this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource(
                    "../view/SampleForm.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        SampleTypeDTO sampleType = null;
        try {
            sampleType = bo.getSampleType(txtSearchId.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(sampleType!=null){
            txtSampleId.setText(sampleType.getSampleId());
            txtTypeName.setText(sampleType.getTypeName());
            txtUnit.setText(sampleType.getUnit());
        }
    }
}
