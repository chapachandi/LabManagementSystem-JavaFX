package bo.custom;

import dto.TestDetailDTO;

import java.util.ArrayList;

public interface TestDetailBo {
    public boolean saveTestDetail(TestDetailDTO dto)throws Exception;
    public boolean updateTestDetail(TestDetailDTO dto)throws Exception;
    public boolean deleteTestDetail(String id)throws Exception;
    public TestDetailDTO getTestDetail(String id)throws Exception;
    public ArrayList<TestDetailDTO> getAllTestDetails()throws Exception;
    public String getReceiptNo()throws Exception;
}
