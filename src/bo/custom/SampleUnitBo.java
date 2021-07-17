package bo.custom;
import dto.SampleUnitDTO;

import java.util.ArrayList;

public interface SampleUnitBo {
    public boolean saveSampleUnit(SampleUnitDTO dto)throws Exception;
    public boolean updateSampleUnit(SampleUnitDTO dto)throws Exception;
    public boolean deleteSampleUnit(String id)throws Exception;
    public SampleUnitDTO getSampleUnit(String id)throws Exception;
    public ArrayList<SampleUnitDTO> getAllSampleUnits()throws Exception;
    public String getUnitCode()throws Exception;
    public String getPatientId() throws Exception;


}
