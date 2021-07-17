package controller;

import bo.BoFactory;
import bo.custom.PatientBo;
import bo.custom.TestBo;
import bo.custom.TestReportBo;
import bo.custom.TestTypeBo;
import db.DBConnection;
import dto.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.PatientTM;
import view.tm.RequestTableTM;
import view.tm.TestReportTM;
import view.tm.TestReportTableTM;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class TestReportFormController {

    public TextField txtReportId;
    public Label txtTypeName;
    public Label txtReferanceRange;
    public ComboBox cmbTestCode;
    public TextField txtTime;
    public DatePicker txtDate;
    public ComboBox cmbPatientId;
    public ComboBox cmbTestCode1;
    public TextField txtResult;
    public Label txtTestName;
    public TableView tblPatient;
    public TableColumn clmReportId;
    public TableColumn clmTestCode;
    public TableColumn clmPatientId;
    public TableColumn clmDate;
    public TableColumn clmTime;
    public TableColumn clmResult;
    public TableColumn clmReferenceRange;
    public TableColumn clmOparetor;
    public TextField txtSearchReportId;
    public Label lblTime;
    public Label lblDate;
    public ComboBox cmbTestTypeId;
    public TableColumn clmTypeId;
    public Label lblReferanceRange;
    public Label lblTypeName;
    public Label lblTestName;
    public AnchorPane root;
    public TableView tblTestReport;

    TestReportBo testReportBo;
    TestBo testBo;
    TestTypeBo testTypeBo;
    PatientBo patientBo;

    public void initialize() throws Exception {

        testReportBo = BoFactory.getInstance().getBo(BoFactory.BOType.TESTREPORT);
        testBo = BoFactory.getInstance().getBo(BoFactory.BOType.TEST);
        testTypeBo = BoFactory.getInstance().getBo(BoFactory.BOType.TESTTYPE);
        patientBo = BoFactory.getInstance().getBo(BoFactory.BOType.PATIENT);

        clmReportId.setCellValueFactory(new PropertyValueFactory("reportId"));
        clmPatientId.setCellValueFactory(new PropertyValueFactory("patientId"));
        clmTestCode.setCellValueFactory(new PropertyValueFactory("testCode"));
        clmTypeId.setCellValueFactory(new PropertyValueFactory("testTypeId"));
        clmDate.setCellValueFactory(new PropertyValueFactory("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory("time"));
        clmResult.setCellValueFactory(new PropertyValueFactory("result"));
        clmReferenceRange.setCellValueFactory(new PropertyValueFactory("referenceRange"));
        clmOparetor.setCellValueFactory(new PropertyValueFactory("btn"));

        getAllPatient();
        getAllTestType();
        getAllTests();
        genarateReportId();
        loadAllTestReports();

        lblDate.setText((String.valueOf(LocalDate.now())));
        setLBLTime();

    }
    private void setLBLTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    private void genarateReportId() throws Exception {
        try {
            String lastId = testReportBo.getReportId();
            txtReportId.setText(lastId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void txtSearchOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (cmbPatientId.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Patient", ButtonType.OK).show();
            cmbPatientId.requestFocus();
            return;
        }
        if (cmbTestTypeId.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Test Type", ButtonType.OK).show();
            cmbTestTypeId.requestFocus();
            return;
        }
        if (cmbTestCode.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Test", ButtonType.OK).show();
            cmbTestCode.requestFocus();
            return;
        }
        if (Pattern.compile(("(RP)[0-9]{2}\\d")).matcher(txtReportId.getText()).matches()) {
            txtReportId.setStyle("-fx-border-color:blue;");
            if (Pattern.compile("\"^[0-9]$\"").matcher(txtResult.getText()).matches()) {
                txtResult.setStyle("-fx-border-color:blue;");
            } else {
                txtResult.setStyle("-fx-border-color:red;");
                txtResult.requestFocus();
            }
        } else {
            txtReportId.setStyle("-fx-border-color:red;");
            txtReportId.requestFocus();
        }

        boolean isSaved = testReportBo.saveTestReport(
                new TestReportDTO(txtReportId.getText(),cmbPatientId.getValue().toString(),
                        cmbTestCode.getValue().toString(),cmbTestTypeId.getValue().toString(),
                        lblDate.getText(),lblTime.getText(),txtResult.getText(),lblReferanceRange.getText())
        );
        System.out.println(isSaved);
        loadAllTestReports();

    }
    public void getAllPatient(){
        try{
            List<PatientDTO> list=patientBo.getAllPatients();
            for (PatientDTO p : list){
                cmbPatientId.getItems().add(p.getPatientId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAllTestType() throws Exception {
        try{
            List<TestTypeDTO> list=testTypeBo.getAllTestTypes();
            for (TestTypeDTO testType: list){
                cmbTestTypeId.getItems().add(testType.getTestTypeId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getAllTests() throws Exception {
        try{
            List<TestDTO> list=testBo.getAllTests();
            for (TestDTO test : list){
                cmbTestCode.getItems().add(test.getTestCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtResult.setText("");
    }



    public void cmbTestCodeOnAction(ActionEvent actionEvent) {
        try {
            String t= (String) cmbTestCode.getValue();
            System.out.println("test testCode t" +t);
            TestDTO test = testBo.getTest(t);
            System.out.println("test testCode " +test.getTestCode());
            lblTestName.setText(test.getTestName());

            //lblFullName.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbPatientIdOnAction(ActionEvent actionEvent) {
        try {
            String p= (String) cmbPatientId.getValue();
            System.out.println("patient id p" +p);
            PatientDTO patient = patientBo.getPatient(p);
            System.out.println("patient id " +patient.getPatientId());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbTestTypeIdOnAction(ActionEvent actionEvent) {
        try {
            String ty= (String) cmbTestTypeId.getValue();
            System.out.println("test type id " +ty);
            TestTypeDTO testType = testTypeBo.getTestType(ty);
            System.out.println("test type id  " +testType.getTestTypeId());
            lblTypeName.setText(testType.getTypeName());
            lblReferanceRange.setText(testType.getReferenceRange());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadAllTestReports() throws Exception {
        ObservableList<TestReportTM> tmList = FXCollections.observableArrayList();
        List<TestReportDTO> allTestReports = testReportBo.getAllTestReports();
        for (TestReportDTO dto : allTestReports) {
            Button btn = new Button("Delete");
            TestReportTM tm = new TestReportTM(
                    dto.getReportId(),
                    dto.getPatientId(),
                    dto.getTestCode(),
                    dto.getTestTypeId(),
                    dto.getDate(),
                    dto.getTime(),
                    dto.getResult(),
                    dto.getReferenceRange(),
                    btn);
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
                        if (testReportBo.deleteTestReport(tm.getReportId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllTestReports();
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
        tblTestReport.setItems(tmList);

    }

    public void btnPrintTestResultOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/TestReport.jrxml");
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
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/TestReport.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            HashMap hm = new HashMap();
            hm.put("reportId", txtReportId.getText());
            hm.put("patientId", cmbPatientId.getValue());
            hm.put("testCode", cmbTestCode.getValue());
            hm.put("testTypeId", cmbTestTypeId);
            hm.put("date", lblDate.getText());
            hm.put("time", lblTime.getText());
            hm.put("result", txtResult.getText());
            hm.put("referenceRange", lblReferanceRange.getText());
            hm.put("testName", lblTestName.getText());
            hm.put("typeName", lblTypeName.getText());


            JasperPrint jp = JasperFillManager.fillReport(jr, hm, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, true);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnReportData(ActionEvent actionEvent) {


    }

    public void btnNewNumberOnAction(ActionEvent actionEvent) {
        this.root.getChildren().clear();
        try {
            this.root.getChildren().add(FXMLLoader.load(
                    this.getClass().getResource("../view/TestReportForm.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    ObservableList<TestReportTableTM> detaillist = FXCollections.observableArrayList();

    public void btnAddOnAction(ActionEvent actionEvent) {
        Button btn = new Button("Delete");
        detaillist.add(new TestReportTableTM(
                txtReportId.getText(),
                cmbPatientId.getValue().toString(),
                cmbTestCode.getValue().toString(),
                cmbTestTypeId.getValue().toString(),
                lblDate.getText(),
                lblTime.getText(),
                txtResult.getText(),
                lblReferanceRange.getText(),btn));
        tblTestReport.setItems(detaillist);

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

    }
    private void ClearMethode(){

    }
}
