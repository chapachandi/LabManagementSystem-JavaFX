package bo.custom.impl;

import bo.custom.TestReportBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.TestReportDAO;

import dto.TestReportDTO;

import entity.TestReport;

import java.util.ArrayList;
import java.util.List;

public class TestReportBoImpl implements TestReportBo {
    private TestReportDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.TESTREPORT);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);
    @Override
    public boolean saveTestReport(TestReportDTO dto) throws Exception {
        return dao.save(new TestReport(dto.getReportId(),
                dto.getPatientId(),
                dto.getTestCode(),
                dto.getTestTypeId(),
                dto.getDate(),
                dto.getTime(),
                dto.getResult(),
                dto.getReferenceRange()));
    }

    @Override
    public boolean updateTestReport(TestReportDTO dto) throws Exception {
        return dao.update(new TestReport(dto.getReportId(),
                dto.getPatientId(),
                dto.getTestCode(),
                dto.getTestTypeId(),
                dto.getDate(),
                dto.getTime(),
                dto.getResult(),
                dto.getReferenceRange()));
    }

    @Override
    public boolean deleteTestReport(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public TestReportDTO getTestReport(String id) throws Exception {
        TestReport tr=dao.get(id);
        return new TestReportDTO(tr.getReportId(),tr.getPatientId(),tr.getTestCode(),tr.getTestTypeId(),
                tr.getDate(),tr.getTime(),tr.getResult(),tr.getReferenceRange());
    }

    @Override
    public ArrayList<TestReportDTO> getAllTestReports() throws Exception {
        List<TestReport> tList=dao.getAll();
        ArrayList<TestReportDTO> dtoList= new ArrayList();
        for (TestReport tr : tList) {
            dtoList.add(new TestReportDTO(tr.getReportId(),tr.getPatientId(),tr.getTestCode(),tr.getTestTypeId(),
                    tr.getDate(),tr.getTime(),tr.getResult(),tr.getReferenceRange()));
        }
        return dtoList;
    }

    @Override
    public String getReportId() throws Exception {
        return qDao.getReportId();
    }
}
