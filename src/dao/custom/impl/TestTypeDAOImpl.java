package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.TestTypeDAO;
import entity.Test;
import entity.TestType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestTypeDAOImpl implements TestTypeDAO {
    @Override
    public boolean save(TestType testType) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO TestType VALUES(?,?,?,?)",testType.getTestTypeId(),
                        testType.getTestCode(),testType.getTypeName(),testType.getReferenceRange());
    }

    @Override
    public boolean update(TestType testType) throws Exception {
        return CrudUtil.execute("UPDATE TestType SET testCode=?,typeName=?,referenceRange=? WHERE testTypeId=?",
                testType.getTestCode(),testType.getTypeName(),testType.getReferenceRange(),testType.getTestTypeId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM TestType WHERE testTypeId=?",id);
    }

    @Override
    public TestType get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM TestType WHERE testTypeId=?",id);
        if (resultSet.next()) {
            return new TestType(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        } else {
            return null;
        }
    }

    @Override
    public List<TestType> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM TestType");
        ArrayList<TestType> testTList = new ArrayList<>();
        while (resultSet.next()) {
            testTList.add(
                    new TestType(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)));
        }
        return testTList;
    }
}
