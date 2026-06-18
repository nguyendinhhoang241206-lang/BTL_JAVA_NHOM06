package service.booking;

import dao.BookingDAO;
import model.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();

    public List<String> getBookedSeatIds(String showTimeId) {
        List<String> bookedSeatIds = new ArrayList<>();
        List<Booking> bookingsForShow = bookingDAO.findByShowTimeId(showTimeId);

        for (Booking b : bookingsForShow) {
            if (b.getStatus() == Booking.Status.SUCCESS) {
                if (b.getBookedSeatIds() != null) {
                    bookedSeatIds.addAll(b.getBookedSeatIds());
                }
            }
        }
        return bookedSeatIds;
    }

    public boolean createBooking(Booking booking) {
        return bookingDAO.add(booking);
    }

    public List<Booking> getBookingsByUserId(String userId) {
        return bookingDAO.findByUserId(userId);
    }

    public boolean updateBookingStatus(String bookingId, Booking.Status newStatus) {
        Booking booking = bookingDAO.findById(bookingId);

        if (booking != null) {
            booking.setStatus(newStatus);
            return bookingDAO.update(booking);
        }
        return false;
    }
}