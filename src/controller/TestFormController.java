package controller;

import bo.BoFactory;
import bo.custom.TestBo;
import bo.custom.TestTypeBo;
import dto.PatientDTO;
import dto.TestDTO;
import dto.TestTypeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.PatientTM;
import view.tm.TestTM;
import view.tm.TestTypeTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class TestFormController {

    public TextField txtTestCode;
    public TextField txtTestName;
    public TextField txtPrice;
    
    public TableColumn clmCode;
    public TableColumn clmTestName;
    public TableColumn clmPrice;
    public TableColumn clmOpareter;
    public Label lblTestName;
    public TextField txtTypeId;
    public ComboBox cmbTestCode;
    public TextField txtTestName1;
    public TextField txtPrice1;
    public TableView tblTestType;
    public TableView tblTest;
    public TableColumn clmTypeId;
    public TableColumn clmTestCode;
    public TableColumn clmTypeName;
    public TableColumn clmRange;
    public TableColumn clmOpareter1;
    public TextField txtTypeName;
    public TextField txtReferaceRange;
    public AnchorPane root;
    public TextField txtSearchCode;
    public TextField txtSearchId;

    TestBo bo;
    TestTypeBo testTypeBo;

    public void initialize() throws Exception {

        bo = BoFactory.getInstance().getBo(BoFactory.BOType.TEST);
        testTypeBo = BoFactory.getInstance().getBo(BoFactory.BOType.TESTTYPE);

        clmCode.setCellValueFactory(new PropertyValueFactory("testCode"));
        clmTestName.setCellValueFactory(new PropertyValueFactory("testName"));
        clmPrice.setCellValueFactory(new PropertyValueFactory("price"));
        clmOpareter.setCellValueFactory(new PropertyValueFactory("btn"));

        clmTypeId.setCellValueFactory(new PropertyValueFactory("testTypeId"));
        clmTestCode.setCellValueFactory(new PropertyValueFactory("testCode"));
        clmTypeName.setCellValueFactory(new PropertyValueFactory("typeName"));
        clmRange.setCellValueFactory(new PropertyValueFactory("referenceRange"));
        clmOpareter1.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllTests();
        loadAllTestTypes();
        getAllTestCode();
        genarateTestCode();
        genarateTypeId();

        tblTest.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((TestTM) newValue);
                });

        tblTestType.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setDataType((TestTypeTM) newValue);
                });


    }
    private void genarateTestCode() throws Exception {
        try {
            String lastId = bo.getTestCode();
            txtTestCode.setText(lastId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void genarateTypeId() {

        try {
            txtTypeId.setText(testTypeBo.getTestTypeId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setDataType(TestTypeTM tm) {
        txtTypeId.setText(tm.getTestTypeId());
        txtTestCode.setText(tm.getTestCode());
        txtTypeName.setText(tm.getTypeName());
        txtReferaceRange.setText(tm.getReferenceRange());
    }

    private void setData(TestTM tm) {
        txtTestCode.setText(tm.getTestCode());
        txtTestName.setText(tm.getTestName());
        txtPrice.setText(tm.getPrice()+"");
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void getDataOnAction(ActionEvent actionEvent) {
    }


    public void btnSaveTestOnAction(ActionEvent actionEvent) throws Exception {
        boolean isSaved = bo.saveTest(
                new TestDTO(txtTestCode.getText(),txtTestName.getText(),Double.parseDouble(txtPrice.getText()))
        );
        System.out.println(isSaved);
        loadAllTests();
    }

    public void btnUpdateTestOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdate = bo.updateTest(new TestDTO(txtTestCode.getText(),txtTestName.getText(),Double.parseDouble(txtPrice.getText())));
        System.out.println(isUpdate);
        loadAllTests();
    }
    public void btnClearTestOnAction(ActionEvent actionEvent) {
        txtTestCode.setText("");
        txtTestName.setText("");
        txtPrice.setText("");
    }

    private void loadAllTests() throws Exception {
        ObservableList<TestTM> tmList = FXCollections.observableArrayList();
        List<TestDTO> allTests = bo.getAllTests();
        for (TestDTO dto : allTests) {
            Button btn = new Button("Delete");
            TestTM tm = new TestTM(dto.getTestCode(),dto.getTestName(),dto.getPrice(),btn);
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
                        if (bo.deleteTest(tm.getTestCode())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllTests();
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
        tblTest.setItems(tmList);


    }

    public void getDataTestTypeOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveTestTypeOnAction(ActionEvent actionEvent) throws Exception {

        boolean isSavedType = testTypeBo.saveTestType(
                new TestTypeDTO(txtTypeId.getText(),cmbTestCode.getValue().toString(),txtTypeName.getText(),txtReferaceRange.getText()));

        System.out.println(isSavedType);
        loadAllTestTypes();

    }
    private void loadAllTestTypes() throws Exception {
        ObservableList<TestTypeTM> tmList = FXCollections.observableArrayList();
        List<TestTypeDTO> allTestTypes = testTypeBo.getAllTestTypes();
        for (TestTypeDTO dto : allTestTypes) {
            Button btn = new Button("Delete");
            TestTypeTM tm = new TestTypeTM(dto.getTestTypeId(),dto.getTestCode(),dto.getTypeName(),
                    dto.getReferenceRange(),btn);
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
                        if (testTypeBo.deleteTestType(tm.getTestTypeId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllTestTypes();
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
        tblTestType.setItems(tmList);


    }


    public void btnClearTypeOnAction(ActionEvent actionEvent) {
        txtTypeId.setText("");
        txtTypeName.setText("");
        txtReferaceRange.setText("");
        lblTestName.setText("");
    }

    public void btnUpdateTestTypeOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile(("(B)[0-9]{2}\\d")).matcher(txtTestCode.getText()).matches()) {
            txtTestCode.setStyle("-fx-border-color:blue;");
            txtTestName.requestFocus();
            if (Pattern.compile("^[A-Za-z'\\s]$").matcher(txtTestName.getText()).matches()) {
                txtTestName.setStyle("-fx-border-color:blue;");
                txtPrice.requestFocus();
                if (Pattern.compile("^[0-9]{7,}.|[0-9]{1,}.[0-9]$").matcher(txtPrice.getText()).matches()) {
                    txtPrice.setStyle("-fx-border-color:blue;");

                    boolean isUpdateType = testTypeBo.updateTestType(new TestTypeDTO(txtTypeId.getText(),
                            cmbTestCode.getValue().toString(), txtTypeName.getText(), txtReferaceRange.getText()));
                    System.out.println(isUpdateType);
                    loadAllTestTypes();

                } else {
                    txtPrice.setStyle("-fx-border-color:red;");
                    txtPrice.requestFocus();
                }
            } else {
                txtTestName.setStyle("-fx-border-color:red;");
                txtTestName.requestFocus();
            }
        }

    }
    public void getAllTestCode()throws Exception{
        try{
            List<TestDTO> list=bo.getAllTests();
            for (TestDTO test : list){
                cmbTestCode.getItems().add(test.getTestCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void cmbTestCodeOnAction(ActionEvent actionEvent) {
        try {
            String t= (String) cmbTestCode.getValue();
            System.out.println("test testCode t" +t);
            TestDTO test = bo.getTest(t);
            System.out.println("test testCode " +test.getTestCode());
            lblTestName.setText(test.getTestName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewTestOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("../view/TestForm.fxml")));
    }

    public void txtSearchCodeOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchCodeOnAction(ActionEvent actionEvent) {


    }

    public void btnNewTypeOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("../view/TestForm.fxml")));
    }

    public void btnSearchIdOnAction(ActionEvent actionEvent) {
        try {
            String s = txtSearchId.getText();
            TestTypeDTO type = testTypeBo.getTestType(s);
            if(type!=null) {
               txtSearchId.setText(type.getTestTypeId());
               cmbTestCode.setValue(type.getTestCode());
               lblTestName.setText(type.getTypeName());
               txtTypeName.setText(type.getTypeName());
               txtReferaceRange.setText(type.getReferenceRange());
            }else{
                new Alert(
                        Alert.AlertType.WARNING,
                        "Can't found Test Type....!\nPlese save this test type...!").show();
            }
        } catch (NullPointerException e){} catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

        try {
            String s = txtSearchCode.getText();
            TestDTO code = bo.getTest(s);
            if(code!=null) {
                txtTestCode.setText(code.getTestCode());
                txtTestName.setText(code.getTestName());
                txtPrice.setText(code.getPrice()+"");


            }else{
                new Alert(
                        Alert.AlertType.WARNING,
                        "Can't found Test....!\nPlese save this test...!").show();
            }
        } catch (NullPointerException e){} catch (Exception e) {
            e.printStackTrace();
        }
    }
}
