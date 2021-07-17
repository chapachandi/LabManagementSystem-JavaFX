package view.tm;

import javafx.scene.control.Button;

public class RequestTM {
    private String requestId;
    private String date;
    private String time;
    private String patientId;
    private Button btn;

    public RequestTM() {
    }

    public RequestTM(String requestId, String date, String time, String patientId, Button btn) {
        this.requestId = requestId;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.btn = btn;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
