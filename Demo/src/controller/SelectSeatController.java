package controller;

import service.SeatValidationService;
import java.util.List;

public class SelectSeatController {
    private SeatValidationService seatValidationService = new SeatValidationService();

    // TODO: Sinh viên tự code logic: Nhận showTimeId từ View (suất chiếu hiện tại), gọi seatValidationService.getBookedSeats(showTimeId) để nhận danh sách ghế đã được đặt và truyền kết quả lên giao diện nhằm vô hiệu hóa các nút ghế đó.
    public List<String> handleGetBookedSeats(String showTimeId) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Khi người dùng nhấn chọn một ghế trên sơ đồ ghế, nhận thông tin showTimeId và seatId, gọi seatValidationService.isSeatAvailable để kiểm tra ghế trống. Trả về true nếu thao tác hợp lệ.
    public boolean handleSelectSeat(String showTimeId, String seatId) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Khi người dùng thay đổi danh sách các ghế đang chọn, gọi seatValidationService.calculateTemporaryPrice(seatIds) để tính toán lại tổng tiền tạm tính và cập nhật lên View (Label hiển thị số tiền).
    public double handleCalculateTemporaryPrice(List<String> seatIds) {
        return 0.0;
    }
}
