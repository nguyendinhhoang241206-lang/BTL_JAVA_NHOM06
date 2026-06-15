package service;

import dao.BookingDAO;
import model.Booking;
import java.time.LocalDateTime;

public class BookingCheckoutService {
    private BookingDAO bookingDAO = new BookingDAO();

    // Hoàn thành logic TODO của bạn
    public boolean checkout(Booking booking) {
        if (booking == null) {
            return false;
        }

        // 1. Cập nhật trạng thái của Booking thành SUCCESS
        booking.setStatus(Booking.Status.SUCCESS);

        // 2. Thiết lập ngày giờ đặt vé hiện tại
        booking.setBookingDate(LocalDateTime.now());

        // 3. Gọi bookingDAO.add(booking) để ghi nhận đơn hàng xuống file
        return bookingDAO.add(booking);
    }
}