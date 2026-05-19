package service;

import dao.BookingDAO;
import model.Booking;
import java.util.List;

public class BookingHistoryService {
    private BookingDAO bookingDAO = new BookingDAO();

    // TODO: Sinh viên tự code logic: Gọi bookingDAO.findByUserId(userId) để truy xuất toàn bộ danh sách lịch sử đặt vé của khách hàng cụ thể này và trả về.
    public List<Booking> getHistoryByUserId(String userId) {
        return null;
    }
}
