package controller;

import model.User;
import service.RegisterService;

public class RegisterController {
    private RegisterService registerService = new RegisterService();

    // TODO: Sinh viên tự code logic: Nhận dữ liệu đăng ký từ View Đăng ký, thực hiện kiểm tra và gọi registerService.register để lưu người dùng mới. Trả về kết quả đăng ký thành công hay thất bại.
    public boolean handleRegister(User user) {
        return false;
    }
}
