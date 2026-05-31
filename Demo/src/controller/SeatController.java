package controller;

import service.SeatService;
import service.BookingService;
import model.Seat;
import java.util.List;

public class SeatController {
    private SeatService seatService = new SeatService();
    private BookingService bookingService = new BookingService();

    // Lấy danh sách 50 ghế của phòng (nếu sau này bạn muốn vẽ ghế động từ DB)
    public List<Seat> loadSeatsForRoom(String roomId) {
        return seatService.getSeatsByRoomId(roomId);
    }

    // Lấy danh sách ID các ghế đã bị bán để gửi lên View bôi màu đỏ
    public List<String> loadBookedSeats(String showTimeId) {
        return bookingService.getBookedSeatIds(showTimeId);
    }
}