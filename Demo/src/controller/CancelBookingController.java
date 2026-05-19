package controller;

import service.CancelBookingService;

public class CancelBookingController {
    private CancelBookingService cancelBookingService = new CancelBookingService();

    // TODO: Sinh viên tự code logic: Khi khách hàng nhấn chọn "Hủy vé" từ danh sách lịch sử ở View, hiển thị Pop-up xác nhận (Confirm Dialog), nếu người dùng xác nhận đồng ý thì gọi cancelBookingService.cancelBooking(bookingId) để thực hiện hủy vé, sau đó cập nhật lại giao diện hiển thị.
    public boolean handleCancelBooking(String bookingId) {
        return false;
    }
}
