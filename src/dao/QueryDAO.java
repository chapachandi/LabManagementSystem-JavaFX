package dao;

public interface QueryDAO extends SuperDAO {
    public String getPatientId()throws Exception;
    public String getTestCode()throws Exception;
    public String getSampleId()throws Exception;
    public String getUnitCode()throws Exception;
    public String getBookingNo()throws Exception;
    public String getTestTypeId()throws Exception;
    public String getReportId()throws Exception;
    public String getRequestId()throws Exception;
    public String getPaymentId()throws Exception;



}
