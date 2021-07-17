package bo.custom.impl;

import bo.custom.TestDetailBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.TestDetailDAO;
import dto.TestDetailDTO;
import entity.TestDetail;

import java.util.ArrayList;
import java.util.List;

public class TestDetailBoImpl implements TestDetailBo {
    private TestDetailDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.TESTDETAIL);
    private QueryDAO qDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveTestDetail(TestDetailDTO dto) throws Exception {
        return dao.save(new TestDetail(dto.getRequestId(),dto.getPaymentId(),dto.getTestCode(),dto.getUnitCode(),
                dto.getTestName(),dto.getPrice()));

    }

    @Override
    public boolean updateTestDetail(TestDetailDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTestDetail(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public TestDetailDTO getTestDetail(String id) throws Exception {
        TestDetail t=dao.get(id);
        return new TestDetailDTO(t.getRequestId(),t.getPaymentId(),t.getTestCode(),t.getUnitCode(),
                t.getTestName(),t.getPrice());
    }

    @Override
    public ArrayList<TestDetailDTO> getAllTestDetails() throws Exception {
        List<TestDetail> tList=dao.getAll();
        ArrayList<TestDetailDTO> dtoList= new ArrayList();
        for (TestDetail t : tList) {
            dtoList.add(new TestDetailDTO(t.getRequestId(),t.getPaymentId(),t.getTestCode(),t.getUnitCode(),
                    t.getTestName(),t.getPrice()));
        }
        return dtoList;
    }

    @Override
    public String getReceiptNo() throws Exception {
        return qDao.getRequestId();
    }
}
