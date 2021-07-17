package dao;

import dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;


    private DaoFactory() {
    }


    public static DaoFactory getInstance() {
        return (daoFactory == null) ?
                (daoFactory = new DaoFactory()) : (daoFactory);
    }


    public enum DAOType {
        PATIENT, BOOKINGNUMBER, REQUEST, SAMPLETYPE, SAMPLEUNIT , TEST, TESTTYPE,  TESTREPORT, TESTDETAIL, PAYMENT, QUERY
    }


    public <T> T getDao(DAOType type) {
        switch (type) {
            case PATIENT:
                return (T) new PatientDAOImpl();
            case TEST:
                return (T) new TestDAOImpl();
            case SAMPLETYPE:
                return (T) new SampleTypeDAOImpl();
            case SAMPLEUNIT:
                return (T) new SampleUnitDAOImpl();
            case BOOKINGNUMBER:
                return (T) new BookingDAOImpl();
            case TESTDETAIL:
                return (T) new TestDetailDAOImpl();
            case REQUEST:
                return (T) new RequestDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case TESTTYPE:
                return (T) new TestTypeDAOImpl();
            case TESTREPORT:
                return (T) new TestReportDAOImpl();

            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
