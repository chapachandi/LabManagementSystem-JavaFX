package bo.custom;


import dto.PaymentDTO;
import view.tm.RequestTM;
import view.tm.TestDetailTM;

import java.util.ArrayList;


public interface PaymentBo {
    public boolean savePayment(PaymentDTO dto)throws Exception;
    public boolean updatePayment(PaymentDTO dto)throws Exception;
    public boolean deletePayment(String id)throws Exception;
    public PaymentDTO getPayment(String id)throws Exception;
    public ArrayList<PaymentDTO> getAllPayments()throws Exception;
    public String getPaymentId()throws Exception;
    public int getLastPaymentId()throws Exception;


}
