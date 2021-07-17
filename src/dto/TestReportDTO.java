package dto;

public class TestReportDTO {
    private String reportId;
    private String patientId;
    private String testCode;
    private String testTypeId;
    private String date;
    private String time;
    private String result;
    private String referenceRange;

    public TestReportDTO() {
    }

    public TestReportDTO(String reportId, String patientId, String testCode, String testTypeId, String date, String time, String result, String referenceRange) {
        this.reportId = reportId;
        this.patientId = patientId;
        this.testCode = testCode;
        this.testTypeId = testTypeId;
        this.date = date;
        this.time = time;
        this.result = result;
        this.referenceRange = referenceRange;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(String testTypeId) {
        this.testTypeId = testTypeId;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReferenceRange() {
        return referenceRange;
    }

    public void setReferenceRange(String referenceRange) {
        this.referenceRange = referenceRange;
    }

    @Override
    public String toString() {
        return "TestReportDTO{" +
                "reportId='" + reportId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", testCode='" + testCode + '\'' +
                ", testTypeId='" + testTypeId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", result='" + result + '\'' +
                ", referenceRange='" + referenceRange + '\'' +
                '}';
    }
}
