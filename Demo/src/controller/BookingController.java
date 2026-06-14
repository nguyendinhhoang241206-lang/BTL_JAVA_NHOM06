package controller;

import service.BookingService;
import service.BookingCheckoutService; // Phải import Service mới này vào
import model.Booking;

import java.util.List;

public class BookingController {
    private BookingService bookingService = new BookingService();
    // Khởi tạo Service xử lý chốt đơn
    private BookingCheckoutService checkoutService = new BookingCheckoutService();

    // CHÚ Ý: Đã đổi sang gọi checkout() của BookingCheckoutService
    public boolean confirmBooking(Booking newBooking) {
        return checkoutService.checkout(newBooking);
    }

    // 1. Lấy danh sách lịch sử vé của 1 khách hàng
    public List<Booking> getBookingsByUserId(String userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    // 2. Hàm xử lý Hủy vé
    public boolean cancelBooking(String bookingId) {
        return bookingService.updateBookingStatus(bookingId, Booking.Status.CANCELLED);
    }
}