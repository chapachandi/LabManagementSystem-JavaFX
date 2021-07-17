package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.TestDAO;
import entity.SampleType;
import entity.Test;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDAOImpl implements TestDAO {
    @Override
    public boolean save(Test test) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Test VALUES(?,?,?)",
                        test.getTestCode(),test.getTestName(),test.getPrice());
    }

    @Override
    public boolean update(Test test) throws Exception {
        return CrudUtil.execute("UPDATE Test SET testName=?,price=? WHERE testCode=?",
               test.getTestName(),test.getPrice(),test.getTestCode());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Test WHERE testCode=?",id);
    }

    @Override
    public Test get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Test WHERE testCode=?",id);
        if (resultSet.next()) {
            return new Test(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3));
        } else {
            return null;
        }
    }

    @Override
    public List<Test> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Test");
        ArrayList<Test> testList = new ArrayList<>();
        while (resultSet.next()) {
            testList.add(
                    new Test(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3)));
        }
        return testList;
    }
}
