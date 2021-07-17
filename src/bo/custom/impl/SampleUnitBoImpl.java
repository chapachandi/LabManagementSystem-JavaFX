package bo.custom.impl;

import bo.custom.SampleUnitBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.SampleUnitDAO;
import dto.SampleTypeDTO;
import dto.SampleUnitDTO;
import entity.SampleType;
import entity.SampleUnit;

import java.util.ArrayList;
import java.util.List;

public class SampleUnitBoImpl implements SampleUnitBo {
    private SampleUnitDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.SAMPLEUNIT);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveSampleUnit(SampleUnitDTO dto) throws Exception {
        return dao.save(new SampleUnit(dto.getUnitCode(),dto.getSampleId(),dto.getTestName(),
                dto.getDate(),dto.getTime(),dto.getPatientId()));
    }

    @Override
    public boolean updateSampleUnit(SampleUnitDTO dto) throws Exception {
        return dao.update(new SampleUnit(dto.getUnitCode(),dto.getSampleId(),
                dto.getTestName(),dto.getDate(),dto.getTime(),dto.getPatientId()));
    }

    @Override
    public boolean deleteSampleUnit(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public SampleUnitDTO getSampleUnit(String id) throws Exception {
        SampleUnit su=dao.get(id);
        return new SampleUnitDTO(su.getUnitCode(),su.getSampleId(),
                su.getTestName(),su.getDate(),su.getTime(),su.getPatientId());
    }

    @Override
    public ArrayList<SampleUnitDTO> getAllSampleUnits() throws Exception {
        List<SampleUnit> suList=dao.getAll();
        ArrayList<SampleUnitDTO> dtoList= new ArrayList();
        for (SampleUnit su : suList) {
            dtoList.add(new SampleUnitDTO(su.getUnitCode(),su.getSampleId(),
                    su.getTestName(),su.getDate(),su.getTime(),su.getPatientId()));
        }
        return dtoList;
    }

    @Override
    public String getUnitCode() throws Exception {
        return qDao.getUnitCode();
    }

    @Override
    public String getPatientId() throws Exception {
        return qDao.getPatientId();
    }
}
