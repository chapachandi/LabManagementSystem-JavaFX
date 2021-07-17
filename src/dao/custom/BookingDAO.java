package dao.custom;

import dao.CrudDAO;
import entity.Booking;
import entity.Patient;

public interface BookingDAO extends CrudDAO<Booking,String> {
    public Booking getBookingDateTime(String date, String time) throws Exception;
}
