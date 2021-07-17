package bo.custom;

import dto.BookingDTO;
import dto.PatientDTO;


import java.util.ArrayList;

public interface BookingBo {
    public boolean saveBooking(BookingDTO dto)throws Exception;
    public boolean updateBooking(BookingDTO dto)throws Exception;
    public boolean deleteBooking(String id)throws Exception;
    public BookingDTO getBooking(String id)throws Exception;
    public ArrayList<BookingDTO> getAllBookings()throws Exception;
    public String getBookingNo()throws Exception;
    public BookingDTO getBookingDTO(String date,String time)throws Exception;

}
