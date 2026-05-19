package controller;

import model.Booking;
import service.BookingHistoryService;
import java.util.List;

public class BookingHistoryController {
    private BookingHistoryService bookingHistoryService = new BookingHistoryService();

    // TODO: Sinh viên tự code logic: Khi người dùng mở tab/màn hình "Lịch sử đặt vé" ở View, nhận thông tin userId hiện tại từ session, gọi bookingHistoryService.getHistoryByUserId(userId) để lấy dữ liệu lịch sử đặt vé và đổ dữ liệu hiển thị lên bảng (JTable) ở giao diện.
    public List<Booking> handleGetHistoryByUserId(String userId) {
        return null;
    }
}
