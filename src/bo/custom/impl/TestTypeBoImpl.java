package bo.custom.impl;

import bo.custom.TestTypeBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.TestDAO;
import dao.custom.TestTypeDAO;
import dto.TestDTO;
import dto.TestTypeDTO;
import entity.Test;
import entity.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestTypeBoImpl implements TestTypeBo {

    private TestTypeDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.TESTTYPE);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveTestType(TestTypeDTO dto) throws Exception {
        return dao.save(new TestType(dto.getTestTypeId(),
                dto.getTestCode(),dto.getTypeName(),dto.getReferenceRange()));
    }

    @Override
    public boolean updateTestType(TestTypeDTO dto) throws Exception {
        return dao.update(new TestType(dto.getTestTypeId(),
                dto.getTestCode(),dto.getTypeName(),dto.getReferenceRange()));
    }

    @Override
    public boolean deleteTestType(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public TestTypeDTO getTestType(String id) throws Exception {
        TestType t=dao.get(id);
        return new TestTypeDTO(t.getTestTypeId(),t.getTestCode(),t.getTypeName(),t.getReferenceRange());
    }

    @Override
    public ArrayList<TestTypeDTO> getAllTestTypes() throws Exception {
        List<TestType> tTList=dao.getAll();
        ArrayList<TestTypeDTO> dtoList= new ArrayList();
        for (TestType t : tTList) {
            dtoList.add(new TestTypeDTO(t.getTestTypeId(),t.getTestCode(),t.getTypeName(),t.getReferenceRange()));
        }
        return dtoList;
    }

    @Override
    public String getTestTypeId() throws Exception {
        return qDao.getTestTypeId();
    }
}
