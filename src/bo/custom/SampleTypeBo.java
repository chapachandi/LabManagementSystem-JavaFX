package bo.custom;

import dto.SampleTypeDTO;
import java.util.ArrayList;

public interface SampleTypeBo {
    public boolean saveSampleType(SampleTypeDTO dto)throws Exception;
    public boolean updateSampleType(SampleTypeDTO dto)throws Exception;
    public boolean deleteSampleType(String id)throws Exception;
    public SampleTypeDTO getSampleType(String id)throws Exception;
    public ArrayList<SampleTypeDTO> getAllSampleTypes()throws Exception;
    public String getSampleId()throws Exception;
}
