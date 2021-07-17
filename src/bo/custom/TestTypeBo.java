package bo.custom;

import dto.TestTypeDTO;

import java.util.ArrayList;

public interface TestTypeBo {
    public boolean saveTestType(TestTypeDTO dto)throws Exception;
    public boolean updateTestType(TestTypeDTO dto)throws Exception;
    public boolean deleteTestType(String id)throws Exception;
    public TestTypeDTO getTestType(String id)throws Exception;
    public ArrayList<TestTypeDTO> getAllTestTypes()throws Exception;
    public String getTestTypeId()throws Exception;
}
