package service;

import dao.BookingDAO;
import model.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();

    // 1. Lấy danh sách ID ghế đã bị đặt của suất chiếu
    public List<String> getBookedSeatIds(String showTimeId) {
        List<String> bookedSeatIds = new ArrayList<>();
        List<Booking> bookingsForShow = bookingDAO.findByShowTimeId(showTimeId);

        for (Booking b : bookingsForShow) {
            if (b.getStatus() == Booking.Status.SUCCESS) {
                if (b.getBookedSeatIds() != null) {
                    bookedSeatIds.addAll(b.getBookedSeatIds());
                }
            }
        }
        return bookedSeatIds;
    }

    // 2. Tạo hóa đơn mới
    public boolean createBooking(Booking booking) {
        return bookingDAO.add(booking);
    }

    // ==========================================
    // CÁC HÀM MỚI PHỤC VỤ LỊCH SỬ & HỦY VÉ
    // ==========================================

    // 3. Lấy lịch sử vé (Gọi thẳng hàm findByUserId đã có sẵn cực chuẩn trong DAO của bạn)
    public List<Booking> getBookingsByUserId(String userId) {
        return bookingDAO.findByUserId(userId);
    }

    // 4. Hủy vé (Dùng combo findById + update của DAO)
    public boolean updateBookingStatus(String bookingId, Booking.Status newStatus) {
        // Bước 1: Tìm vé bằng hàm có sẵn
        Booking booking = bookingDAO.findById(bookingId);

        if (booking != null) {
            // Bước 2: Sửa trạng thái thành CANCELLED
            booking.setStatus(newStatus);
            // Bước 3: Đẩy lại xuống DAO để lưu đè (update) vào file
            return bookingDAO.update(booking);
        }
        return false;
    }
}