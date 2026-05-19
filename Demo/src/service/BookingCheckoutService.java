package service;

import dao.BookingDAO;
import model.Booking;

public class BookingCheckoutService {
    private BookingDAO bookingDAO = new BookingDAO();

    // TODO: Sinh viên tự code logic: Nhận đối tượng Booking tạm tính, cập nhật trạng thái của Booking thành SUCCESS, thiết lập ngày giờ đặt vé hiện tại (LocalDateTime.now()). Gọi bookingDAO.add(booking) để ghi nhận đơn hàng thành công xuống file. Trả về true nếu thành công.
    public boolean checkout(Booking booking) {
        return false;
    }
}
