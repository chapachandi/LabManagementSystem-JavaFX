package dao.custom.impl;

import dao.CrudUtil;
import dao.QueryDAO;

import java.sql.ResultSet;

public class QueryDAOImpl implements QueryDAO {
    //------------ genarate id --------------------
    @Override
    public String getPatientId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT patientId FROM Patient ORDER BY patientId DESC LIMIT 1");
        String pID = "P001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("P");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                pID = "P0" + (selectedId + 1);
            }else {
                pID = "P00" + (selectedId + 1);
            }

        }
        return pID;
    }

    @Override
    public String getTestCode() throws Exception {

        ResultSet set = CrudUtil.execute("SELECT testCode FROM Test ORDER BY testCode DESC LIMIT 1");
        String testCode = "T001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("T");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                testCode = "T0" + (selectedId + 1);
            }else {
                testCode = "T00" + (selectedId + 1);
            }

        }
        return testCode;
    }

    @Override
    public String getSampleId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT sampleId FROM SampleType ORDER BY sampleId DESC LIMIT 1");
        String sID = "S001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("S");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                sID = "S0" + (selectedId + 1);
            }else {
                sID = "S00" + (selectedId + 1);
            }

        }
        return sID;
    }

    @Override
    public String getUnitCode() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT unitCode FROM SampleUnit ORDER BY unitCode DESC LIMIT 1");
        String unitCode= "SU001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("SU");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                unitCode = "SU0" + (selectedId + 1);
            }else {
                unitCode = "SU00" + (selectedId + 1);

            }
        }
        return unitCode;
    }

    @Override
    public String getBookingNo() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT bookingNo FROM BookingNumber ORDER BY bookingNo DESC LIMIT 1");
        String bNo = "BN001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("BN");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                bNo = "BN0" + (selectedId + 1);
            }else {
                bNo = "BN00" + (selectedId + 1);
            }

        }
        return bNo;
    }

    @Override
    public String getTestTypeId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT testTypeId FROM TestType ORDER BY testTypeId DESC LIMIT 1");
        String testTypeId = "TTY001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("TTY");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                testTypeId = "TTY0" + (selectedId + 1);
            }else {
                testTypeId = "TTY00" + (selectedId + 1);
            }

        }
        return testTypeId;
    }

    @Override
    public String getReportId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT reportId FROM TestReport ORDER BY reportId DESC LIMIT 1");
        String tReportID = "RP001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("RP");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                tReportID = "RP0" + (selectedId + 1);
            }else {
                tReportID = "RP00" + (selectedId + 1);
            }

        }
        return tReportID;
    }

    @Override
    public String getRequestId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT requestId FROM Request ORDER BY requestId DESC LIMIT 1");
        String reqID = "R001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("R");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                reqID = "R0" + (selectedId + 1);
            }else {
                reqID = "R00" + (selectedId + 1);
            }

        }
        return reqID;

    }

    @Override
    public String getPaymentId() throws Exception {
        return null;
    }




}
