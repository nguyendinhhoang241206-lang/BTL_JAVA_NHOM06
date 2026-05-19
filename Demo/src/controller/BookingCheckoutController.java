package controller;

import model.Booking;
import service.BookingCheckoutService;

public class BookingCheckoutController {
    private BookingCheckoutService bookingCheckoutService = new BookingCheckoutService();

    // TODO: Sinh viên tự code logic: Khi người dùng nhấn nút "Thanh toán/Xác nhận đặt vé" trên View, nhận thông tin đơn đặt vé, gọi bookingCheckoutService.checkout(booking) để lưu đơn hàng và hiển thị hộp thoại thông báo kết quả (Đặt vé thành công hay thất bại).
    public boolean handleCheckout(Booking booking) {
        return false;
    }
}
