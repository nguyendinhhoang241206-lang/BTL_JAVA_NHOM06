package controller;

import service.SeatService;
import service.BookingService;
import model.Seat;
import java.util.List;

public class SeatController {
    private SeatService seatService = new SeatService();
    private BookingService bookingService = new BookingService();

    public List<Seat> loadSeatsForRoom(String roomId) {
        return seatService.getSeatsByRoomId(roomId);
    }

    public List<String> loadBookedSeats(String showTimeId) {
        return bookingService.getBookedSeatIds(showTimeId);
    }
}