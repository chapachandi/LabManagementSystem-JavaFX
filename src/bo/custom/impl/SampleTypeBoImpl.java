package bo.custom.impl;

import bo.custom.SampleTypeBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.SampleTypeDAO;
import dto.SampleTypeDTO;
import entity.SampleType;


import java.util.ArrayList;
import java.util.List;

public class SampleTypeBoImpl implements SampleTypeBo {
    private SampleTypeDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.SAMPLETYPE);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);


    @Override
    public boolean saveSampleType(SampleTypeDTO dto) throws Exception {
        return dao.save(new SampleType(dto.getSampleId(),dto.getTypeName(),
                dto.getUnit()));
    }

    @Override
    public boolean updateSampleType(SampleTypeDTO dto) throws Exception {
        return dao.update(new SampleType(dto.getSampleId(),dto.getTypeName(),dto.getUnit()));
    }

    @Override
    public boolean deleteSampleType(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public SampleTypeDTO getSampleType(String id) throws Exception {
        SampleType st=dao.get(id);
        return new SampleTypeDTO(st.getSampleId(),st.getTypeName(),st.getUnit());
    }

    @Override
    public ArrayList<SampleTypeDTO> getAllSampleTypes() throws Exception {
        List<SampleType> stList=dao.getAll();
        ArrayList<SampleTypeDTO> dtoList= new ArrayList();
        for (SampleType st : stList) {
            dtoList.add(new SampleTypeDTO(st.getSampleId(),st.getTypeName(),st.getUnit()));
        }
        return dtoList;
    }

    @Override
    public String getSampleId() throws Exception {
        return qDao.getSampleId();
    }
}
