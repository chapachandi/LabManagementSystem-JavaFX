package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.RequestDAO;
import entity.Request;
import entity.SampleType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImpl implements RequestDAO {
    @Override
    public boolean save(Request request) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Request VALUES(?,?,?,?)",request.getRequestId(),request.getDate(),request.getTime(),request.getPatientId());
    }

    @Override
    public boolean update(Request request) throws Exception {
        return CrudUtil.execute("UPDATE Request SET date=?,time=?, patientId=? WHERE requestId=?",
                request.getDate(),request.getTime(),request.getPatientId(),request.getRequestId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Request WHERE requestId=?",id);
    }

    @Override
    public Request get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Request WHERE requestId=?",id);
        if (resultSet.next()) {
            return new Request(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
        } else {
            return null;
        }
    }

    @Override
    public List<Request> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Request");
        ArrayList<Request> requestList = new ArrayList<>();
        while (resultSet.next()) {
            requestList.add(
                    new Request(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        return requestList;
    }
}
