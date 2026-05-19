package controller;

import service.DiscountCalculationService;

public class ApplyDiscountController {
    private DiscountCalculationService discountCalculationService = new DiscountCalculationService();

    // TODO: Sinh viên tự code logic: Nhận mã giảm giá (promoCode) người dùng nhập từ View cùng số tiền tạm tính hiện thời (originalPrice). Thực hiện kiểm tra tính hợp lệ (không để trống) và gọi discountCalculationService.calculateDiscount để lấy số tiền giảm, từ đó cập nhật lại thông tin giảm giá và tổng tiền thanh toán lên màn hình giao diện.
    public double handleApplyPromoCode(String promoCode, double originalPrice) {
        return 0.0;
    }
}
