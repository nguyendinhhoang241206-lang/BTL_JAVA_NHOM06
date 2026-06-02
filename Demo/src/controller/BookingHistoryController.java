package controller;

import service.BookingHistoryService;
import java.util.List;
import utils.Session; // Sử dụng đúng class Session trong package utils của bạn

public class BookingHistoryController {

    private BookingHistoryService bookingHistoryService = new BookingHistoryService();

    // Hàm này View sẽ gọi trực tiếp không cần truyền tham số
    public List<Object[]> getMyHistory() {
        // 1. Kiểm tra trạng thái đăng nhập
        if (!Session.isLoggedIn()) {
            return null;
        }

        // 2. CHỐT HẠ: Lấy đúng ID (VD: "U01", "U02") thay vì Username
        // Vì trong file FakeData, đối tượng Booking liên kết qua thuộc tính userId (khóa chính của User)
        String userId = Session.getCurrentUser().getId();

        return bookingHistoryService.getHistoryByUserId(userId);
    }

    // Nút hủy vé (giữ nguyên để bạn mở rộng sau này nếu cần)
    public boolean cancelBooking(String bookingId) {
        return false;
    }
}