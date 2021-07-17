package bo.custom;

import dto.PatientDTO;
import entity.Patient;

import java.util.ArrayList;

public interface PatientBo {
    public boolean savePatient(PatientDTO dto)throws Exception;
    public boolean updatePatient(PatientDTO dto)throws Exception;
    public boolean deletePatient(String id)throws Exception;
    public PatientDTO getPatient(String id)throws Exception;
    public ArrayList<PatientDTO> getAllPatients()throws Exception;
    public String getPatientId()throws Exception;
    public PatientDTO  getPatientDto(String nic) throws Exception;

}
