package view.tm;

import javafx.scene.control.Button;

public class SampleUnitTM {
    private String unitCode;
    private String sampleId;
    private String testName;
    private String date;
    private String time;
    private String patientId;
    private Button btn;

    public SampleUnitTM() {
    }

    public SampleUnitTM(String unitCode, String sampleId, String testName, String date, String time, String patientId, Button btn) {
        this.unitCode = unitCode;
        this.sampleId = sampleId;
        this.testName = testName;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.btn = btn;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
