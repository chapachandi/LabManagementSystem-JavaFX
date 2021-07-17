package controller;


import bo.BoFactory;
import bo.custom.PatientBo;
import dto.PatientDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.PatientTM;

import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class PatientFormController {


    public TextField txtPatientId;
    public Label labaleGender;
    public ComboBox cmbTitle;
    public TextField txtFullName;
    public TextField txtAddress;
    public RadioButton rdbtnMale;
    public ToggleGroup GenderGroup;
    public RadioButton rdbtnFemale;
    public TextField txtAge;
    public Label txtName2;
    public TextField txtNic;
    public Label txtName11;
    public TextField txtMobile;
    public TableView tblPatient;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmOpareter;
    public Label lblPatientId;
    public Label lblTitle;
    public Label lblFullName;
    public Label lblAddress;
    public Label lblAge;
    public Label lblGender;
    public Label lblMobile;
    public Label lblNic;
    public AnchorPane root;
    public TableColumn clmTitle;
    public TableColumn clmAddress;
    public TableColumn clmFullName;
    public TableColumn clmGender;
    public TableColumn clmAge;
    public TableColumn clmNic;
    public TableColumn clmMobile;
    public TextField txtSearch;
    public Button btnSearch;

    PatientBo bo;

    ObservableList<String> observableList= FXCollections.observableArrayList();
    ObservableList <TableModel> obtable= FXCollections.observableArrayList();

    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.PATIENT);

        clmId.setCellValueFactory(new PropertyValueFactory("patientId"));
        clmTitle.setCellValueFactory(new PropertyValueFactory("title"));
        clmFullName.setCellValueFactory(new PropertyValueFactory("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory("address"));
        clmGender.setCellValueFactory(new PropertyValueFactory("gender"));
        clmAge.setCellValueFactory(new PropertyValueFactory("age"));
        clmNic.setCellValueFactory(new PropertyValueFactory("nic"));
        clmMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        clmOpareter.setCellValueFactory(new PropertyValueFactory("btn"));

        cmbTitle.setItems(observableList);
        loadAllPatients();
        genaratePatientId();
        //bo.getPatientId();

        observableList.add("Mr");
        observableList.add("Mrs");
        observableList.add("Miss");
        observableList.add("Thero");

        //------------------------------ set Listener-------------

        tblPatient.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((PatientTM) newValue);
                });


    }
    private void setData(PatientTM tm) {
        txtPatientId.setText(tm.getPatientId());
        cmbTitle.setValue(tm.getTitle());
        txtFullName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        labaleGender.setText(tm.getGender());
        txtAge.setText(tm.getAge());
        txtNic.setText(tm.getNic());
        txtMobile.setText(tm.getMobile()+"");
    }

    //------------------- Genarate Id ----------------

    private void genaratePatientId() throws Exception {
        String pId = bo.getPatientId();
        txtPatientId.setText(pId);

    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile(("(P)[0-9]{2}\\d")).matcher(txtPatientId.getText()).matches()) {
            txtPatientId.setStyle("-fx-border-color:blue;");
            txtFullName.requestFocus();
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtFullName.getText()).matches()) {
                txtFullName.setStyle("-fx-border-color:blue;");
                txtAddress.requestFocus();
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddress.getText()).matches()) {
                    txtAddress.setStyle("-fx-border-color:blue;");
                    txtAge.requestFocus();
                    if (Pattern.compile("^[0-9]{2}[ year|month|weeks|days]*\\b$").matcher(txtAge.getText()).matches()) {
                        txtAge.setStyle("-fx-border-color:blue;");
                        txtNic.requestFocus();
                        if (Pattern.compile("^[0-9]{9}[vVxX]$").matcher(txtNic.getText()).matches()) {
                            txtNic.setStyle("-fx-border-color:blue;");
                            txtMobile.requestFocus();
                            if (Pattern.compile("^[0-9]{10}$").matcher(txtMobile.getText()).matches()) {
                                txtMobile.setStyle("-fx-border-color:blue;");

                                boolean isSaved = bo.savePatient(
                                        new PatientDTO(txtPatientId.getText(),cmbTitle.getValue().toString(),
                                                txtFullName.getText(),
                                                txtAddress.getText(),
                                                labaleGender.getText(),txtAge.getText(),
                                                txtNic.getText(),Integer.parseInt(txtMobile.getText()))
                                );
                                System.out.println(isSaved);
                                loadAllPatients();
                                clearMethode();

                            } else {
                                txtMobile.setStyle("-fx-border-color:red;");
                                txtMobile.requestFocus();
                            }
                        } else {
                            txtNic.setStyle("-fx-border-color:red;");
                            txtNic.requestFocus();
                        }
                    } else {
                        txtAge.setStyle("-fx-border-color:red;");
                        txtAge.requestFocus();
                    }
                } else {
                    txtAddress.setStyle("-fx-border-color:red;");
                    txtAddress.requestFocus();
                }
            } else {
                txtFullName.setStyle("-fx-border-color:red;");
                txtFullName.requestFocus();
            }
        }else {
            txtPatientId.setStyle("-fx-border-color:red;");
            txtPatientId.requestFocus();
        }



    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile(("(P)[0-9]{2}\\d")).matcher(txtPatientId.getText()).matches()) {
            txtPatientId.setStyle("-fx-border-color:blue;");
            txtFullName.requestFocus();
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtFullName.getText()).matches()) {
                txtFullName.setStyle("-fx-border-color:blue;");
                txtAddress.requestFocus();
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddress.getText()).matches()) {
                    txtAddress.setStyle("-fx-border-color:blue;");
                    txtAge.requestFocus();
                    if (Pattern.compile("^[0-9]{2}[ year|month|weeks|days]*\\b$").matcher(txtAge.getText()).matches()) {
                        txtAge.setStyle("-fx-border-color:blue;");
                        txtNic.requestFocus();
                        if (Pattern.compile("^[0-9]{9}[vVxX]$").matcher(txtNic.getText()).matches()) {
                            txtNic.setStyle("-fx-border-color:blue;");
                            txtMobile.requestFocus();
                            if (Pattern.compile("^[0-9]{10}$").matcher(txtMobile.getText()).matches()) {
                                txtMobile.setStyle("-fx-border-color:blue;");
                                boolean isUpdate = bo.updatePatient(new PatientDTO(txtPatientId.getText(),
                                        cmbTitle.getValue().toString(),txtFullName.getText(),txtAddress.getText(),
                                        labaleGender.getText(),txtAge.getText(),
                                        txtNic.getText(),Integer.parseInt(txtMobile.getText())));
                                System.out.println(isUpdate);
                                loadAllPatients();
                                clearMethode();
                            } else {
                                txtMobile.setStyle("-fx-border-color:red;");
                                txtMobile.requestFocus();

                            }
                        } else {
                            txtNic.setStyle("-fx-border-color:red;");
                            txtNic.requestFocus();
                        }
                    } else {
                        txtAge.setStyle("-fx-border-color:red;");
                        txtAge.requestFocus();
                    }
                } else {
                    txtAddress.setStyle("-fx-border-color:red;");
                    txtAddress.requestFocus();
                }
            } else {
                txtFullName.setStyle("-fx-border-color:red;");
                txtFullName.requestFocus();
            }
        }else {
            txtPatientId.setStyle("-fx-border-color:red;");
            txtPatientId.requestFocus();
        }


    }

    private void clearMethode(){
        txtPatientId.setText("");
        txtFullName.setText("");
        txtAddress.setText("");
        txtAge.setText("");
        txtNic.setText("");
        txtMobile.setText("");
    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }



    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource(
                "../view/ReqestAccessForm.fxml")));
    }


    private void loadAllPatients() throws Exception {
        ObservableList<PatientTM> tmList = FXCollections.observableArrayList();
        List<PatientDTO> allPatients = bo.getAllPatients();
        for (PatientDTO dto : allPatients) {
            Button btn = new Button("Delete");
            PatientTM tm = new PatientTM(dto.getPatientId(),dto.getTitle(), dto.getName(),
                    dto.getAddress(), dto.getGender(),dto.getAge(),dto.getNic(),dto.getMobile(),btn);
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
                        if (bo.deletePatient(tm.getPatientId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllPatients();
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
        tblPatient.setItems(tmList);

    }

    public void radioBtnOnAction(ActionEvent actionEvent) {
        String message = "";
        if(rdbtnMale.isSelected()){
            message += rdbtnMale.getText()+"\n";
        }
        if(rdbtnFemale.isSelected()){
            message += rdbtnFemale.getText()+"\n";
        }
        labaleGender.setText(message);

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String s = txtSearch.getText();
        try {
            PatientDTO patient = bo.getPatientDto(s);
            if(patient!=null) {
                txtPatientId.setText(patient.getPatientId());
                cmbTitle.setValue(patient.getTitle());
                txtFullName.setText(patient.getName());
                txtAddress.setText(patient.getAddress());
                txtNic.setText(patient.getNic());

                txtAge.setText(patient.getAge());
                txtMobile.setText(patient.getMobile() + "");
                String gender=patient.getGender();
                lblGender.setText(gender);

            }else{
                new Alert(
                        Alert.AlertType.WARNING,
                        "Can't found Patient....!\nPlese save this patient...!").show();
            }
        } catch (NullPointerException e){} catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getGender(String gender) {
        switch(gender){
            case "Female":
                rdbtnFemale.setSelected(true);

                break;
            case "Male":
                rdbtnMale.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void btnNewPatientOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/PatientForm.fxml")));
    }
}
