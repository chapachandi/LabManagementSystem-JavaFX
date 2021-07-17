package bo.custom.impl;

import bo.custom.TestBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.TestDAO;
import dto.TestDTO;
import entity.Test;

import java.util.ArrayList;
import java.util.List;

public class TestBoImpl implements TestBo {
    private TestDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.TEST);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveTest(TestDTO dto) throws Exception {
        return dao.save(new Test(dto.getTestCode(),dto.getTestName(),dto.getPrice()));
    }

    @Override
    public boolean updateTest(TestDTO dto) throws Exception {
        return dao.update(new Test(dto.getTestCode(),dto.getTestName(),dto.getPrice()));
    }

    @Override
    public boolean deleteTest(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public TestDTO getTest(String id) throws Exception {
        Test t=dao.get(id);
        return new TestDTO(t.getTestCode(),t.getTestName(),t.getPrice());
    }

    @Override
    public ArrayList<TestDTO> getAllTests() throws Exception {
        List<Test> tList=dao.getAll();
        ArrayList<TestDTO> dtoList= new ArrayList();
        for (Test t : tList) {
            dtoList.add(new TestDTO(t.getTestCode(),t.getTestName(),
                    t.getPrice()));
        }
        return dtoList;
    }

    @Override
    public String getTestCode() throws Exception {
        return qDao.getTestCode();
    }
}
