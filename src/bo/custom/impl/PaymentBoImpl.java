package bo.custom.impl;

import bo.custom.PaymentBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.PayementDAO;
import dto.PaymentDTO;
import entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {
    private PayementDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.PAYMENT);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        return dao.save(new Payment(dto.getPaymentId(),dto.getPatientId(),dto.getTestName(),
                dto.getDate(),dto.getTime(),dto.getSubtotal(),dto.getAmount(),dto.getBalance(),dto.getPaymentType()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public PaymentDTO getPayment(String id) throws Exception {
        Payment pay=dao.get(id);
        return new PaymentDTO(pay.getPaymentId(),pay.getPatientId(),pay.getTestName(),
                pay.getDate(),pay.getTime(),pay.getSubtotal(),pay.getAmount(),pay.getBalance(),pay.getPaymentType());
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        List<Payment> paytList=dao.getAll();
        ArrayList<PaymentDTO> dtoList= new ArrayList();
        for (Payment pay : paytList) {
            dtoList.add(new PaymentDTO(pay.getPaymentId(),pay.getPatientId(),pay.getTestName(),
                    pay.getDate(),pay.getTime(),pay.getSubtotal(),pay.getAmount(),pay.getBalance(),pay.getPaymentType()));
        }
        return dtoList;
    }

    @Override
    public String getPaymentId() throws Exception {
        return qDao.getPaymentId();
    }

    @Override
    public int getLastPaymentId() throws Exception {

        return dao.getLastPaymentId();
    }


}
