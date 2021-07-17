package dao.custom;

import dao.CrudDAO;
import entity.Patient;

public interface PatientDAO extends CrudDAO<Patient,String> {
    public Patient getPatient(String nic) throws Exception;
}
