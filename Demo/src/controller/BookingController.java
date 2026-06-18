package controller;

import service.BookingService;
import service.BookingCheckoutService; 
import model.Booking;

import java.util.List;

public class BookingController {
    private BookingService bookingService = new BookingService();

    private BookingCheckoutService checkoutService = new BookingCheckoutService();


    public boolean confirmBooking(Booking newBooking) {
        return checkoutService.checkout(newBooking);
    }


    public List<Booking> getBookingsByUserId(String userId) {
        return bookingService.getBookingsByUserId(userId);
    }


    public boolean cancelBooking(String bookingId) {
        return bookingService.updateBookingStatus(bookingId, Booking.Status.CANCELLED);
    }
}