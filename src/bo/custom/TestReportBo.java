package bo.custom;


import dto.TestReportDTO;

import java.util.ArrayList;

public interface TestReportBo {
    public boolean saveTestReport(TestReportDTO dto)throws Exception;
    public boolean updateTestReport(TestReportDTO dto)throws Exception;
    public boolean deleteTestReport(String id)throws Exception;
    public TestReportDTO getTestReport(String id)throws Exception;
    public ArrayList<TestReportDTO> getAllTestReports()throws Exception;
    public String getReportId()throws Exception;
}
