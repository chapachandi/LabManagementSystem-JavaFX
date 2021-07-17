package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SampleTypeDAO;
import entity.SampleType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SampleTypeDAOImpl implements SampleTypeDAO {
    @Override
    public boolean save(SampleType sampleType) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO SampleType VALUES(?,?,?)",
                        sampleType.getSampleId(),sampleType.getTypeName(),sampleType.getUnit());
    }

    @Override
    public boolean update(SampleType sampleType) throws Exception {
        return CrudUtil.execute("UPDATE SampleType SET typeName=?,unit=? WHERE sampleId=?",
                sampleType.getTypeName(),sampleType.getUnit(),sampleType.getSampleId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM SampleType WHERE sampleId=?",id);
    }

    @Override
    public SampleType get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM SampleType WHERE sampleId=?",id);
        if (resultSet.next()) {
            return new SampleType(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
        } else {
            return null;
        }
    }

    @Override
    public List<SampleType> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM SampleType");
        ArrayList<SampleType> sampleTypeList = new ArrayList<>();
        while (resultSet.next()) {
            sampleTypeList.add(
                    new SampleType(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
        }
        return sampleTypeList;
    }
}
