package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BookingDAO;
import entity.Booking;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public boolean save(Booking booking) throws Exception {
        return CrudUtil.
                execute("INSERT INTO BookingNumber VALUES(?,?,?,?)",
                booking.getBookingNo(),
                booking.getDate(),
                booking.getTime(),
                booking.getPatientId());
    }

    @Override
    public boolean update(Booking booking) throws Exception {
        return CrudUtil.execute("UPDATE BookingNumber SET date=?,time=?,patientId=? WHERE bookingNo=?",
                booking.getDate(),booking.getTime(),booking.getPatientId(),booking.getBookingNo());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM BookingNumber WHERE bookingNo=?",id);
    }

    @Override
    public Booking get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM BookingNumber WHERE  date=? and time=?",id);
        if (resultSet.next()) {
            return new Booking(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4));
        } else {
            return null;
        }
    }

    @Override
    public List<Booking> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM BookingNumber");
        ArrayList<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            bookingList.add(
                    new Booking(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4)));
        }
        return bookingList;
    }

    @Override
    public Booking getBookingDateTime(String date, String time) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM bookingnumber WHERE date=? and time=?",date,time);
        if (resultSet.next()) {
            return new Booking(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4));
        } else {
            return null;
        }
    }
}
