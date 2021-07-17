package bo;

import bo.custom.impl.*;


public class BoFactory {

    private static BoFactory boFactory;


    private BoFactory() {
    }


    public static BoFactory getInstance() {
        return (boFactory == null) ?
                (boFactory = new BoFactory()) : (boFactory);
    }


    public enum BOType {
        PATIENT, BOOKINGNUMBER ,REQUEST, SAMPLETYPE, SAMPLEUNIT , TEST, TESTTYPE,  TESTREPORT, TESTDETAIL, PAYMENT,PLACEREQ
    }


    public <T> T getBo(BOType type) {
        switch (type) {
            case PATIENT:
                return (T) new PatientBoImpl();
            case TEST:
                return (T) new TestBoImpl();
            case SAMPLETYPE:
                return (T) new SampleTypeBoImpl();
            case SAMPLEUNIT:
                return (T) new SampleUnitBoImpl();
            case BOOKINGNUMBER:
                return (T) new BookingBolmpl();
            case TESTDETAIL:
                return (T) new TestDetailBoImpl();
            case REQUEST:
                return (T) new RequestBoImpl();
            case PAYMENT:
                return (T) new PaymentBoImpl();
            case TESTTYPE:
                return (T) new TestTypeBoImpl();
            case TESTREPORT: return (T) new TestReportBoImpl();
            case PLACEREQ: return (T) new PlaceRequestBoImpl();


            default:
                return null;
        }
    }
}
