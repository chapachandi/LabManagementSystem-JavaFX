package bo.custom;

import dto.RequestDTO;
import view.tm.RequestTM;
import view.tm.TestDetailTM;

import java.util.ArrayList;
import java.util.List;

public interface RequestBo {
    public boolean saveRequest(RequestDTO dto)throws Exception;
    public boolean updateRequest(RequestDTO dto)throws Exception;
    public boolean deleteRequest(String id)throws Exception;
    public RequestDTO getRequest(String id)throws Exception;
    public ArrayList<RequestDTO> getAllRequests()throws Exception;
    public String getRequestId()throws Exception;


}
