package service;

import dao.BookingDAO;
import model.Booking;
import java.util.List;

public class RevenueReportService {
    private BookingDAO bookingDAO = new BookingDAO();

    // TODO: Sinh viên tự code logic: Gọi bookingDAO.findAll() để lấy toàn bộ danh sách đặt vé trong hệ thống. Lọc ra các hóa đơn Booking có trạng thái là SUCCESS. Duyệt qua danh sách đó và cộng dồn thuộc tính totalPrice để tính toán tổng doanh thu. Trả về tổng tiền doanh thu.
    public double calculateTotalRevenue() {
        return 0.0;
    }
}
