package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.TestReportDAO;
import entity.Test;
import entity.TestReport;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestReportDAOImpl implements TestReportDAO {
    @Override
    public boolean save(TestReport testReport) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO TestReport VALUES(?,?,?,?,?,?,?,?)",
                        testReport.getReportId(),testReport.getPatientId(),testReport.getTestCode(),testReport.getTestTypeId(),
                        testReport.getDate(),testReport.getTime(),testReport.getResult(),testReport.getReferenceRange());
    }

    @Override
    public boolean update(TestReport testReport) throws Exception {
        return CrudUtil.execute("UPDATE TestReport SET  patientId=?, testCode=?, date=?, time=?, result=?, referenceRange=? WHERE reportId=?",
                testReport.getReportId(),testReport.getPatientId(),testReport.getTestCode(),testReport.getTestTypeId(),
                testReport.getDate(),testReport.getTime(),testReport.getResult(),testReport.getReferenceRange());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM TestReport WHERE reportId=?",id);
    }

    @Override
    public TestReport get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM TestReport WHERE reportId=?",id);
        if (resultSet.next()) {
            return new TestReport(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8));
        } else {
            return null;
        }
    }

    @Override
    public List<TestReport> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM TestReport");
        ArrayList<TestReport> testRList = new ArrayList<>();
        while (resultSet.next()) {
            testRList.add(
                    new TestReport(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)));
        }
        return testRList;
    }
}
