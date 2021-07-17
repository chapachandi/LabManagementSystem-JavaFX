package bo.custom;

import dto.TestDTO;

import java.util.ArrayList;

public interface TestBo {
    public boolean saveTest(TestDTO dto)throws Exception;
    public boolean updateTest(TestDTO dto)throws Exception;
    public boolean deleteTest(String id)throws Exception;
    public TestDTO getTest(String id)throws Exception;
    public ArrayList<TestDTO> getAllTests()throws Exception;
    public String getTestCode()throws Exception;
}
