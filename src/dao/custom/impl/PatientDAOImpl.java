package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PatientDAO;
import entity.Patient;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {


    @Override
    public boolean save(Patient patient) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Patient VALUES(?,?,?,?,?,?,?,?)",
                        patient.getPatientId(), patient.getTitle(), patient.getName(),
                        patient.getAddress(), patient.getGender(), patient.getAge(),
                        patient.getNic(), patient.getMobile());
    }


    @Override
    public boolean update(Patient patient) throws Exception {
        return CrudUtil.execute(
                "UPDATE Patient SET title=?,name=?,address=?,gender=?,age=?,nic=?,mobile=? WHERE patientId=?",
                patient.getTitle(), patient.getName(), patient.getAddress(),
                patient.getGender(), patient.getAge(), patient.getNic(), patient.getMobile(), patient.getPatientId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Patient WHERE patientId=?", id);
    }

    @Override
    public Patient get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Patient WHERE patientId=?", id);
        if (resultSet.next()) {
            return new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
        } else {
            System.out.println("ijn");
            return null;
        }
    }

    @Override
    public List<Patient> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Patient");
        ArrayList<Patient> patientList = new ArrayList<>();
        while (resultSet.next()) {
            patientList.add(
                    new Patient(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getInt(8)));
        }
        return patientList;
    }

    @Override
    public Patient getPatient(String nic) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Patient WHERE nic=?", nic);
        if (resultSet.next()) {
            return new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
        } else {
            return null;
        }
    }
}



