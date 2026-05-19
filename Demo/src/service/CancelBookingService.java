package service;

import dao.BookingDAO;
import model.Booking;

public class CancelBookingService {
    private BookingDAO bookingDAO = new BookingDAO();

    // TODO: Sinh viên tự code logic: Nhận ID của Booking cần hủy, tìm kiếm đơn đặt vé bằng bookingDAO.findById(bookingId). Nếu tồn tại, thay đổi trạng thái của Booking đó từ SUCCESS/PENDING thành CANCELLED, sau đó gọi bookingDAO.update(booking) để lưu đè dữ liệu. Việc đổi trạng thái thành CANCELLED sẽ tự động giúp giải phóng các ghế thuộc suất chiếu đó (vì SeatValidationService chỉ lọc ghế từ các hóa đơn SUCCESS/PENDING).
    public boolean cancelBooking(String bookingId) {
        return false;
    }
}
