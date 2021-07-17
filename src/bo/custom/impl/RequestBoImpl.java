package bo.custom.impl;

import bo.custom.RequestBo;
import dao.DaoFactory;
import dao.QueryDAO;

import dao.custom.RequestDAO;
import dto.RequestDTO;

import dto.SampleTypeDTO;
import entity.Request;
import entity.SampleType;


import java.util.ArrayList;
import java.util.List;

public class RequestBoImpl implements RequestBo {
    private RequestDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.REQUEST);
    private QueryDAO qDao= DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveRequest(RequestDTO dto) throws Exception {
        return dao.save(new Request(dto.getRequestId(),dto.getDate(),dto.getTime(),dto.getPatientId()));
    }

    @Override
    public boolean updateRequest(RequestDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteRequest(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public RequestDTO getRequest(String id) throws Exception {
        Request rst=dao.get(id);
        return new RequestDTO(rst.getRequestId(),rst.getDate(),rst.getTime(),rst.getPatientId());
    }

    @Override
    public ArrayList<RequestDTO> getAllRequests() throws Exception {
        List<Request> rList=dao.getAll();
        ArrayList<RequestDTO> dtoList= new ArrayList();
        for (Request r : rList) {
            dtoList.add(new RequestDTO(r.getRequestId(),r.getDate(),r.getTime(),r.getPatientId()));
        }
        return dtoList;
    }

    @Override
    public String getRequestId() throws Exception {
        return qDao.getRequestId();

    }
}
