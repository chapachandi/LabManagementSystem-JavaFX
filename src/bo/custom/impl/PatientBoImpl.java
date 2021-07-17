package bo.custom.impl;

import bo.custom.PatientBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.PatientDAO;
import dto.PatientDTO;
import entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientBoImpl implements PatientBo {
    private PatientDAO dao= DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.PATIENT);
    private QueryDAO qDao= DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean savePatient(PatientDTO dto) throws Exception {
        return dao.save(new Patient(dto.getPatientId(),dto.getTitle(),
                dto.getName(),dto.getAddress(),dto.getGender(),dto.getAge(),dto.getNic(),dto.getMobile()));
    }

    @Override
    public boolean updatePatient(PatientDTO dto) throws Exception {
        return dao.update(new Patient(dto.getPatientId(),dto.getTitle(),
                dto.getName(),dto.getAddress(),dto.getGender(),dto.getAge(),dto.getNic(),dto.getMobile()));
    }

    @Override
    public boolean deletePatient(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public PatientDTO getPatient(String id) throws Exception {
        Patient p = dao.get(id);
        return new PatientDTO(p.getPatientId(),p.getTitle(), p.getName(),p.getAddress(),p.getGender(),p.getAge(),p.getNic(),p.getMobile());
    }

    @Override
    public ArrayList<PatientDTO> getAllPatients() throws Exception {
        List<Patient> pList=dao.getAll();
        ArrayList<PatientDTO> dtoList= new ArrayList();
        for (Patient p : pList) {
            dtoList.add(new PatientDTO(p.getPatientId(),p.getTitle(),
                    p.getName(),p.getAddress(),p.getGender(),p.getAge(),p.getNic(),p.getMobile()));
        }
        return dtoList;
    }

    //-------------- genarate id -------------------

    @Override
    public String getPatientId() throws Exception {
        return qDao.getPatientId();
    }

    @Override
    public PatientDTO getPatientDto(String nic) throws Exception {
        Patient p = dao.getPatient(nic);
        return new PatientDTO(p.getPatientId(),p.getTitle(), p.getName(),p.getAddress(),p.getGender(),p.getAge(),p.getNic(),p.getMobile());
    }
}
