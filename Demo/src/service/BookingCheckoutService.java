package service;

import dao.BookingDAO;
import model.Booking;
import java.time.LocalDateTime;

public class BookingCheckoutService {
    private BookingDAO bookingDAO = new BookingDAO();


    public boolean checkout(Booking booking) {
        if (booking == null) {
            return false;
        }

        booking.setStatus(Booking.Status.SUCCESS);

        booking.setBookingDate(LocalDateTime.now());

        return bookingDAO.add(booking);
    }
}