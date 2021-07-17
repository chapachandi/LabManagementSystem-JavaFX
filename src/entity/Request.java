package entity;

public class Request implements SuperEntity{
    private String requestId;
    private String date;
    private String time;
    private String patientId;

    public Request() {
    }

    public Request(String requestId, String date, String time, String patientId) {
        this.requestId = requestId;
        this.date = date;
        this.time = time;
        this.patientId = patientId;
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

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }
}
