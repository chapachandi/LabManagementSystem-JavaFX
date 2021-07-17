package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SampleUnitDAO;
import entity.SampleType;
import entity.SampleUnit;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SampleUnitDAOImpl implements SampleUnitDAO{


    @Override
    public boolean save(SampleUnit sampleUnit) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO SampleUnit VALUES(?,?,?,?,?,?)",
                        sampleUnit.getUnitCode(),sampleUnit.getSampleId(),sampleUnit.getTestName(),
                        sampleUnit.getDate(),sampleUnit.getTime(),sampleUnit.getPatientId());
    }

    @Override
    public boolean update(SampleUnit sampleUnit) throws Exception {
        return CrudUtil.execute("UPDATE SampleUnit SET sampleId=?,testName=?,date=?,time=?, patientId=? WHERE unitCode=?",
                sampleUnit.getSampleId(),sampleUnit.getTestName(),sampleUnit.getDate(),sampleUnit.getTime(),
                sampleUnit.getPatientId(),sampleUnit.getUnitCode());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM SampleUnit WHERE unitCode=?",id);
    }

    @Override
    public SampleUnit get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM SampleUnit WHERE unitCode=?",id);
        if (resultSet.next()) {
            return new SampleUnit(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
        } else {
            return null;
        }
    }

    @Override
    public List<SampleUnit> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM SampleUnit");
        ArrayList<SampleUnit> sampleUnitList = new ArrayList<>();
        while (resultSet.next()) {
            sampleUnitList.add(
                    new SampleUnit(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                            resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
        }
        return sampleUnitList;
    }

}
