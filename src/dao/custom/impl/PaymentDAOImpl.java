package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PayementDAO;
import entity.Payment;
import entity.Request;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PayementDAO {
    @Override
    public boolean save(Payment payment) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?,?)",payment.getPaymentId(),payment.getPatientId(),
                        payment.getTestName(),payment.getDate(),payment.getTime(),payment.getSubtotal(),
                        payment.getAmount(),payment.getBalance(),payment.getPaymentType());
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Payment WHERE paymentId=?",id);
    }

    @Override
    public Payment get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Payment WHERE paymentId=?",id);
        if (resultSet.next()) {
            return new Payment(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),resultSet.getDouble(8),resultSet.getString(9));
        } else {
            return null;
        }
    }

    @Override
    public List<Payment> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Payment");
        ArrayList<Payment> paymentsList = new ArrayList<>();
        while (resultSet.next()) {
            paymentsList.add(
                    new Payment(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                            resultSet.getDouble(6),
                            resultSet.getDouble(7),resultSet.getDouble(8),resultSet.getString(9)));
        }
        return paymentsList;
    }

    @Override
    public int getLastPaymentId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1");
        if(resultSet.next()){
          return resultSet.getInt(1);
        }
          return 1;
    }
}
