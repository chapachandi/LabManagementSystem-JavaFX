package controller;


import bo.BoFactory;
import bo.custom.BookingBo;
import bo.custom.PatientBo;
import com.sun.deploy.uitoolkit.ui.ConsoleWindow;
import dto.BookingDTO;
import dto.PatientDTO;
import dto.TestDetailDTO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.ir.LiteralNode;
import org.apache.poi.ss.usermodel.DateUtil;
import view.tm.BookingTM;
import view.tm.PatientTM;
import view.tm.RequestTableTM;
import view.tm.TestDetailTM;


import javax.swing.table.TableModel;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class BookingFormController {


    public TextField txtBookingNo;
    public ComboBox cmbPatientId1;
    public TextField txtTime;
    public DatePicker txtDate;
    public TableView tblBooking;
    public TableColumn clmBookingNo;
    public TableColumn clmDate;
    public TableColumn clmTime;
    public TableColumn clmPatientIId;
    public TableColumn clmOpareter;
    public TextField txtSearchBookinNo;
    public AnchorPane root;
    public DatePicker date;
    public TextField txtSearch;
    public TextField txtSearchTime;
    public Label lblBooking;
    public TextField txtSearchDate;

    BookingBo bo;
    PatientBo pBo;


    ObservableList<String> observableList= FXCollections.observableArrayList();


    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.BOOKINGNUMBER);
        pBo= BoFactory.getInstance().getBo(BoFactory.BOType.PATIENT);

        clmBookingNo.setCellValueFactory(new PropertyValueFactory("bookingNo"));
        clmDate.setCellValueFactory(new PropertyValueFactory("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory("time"));
        clmPatientIId.setCellValueFactory(new PropertyValueFactory("patientId"));
        clmOpareter.setCellValueFactory(new PropertyValueFactory("btn"));

        DatePicker datePicker = new DatePicker();


        getAllPatient();
        genarateBookingNo();
        loadAllBookings();

        verify();

        //------------------------------ set Listener-------------

        tblBooking.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((BookingTM) newValue);
                });

    }

    private void setData(BookingTM tm) {
        txtBookingNo.setText(tm.getBookingNo());
       // date.setValue(tm.getDate());
        txtTime.setText(tm.getTime());
        cmbPatientId1.setValue(tm.getPatientId());
    }

    public void verify() {
        LocalDate date = LocalDate.of(2020, 10, 1);
        if (date.toString().equals(date)) {
            System.out.println("Success!!!");
        }
    }

    //-------------Gearate Number----------

    private void genarateBookingNo() throws Exception {
        String bookingNo = bo.getBookingNo();
        txtBookingNo.setText(bookingNo);

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (cmbPatientId1.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Patient", ButtonType.OK).show();
            cmbPatientId1.requestFocus();
            return;
        }


        if (Pattern.compile(("^(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$")).matcher(txtTime.getText()).matches()) {
            txtTime.setStyle("-fx-border-color:blue;");

            boolean isSaved = false;
            try {
                isSaved = bo.saveBooking(
                        new BookingDTO(
                                txtBookingNo.getText(),
                                date.getValue().toString(),
                                txtTime.getText(),
                                cmbPatientId1.getValue().toString()
                        ));
                System.out.println(isSaved);
                loadAllBookings();
                clearBooking();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(isSaved);
        }else {
            txtTime.setStyle("-fx-border-color:red;");
            txtTime.requestFocus();
        }


    }
    private void clearBooking(){
        txtBookingNo.setText("");
        txtTime.setText("");
        cmbPatientId1.setValue("");
    }

    public void getAllPatient() throws Exception {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<PatientDTO> arrayList = pBo.getAllPatients();

        for (PatientDTO p:arrayList){
            observableList.add((p.getPatientId()));

        }
        cmbPatientId1.setItems(observableList);



    }
    private void loadAllBookings() throws Exception {
        ObservableList<BookingTM> tmList = FXCollections.observableArrayList();
        List<BookingDTO> allBookings = bo.getAllBookings();
        for (BookingDTO dto : allBookings) {
            Button btn = new Button("Delete");
            BookingTM tm = new BookingTM(dto.getBookingNo(),dto.getDate(),dto.getTime(),dto.getPatientId(),btn);
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
                        if (bo.deleteBooking(tm.getBookingNo())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllBookings();
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
        tblBooking.setItems(tmList);
    }


    public void btnUpdateOnAction(ActionEvent actionEvent)  {

        if (cmbPatientId1.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Select a Patient", ButtonType.OK).show();
            cmbPatientId1.requestFocus();
            return;
        }
        if (Pattern.compile(("(B)[0-9]{2}\\d")).matcher(txtBookingNo.getText()).matches()) {
            txtBookingNo.setStyle("-fx-border-color:blue;");
            if (Pattern.compile(("^(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$")).matcher(txtTime.getText()).matches()) {
                txtTime.setStyle("-fx-border-color:blue;");
                try {
                    boolean isUpdate = bo.updateBooking(
                            new BookingDTO(
                                    txtBookingNo.getText(),
                                    date.getValue().toString(),
                                    txtTime.getText(),
                                    cmbPatientId1.getValue().toString()
                            ));
                    System.out.println(isUpdate);
                    loadAllBookings();
                    clearBooking();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                txtTime.setStyle("-fx-border-color:red;");
                txtTime.requestFocus();
            }
        }else {
            txtBookingNo.setStyle("-fx-border-color:red;");
            txtBookingNo.requestFocus();
        }


    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnNewPatientOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/PatientForm.fxml")));
    }

    public void txtSearchBookingIdOnAction(ActionEvent actionEvent) {




    }

    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("../view/ReqestAccessForm.fxml")));

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            String date = txtSearchDate.getText();
            String time = txtSearchTime.getText();
            BookingDTO bookingDTO = bo.getBookingDTO(date, time);
            if(bookingDTO==null){
                lblBooking.setText("No Booking Schedule");
            }else{
                lblBooking.setText("Booking");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewNumberOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("../view/BookingForm.fxml")));
    }
}
