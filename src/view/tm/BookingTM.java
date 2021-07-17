package view.tm;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import rex.utils.S;

public class BookingTM {
    private String bookingNo;
    private String date;
    private String time;
    private String patientId;
    private Button btn;

    public BookingTM() {
    }

    public BookingTM(String bookingNo, String date, String time, String patientId, Button btn) {
        this.bookingNo = bookingNo;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.btn = btn;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
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
