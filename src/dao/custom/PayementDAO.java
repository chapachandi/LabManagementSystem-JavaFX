package dao.custom;

import dao.CrudDAO;
import entity.Payment;

public interface PayementDAO extends CrudDAO<Payment,String> {
    public int getLastPaymentId() throws Exception;
}
