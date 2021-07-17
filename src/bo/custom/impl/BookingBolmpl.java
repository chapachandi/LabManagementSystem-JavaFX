package bo.custom.impl;

import bo.custom.BookingBo;
import bo.custom.PatientBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.BookingDAO;
import dao.custom.PatientDAO;
import dto.BookingDTO;
import dto.PatientDTO;
import entity.Booking;
import entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class BookingBolmpl implements BookingBo{
    private BookingDAO dao= DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.BOOKINGNUMBER);
    private QueryDAO qDao= DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.QUERY);


    @Override
    public boolean saveBooking(BookingDTO dto) throws Exception {
        return dao.save(new Booking(dto.getBookingNo(),dto.getDate(),dto.getTime(),dto.getPatientId()));
    }

    @Override
    public boolean updateBooking(BookingDTO dto) throws Exception {
        return dao.update(new Booking(dto.getBookingNo(),dto.getDate(),dto.getTime(),dto.getPatientId()));
    }

    @Override
    public boolean deleteBooking(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public BookingDTO getBooking(String id) throws Exception {
        Booking b=dao.get(id);
        return new BookingDTO(b.getBookingNo(),b.getDate(),b.getTime(),b.getPatientId());
    }

    @Override
    public ArrayList<BookingDTO> getAllBookings() throws Exception {
        List<Booking> bList=dao.getAll();
        ArrayList<BookingDTO> dtoList= new ArrayList();
        for (Booking b : bList) {
            dtoList.add(new BookingDTO(b.getBookingNo(),b.getDate(),b.getTime(),b.getPatientId()));
        }
        return dtoList;
    }

    @Override
    public String getBookingNo() throws Exception {
        return qDao.getBookingNo();
    }

    @Override
    public BookingDTO getBookingDTO(String date, String time) throws Exception {
        Booking b = dao.getBookingDateTime(date, time);
        if(b!=null) {
            return new BookingDTO(b.getBookingNo(), b.getDate(), b.getTime(), b.getPatientId());
        }else{
            return null;
        }

    }


}
