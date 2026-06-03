package controller;

import service.BookingHistoryService;
import service.BookingService; // Khai báo thêm BookingService
import model.Booking;
import java.util.List;
import utils.Session;

public class BookingHistoryController {

    private BookingHistoryService bookingHistoryService = new BookingHistoryService();
    private BookingService bookingService = new BookingService(); // Khởi tạo service xử lý vé

    // Hàm lấy lịch sử
    public List<Object[]> getMyHistory() {
        if (!Session.isLoggedIn()) {
            return null;
        }

        String userId = Session.getCurrentUser().getId();
        return bookingHistoryService.getHistoryByUserId(userId);
    }

    // ==========================================
    // ĐÃ FIX: CHỨC NĂNG HỦY VÉ THỰC TẾ
    // ==========================================
    public boolean cancelBooking(String bookingId) {
        // Nhờ BookingService đổi trạng thái vé này thành CANCELLED rồi lưu xuống file
        return bookingService.updateBookingStatus(bookingId, Booking.Status.CANCELLED);
    }
}