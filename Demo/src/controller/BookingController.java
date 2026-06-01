package controller;

import service.BookingService;
import model.Booking;

import java.util.List;

public class BookingController {
    private BookingService bookingService = new BookingService();

    // Chỉ cần nhận 1 object Booking là đủ vì bên trong nó đã chứa List ghế rồi
    public boolean confirmBooking(Booking newBooking) {
        return bookingService.createBooking(newBooking);
    }
    // 1. Lấy danh sách lịch sử vé của 1 khách hàng
    public List<Booking> getBookingsByUserId(String userId) {
        return bookingService.getBookingsByUserId(userId);
        // (Nhớ đảm bảo BookingDAO/BookingService của bạn có hàm lọc theo userId này nhé)
    }

    // 2. Hàm xử lý Hủy vé
    public boolean cancelBooking(String bookingId) {
        return bookingService.updateBookingStatus(bookingId, Booking.Status.CANCELLED);
    }

}