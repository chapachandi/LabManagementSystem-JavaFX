package bo.custom.impl;

import bo.custom.PlaceRequestBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.*;
import db.DBConnection;
import dto.PaymentDTO;
import dto.RequestDTO;
import dto.SampleUnitDTO;
import dto.TestDetailDTO;
import entity.Payment;
import entity.Request;
import entity.SampleUnit;
import entity.TestDetail;
import javafx.collections.ObservableList;
import view.tm.RequestTableTM;

import java.sql.Connection;

public class PlaceRequestBoImpl implements PlaceRequestBo {
    private PayementDAO payementDAO= DaoFactory.getInstance().getDao(DaoFactory.DAOType.PAYMENT);
    private SampleUnitDAO sampleUnitDAO= DaoFactory.getInstance().getDao(DaoFactory.DAOType.SAMPLEUNIT);
    private RequestDAO reqdao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.REQUEST);
    private TestDetailDAO testDetailDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.TESTDETAIL);
    private TestDAO testDAO= DaoFactory.getInstance().getDao(DaoFactory.DAOType.TEST);

    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean placeRequest(
            PaymentDTO paymentDTO,
            RequestDTO requestDTO,
            SampleUnitDTO sampleUnitDTO,
            ObservableList<RequestTableTM> detaillist) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        try {
//            connection.setAutoCommit(false);
//            Payment payment= new Payment(
//                    paymentDTO.getPaymentId(),
//                    paymentDTO.getPatientId(),
//                    paymentDTO.getTestName(),
//                    paymentDTO.getDate(),
//                    paymentDTO.getTime(),
//                    paymentDTO.getSubtotal(),
//                    paymentDTO.getAmount(),
//                    paymentDTO.getBalance(),
//                    paymentDTO.getPaymentType()
//            );
//            Request request = new Request(
//                    requestDTO.getRequestId(),
//                    requestDTO.getDate(),
//                    requestDTO.getTime(),
//                    requestDTO.getPatientId()
//            );
//            SampleUnit sampleUnit = new SampleUnit(
//                    sampleUnitDTO.getUnitCode(),
//                    sampleUnitDTO.getSampleId(),
//                    sampleUnitDTO.getTestName(),
//                    sampleUnitDTO.getDate(),
//                    sampleUnitDTO.getTime(),
//                    sampleUnitDTO.getPatientId()
//            );
//            TestDetail testDetail = new TestDetail(
//
//            );

            boolean paysave = payementDAO.save(
                    new Payment(
                            paymentDTO.getPaymentId(),
                            paymentDTO.getPatientId(),
                            paymentDTO.getTestName(),
                            paymentDTO.getDate(),
                            paymentDTO.getTime(),
                            paymentDTO.getSubtotal(),
                            paymentDTO.getAmount(),
                            paymentDTO.getBalance(),
                            paymentDTO.getPaymentType()
                    ));
            connection.setAutoCommit(false);
            if (paysave){
                boolean savereq = reqdao.save(
                        new Request(
                                requestDTO.getRequestId(),
                                requestDTO.getDate(),
                                requestDTO.getTime(),
                                requestDTO.getPatientId()
                        ));
                if (savereq){
                    boolean savespmunit = sampleUnitDAO.save(
                            new SampleUnit(
                                    sampleUnitDTO.getUnitCode(),
                                    sampleUnitDTO.getSampleId(),
                                    sampleUnitDTO.getTestName(),
                                    sampleUnitDTO.getDate(),
                                    sampleUnitDTO.getTime(),
                                    sampleUnitDTO.getPatientId()
                            ));
                    if (savespmunit){
                        boolean isadded = false;
                        for (RequestTableTM dto:detaillist) {
                            isadded = testDetailDAO.save(
                                    new TestDetail(
                                            dto.getRequestId(),
                                            dto.getPaymentId(),
                                            dto.getTestCode(),
                                            dto.getUnitCode(),
                                            dto.getTestName(),
                                            dto.getPrice()
                                            ));

                        }



                        connection.commit();
                        return true;
                    }
                }
            }


        }finally{
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }



}


