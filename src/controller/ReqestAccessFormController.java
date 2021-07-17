package controller;


import bo.BoFactory;
import bo.custom.*;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.*;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ReqestAccessFormController {

    public Label lblFullName;
    public Label lblReceiptNo;
    public TableView<TestDetailTM> tblRequest;
    public TableColumn clmReceiptNo;
    public TableColumn clmTestName;
    public TableColumn clmPrice;
    public TableColumn clmTotal;
    public TableColumn clmOpareter;
    public Label lblSubtotalOnAction;
    public TextField txtPayAmount;
    public ComboBox cmbPaymentType;
    public TextField txtBalanece;
    public Label lblTestName;
    public Label lblPrice;
    public Label lblUnitCode;
    public Label lblSampleType;
    public DatePicker txtDate1;
    public ComboBox cmbSampleId;
    public AnchorPane root;
    public ComboBox cmbPatientId;
    public TextField txtTime;
    public ComboBox cmbTestCode;
    public AnchorPane root1;
    public Label lblRequestId;
    public TableColumn clmRequestId;
    public TableView tblRequestAccess;
    public TextField txtPaymentId;
    public Label lblDate;
    public Label lblTime;
    public Label lblSubtotal;
    public Button btnPrintBill;

    TestDetailBo testDetailBo;
    TestBo testBo;
    PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BOType.PATIENT);
    SampleTypeBo sampleTypeBo;
    RequestBo requestBo;
    SampleUnitBo sampleUnitBo;
    PaymentBo paymentBo;
    PlaceRequestBo placeRequestBo;
    int paymentId;
    String testCode;
    String unitCode;



    ObservableList<String> observableList = FXCollections.observableArrayList();
    ObservableList<RequestTableTM> obtable = FXCollections.observableArrayList();

    public ReqestAccessFormController() throws Exception {
    }

    public void initialize() throws Exception {

        testDetailBo = BoFactory.getInstance().getBo(BoFactory.BOType.TESTDETAIL);
        testBo = BoFactory.getInstance().getBo(BoFactory.BOType.TEST);
        requestBo = BoFactory.getInstance().getBo(BoFactory.BOType.REQUEST);
        sampleTypeBo = BoFactory.getInstance().getBo(BoFactory.BOType.SAMPLETYPE);
        sampleUnitBo = BoFactory.getInstance().getBo(BoFactory.BOType.SAMPLEUNIT);
        paymentBo = BoFactory.getInstance().getBo(BoFactory.BOType.PAYMENT);
        placeRequestBo = BoFactory.getInstance().getBo(BoFactory.BOType.PLACEREQ);

        observableList.add("Cash");
        observableList.add("Card");

        cmbPaymentType.setItems(observableList);


        clmRequestId.setCellValueFactory(new PropertyValueFactory("requestId"));
        clmTestName.setCellValueFactory(new PropertyValueFactory("testName"));
        clmPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clmTotal.setCellValueFactory(new PropertyValueFactory("tot"));
        clmOpareter.setCellValueFactory(new PropertyValueFactory("btn"));

        getAllPatient();
        getAllSampleType();
        getAllTests();


        //loadAllTestDetails();

        lblDate.setText((String.valueOf(LocalDate.now())));
        setLBLTime();

        genarateRequestId();
        genarateUnitCode();


    }

    //------------ genarate id -------------------

    private void genarateRequestId() {

        try {
            String lastId = requestBo.getRequestId();
            lblRequestId.setText(lastId);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void genarateUnitCode() throws Exception {
        String ucode = sampleUnitBo.getUnitCode();
        lblUnitCode.setText(ucode);

    }


    private void setLBLTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
                    lblTime.setText(LocalDateTime.now().format(formatter));
                }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void setData(SampleTypeTM newValue) {
    }

    private void loadAllTestDetails() throws Exception {

        ObservableList<TestDetailTM> tmList = FXCollections.observableArrayList();
        List<TestDetailDTO> allTestDetails = testDetailBo.getAllTestDetails();

        for (TestDetailDTO dto : allTestDetails) {
            Button btn = new Button("Delete");
            TestDetailTM tm = new TestDetailTM(
                    dto.getRequestId(),
                    dto.getPaymentId(),
                    dto.getTestCode(),
                    dto.getUnitCode(),
                    dto.getTestName(),
                    dto.getPrice(),
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
                        if (testDetailBo.deleteTestDetail(tm.getRequestId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllTestDetails();
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
        tblRequestAccess.setItems(tmList);

    }

    public void getAllPatient() {
        try {
            List<PatientDTO> list = patientBo.getAllPatients();
            for (PatientDTO p : list) {
                cmbPatientId.getItems().add(p.getPatientId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAllSampleType() throws Exception {
        try {
            List<SampleTypeDTO> list = sampleTypeBo.getAllSampleTypes();
            for (SampleTypeDTO sampleType : list) {
                cmbSampleId.getItems().add(sampleType.getSampleId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAllTests() throws Exception {
        try {
            List<TestDTO> list = testBo.getAllTests();
            for (TestDTO test : list) {
                cmbTestCode.getItems().add(test.getTestCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void btnNewPatientOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/PatientForm.fxml")));
    }

    public void btnRequestOnAction(ActionEvent actionEvent) {

    }

    public void txtPayAmountOnAction(ActionEvent actionEvent){
        double subtot = Double.parseDouble(lblSubtotal.getText());
        double pay = Double.parseDouble(txtPayAmount.getText());
        if (subtot <= pay) {
            double balance = pay - subtot;
            txtBalanece.setText(String.valueOf(balance));
        } else {
            txtPayAmount.requestFocus();
        }

    }

    public void btnPayOnaction(ActionEvent actionEvent) throws Exception {

        //------------------- transaction ---------------------------------
        if (cmbPaymentType.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Payment Type", ButtonType.OK).show();
            cmbPaymentType.requestFocus();
            return;
        }
        if (Pattern.compile("^[0-9]{7,}.|[0-9]{1,}.[0-9]$").matcher(txtPayAmount.getText()).matches()) {
            txtPayAmount.setStyle("-fx-border-color:blue;");

            try {
                boolean isAdd = placeRequestBo.placeRequest(
                        new PaymentDTO( (paymentBo.getLastPaymentId() + 1),
                                cmbPatientId.getValue().toString(),
                                lblTestName.getText(),
                                lblDate.getText(),
                                lblTime.getText(),
                                Double.parseDouble(lblSubtotal.getText()),
                                Double.parseDouble(txtPayAmount.getText()),
                                Double.parseDouble(txtBalanece.getText()),
                                cmbPaymentType.getValue().toString()),
                        new RequestDTO(
                                lblRequestId.getText(),
                                lblDate.getText(),
                                lblTime.getText(),
                                cmbPatientId.getValue().toString()),
                        new SampleUnitDTO(
                                lblUnitCode.getText(),
                                cmbSampleId.getValue().toString(),
                                lblTestName.getText(),
                                lblDate.getText(),
                                lblTime.getText(),
                                cmbPatientId.getValue().toString()),
                                detaillist
                );
                System.out.println(isAdd);


            } catch (Exception ex) {
                new Alert(Alert.AlertType.WARNING, ex.getMessage()).show();
            }
        }else {
            txtPayAmount.setStyle("-fx-border-color:red;");
            txtPayAmount.requestFocus();
        }



    }

    private void clearRequestAccessForm() {
        try {
            tblRequestAccess.getItems().clear();
            cmbPatientId.getSelectionModel().clearSelection();
            cmbSampleId.getSelectionModel().clearSelection();
            cmbTestCode.getSelectionModel().clearSelection();
        } catch (NullPointerException e) {
        }
    }

    ObservableList<RequestTableTM> detaillist = FXCollections.observableArrayList();

    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {

        if (cmbPatientId.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Patient", ButtonType.OK).show();
            cmbPatientId.requestFocus();
            return;
        }
        if (cmbSampleId.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Sample Id", ButtonType.OK).show();
            cmbSampleId.requestFocus();
            return;
        }
        if (cmbTestCode.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Test Code", ButtonType.OK).show();
            cmbTestCode.requestFocus();
            return;
        }

        Button btn = new Button("Delete");
        detaillist.add(new RequestTableTM(
                lblRequestId.getText(),
                (paymentBo.getLastPaymentId() + 1),
                cmbTestCode.getValue().toString(),
                lblUnitCode.getText(),
                lblTestName.getText(),
                Double.parseDouble(lblPrice.getText()),
                Double.parseDouble(lblPrice.getText()),
                btn));
        tblRequestAccess.setItems(detaillist);
        calculateTotal();

    }

    //--------------- combo load ----------------------------------

    public void cmbPatientSelectOnAction(ActionEvent actionEvent) throws Exception {

        try {
            String p = (String) cmbPatientId.getValue();
            System.out.println("patient id p" + p);
            PatientDTO patient = patientBo.getPatient(p);
            System.out.println("patient id " + patient.getPatientId());
            lblFullName.setText(patient.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void cmbTestCodeOnAction(ActionEvent actionEvent) {
        try {
            String t = (String) cmbTestCode.getValue();
            System.out.println("test testCode t" + t);
            TestDTO test = testBo.getTest(t);
            System.out.println("test testCode " + test.getTestCode());
            lblTestName.setText(test.getTestName());
            lblPrice.setText(test.getPrice() + "");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbSampleIdOnAction(ActionEvent actionEvent) {
        try {
            String s = (String) cmbSampleId.getValue();
            System.out.println("SampleType sampleId s" + s);
            SampleTypeDTO sampleType = sampleTypeBo.getSampleType(s);
            System.out.println("SampleType sampleId " + sampleType.getSampleId());
            lblSampleType.setText(sampleType.getTypeName());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void lblSubtotalOnAction(MouseEvent mouseEvent) {

    }

    private void calculateTotal() {

        double total = 0;
        for (int i = 0; i < tblRequestAccess.getItems().size(); i++) {
            TableColumn tableColumn = (TableColumn) tblRequestAccess.getColumns().get(3);

            total += (double) tableColumn.getCellObservableValue(i).getValue();
        }
        lblSubtotal.setText(String.valueOf(total));


    }


    public void cmbPaymentTypeOnAction(ActionEvent actionEvent) {

    }

    public void btnPrintBillOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Payment.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            HashMap hm = new HashMap();
            hm.put("requestId", lblRequestId.getText());
            hm.put("name", lblFullName.getText());
            hm.put("testName", lblTestName.getText());
            hm.put("price", lblPrice.getText());
            hm.put("date", lblDate.getText());
            hm.put("time", lblTime.getText());
            hm.put("subtotal", lblSubtotal.getText());
            hm.put("amount", txtPayAmount.getText());
            hm.put("balance", txtBalanece.getText());
            hm.put("payementType", cmbPaymentType.getValue());


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
}
