package dto;

import javafx.scene.control.DatePicker;

public class BookingDTO {
    private String bookingNo;
    private String date;
    private String time;
    private String patientId;

    public BookingDTO() {
    }

    public BookingDTO(String bookingNo, String date, String time, String patientId) {
        this.bookingNo = bookingNo;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
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

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingNo='" + bookingNo + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }
}
