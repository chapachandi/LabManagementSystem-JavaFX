package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.TestDetailDAO;
import entity.TestDetail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDetailDAOImpl implements TestDetailDAO {
    @Override
    public boolean save(TestDetail testDetail) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO TestDetail VALUES(?,?,?,?,?,?)",testDetail.getRequestId(),testDetail.getPaymentId(),
                        testDetail.getTestCode(),testDetail.getUnitCode(),testDetail.getTestName(),
                        testDetail.getPrice());
    }

    @Override
    public boolean update(TestDetail testDetail) throws Exception {
        return CrudUtil.execute("UPDATE TestDetail SET paymentId=?, testCode=?,unitCode=?,  testName=?, price=? WHERE requestId=?",
                testDetail.getPaymentId(),testDetail.getTestCode(),testDetail.getUnitCode(),testDetail.
                        getTestName(),testDetail.getPrice(),testDetail.getRequestId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM TestDetail WHERE requestId=?",id);
    }

    @Override
    public TestDetail get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM TestDetail WHERE requestId=?",id);
        if (resultSet.next()) {
            return new TestDetail(resultSet.getString(1),resultSet.getInt(2),
                    resultSet.getString(3),resultSet.getString(4),
                    resultSet.getString(5),resultSet.getDouble(6));
        } else {
            return null;
        }
    }

    @Override
    public List<TestDetail> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM TestDetail");
        ArrayList<TestDetail> testdetailList = new ArrayList<>();
        while (resultSet.next()) {
            testdetailList.add(
                    new TestDetail(resultSet.getString(1),resultSet.getInt(2),
                            resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getDouble(6)));
        }
        return testdetailList;
    }
}
