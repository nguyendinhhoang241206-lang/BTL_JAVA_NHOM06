package controller;

import service.BookingHistoryService;
import service.BookingService; 
import model.Booking;
import java.util.List;
import utils.Session;

public class BookingHistoryController {

    private BookingHistoryService bookingHistoryService = new BookingHistoryService();
    private BookingService bookingService = new BookingService();


    public List<Object[]> getMyHistory() {
        if (!Session.isLoggedIn()) {
            return null;
        }

        String userId = Session.getCurrentUser().getId();
        return bookingHistoryService.getHistoryByUserId(userId);
    }

    public boolean cancelBooking(String bookingId) {

        return bookingService.updateBookingStatus(bookingId, Booking.Status.CANCELLED);
    }
}