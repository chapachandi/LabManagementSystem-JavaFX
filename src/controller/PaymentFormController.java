package controller;

import bo.BoFactory;
import bo.custom.PaymentBo;
import db.DBConnection;
import dto.PatientDTO;
import dto.PaymentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.PatientTM;
import view.tm.PaymentTM;
import view.tm.SampleTypeTM;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentFormController {

    public TableColumn clmPayId;
    public AnchorPane root1;
    public TableView tblPayment;
    public TableColumn clmPatientId;
    public TableColumn clmTestName;
    public TableColumn clmDate;
    public TableColumn clmTime;
    public TableColumn clmSubTotal;
    public TableColumn clmAmount;
    public TableColumn clmBalance;
    public TableColumn clmPaymentType;
    public TextField txtSearch;
    public TableColumn clmOparetor;

    PaymentBo bo;

    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.PAYMENT);

        clmPayId.setCellValueFactory(new PropertyValueFactory("paymentId"));
        clmPatientId.setCellValueFactory(new PropertyValueFactory("patientId"));
        clmTestName.setCellValueFactory(new PropertyValueFactory("testName"));
        clmDate.setCellValueFactory(new PropertyValueFactory("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory("time"));
        clmSubTotal.setCellValueFactory(new PropertyValueFactory("subtotal"));
        clmAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        clmBalance.setCellValueFactory(new PropertyValueFactory("balance"));
        clmPaymentType.setCellValueFactory(new PropertyValueFactory("paymentType"));
        clmOparetor.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllPayments();

    }

    private void setData(SampleTypeTM newValue) {
    }
    private void loadAllPayments() throws Exception {
        ObservableList<PaymentTM> tmList = FXCollections.observableArrayList();
        List<PaymentDTO> allPayments = bo.getAllPayments();
        for (PaymentDTO dto : allPayments) {
            Button btn = new Button("Delete");
            PaymentTM tm = new PaymentTM(
                    dto.getPaymentId(),
                    dto.getPatientId(),
                    dto.getTestName(),
                    dto.getDate(),
                    dto.getTime(),
                    dto.getSubtotal(),
                    dto.getAmount(),
                    dto.getBalance(),
                    dto.getPaymentType(),
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
                        if (bo.deletePayment(String.valueOf(tm.getPaymentId()))) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllPayments();
                            System.out.println("Aa");
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
       tblPayment.setItems(tmList);

    }


    public void btnPrintPaymentOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/PaymentDetails.jrxml");
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
