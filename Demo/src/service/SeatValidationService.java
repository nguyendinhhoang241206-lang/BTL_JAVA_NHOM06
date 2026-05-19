package service;

import dao.BookingDAO;
import dao.SeatDAO;
import model.Booking;
import model.Seat;
import java.util.List;

public class SeatValidationService {
    private BookingDAO bookingDAO = new BookingDAO();
    private SeatDAO seatDAO = new SeatDAO();

    // TODO: Sinh viên tự code logic: Gọi bookingDAO.findByShowTimeId(showTimeId) để lấy tất cả Booking của suất chiếu này. Duyệt qua danh sách Booking có trạng thái SUCCESS hoặc PENDING, thu thập toàn bộ các bookedSeatIds từ các booking đó. Trả về danh sách mã ghế đã được đặt.
    public List<String> getBookedSeats(String showTimeId) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Gọi getBookedSeats(showTimeId) để lấy toàn bộ danh sách ghế đã được mua/đặt trước của suất chiếu. Kiểm tra xem seatId có nằm trong danh sách đó hay không. Trả về true nếu ghế vẫn còn trống (chưa bị đặt).
    public boolean isSeatAvailable(String showTimeId, String seatId) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt qua danh sách mã ghế được chọn (seatIds). Với mỗi seatId, gọi seatDAO.findById(seatId) để lấy thông tin ghế và cộng dồn thuộc tính price của ghế đó vào tổng tiền. Trả về tổng tiền tạm tính.
    public double calculateTemporaryPrice(List<String> seatIds) {
        return 0.0;
    }
}
