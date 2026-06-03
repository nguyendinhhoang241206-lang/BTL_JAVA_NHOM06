package controller;

import service.ForgotPasswordService;
import view.ForgotPassword;
import view.LoginForm;
import utils.ValidationUtil;
import javax.swing.JOptionPane;

public class ForgotPasswordController {
    private ForgotPasswordService forgotPasswordService = new ForgotPasswordService();
    private ForgotPassword view;

    public ForgotPasswordController(ForgotPassword view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Sự kiện khi nhấn nút "Xác nhận" (Khôi phục mật khẩu)
        this.view.addResetListener(e -> {
            String username = view.getUsername();
            String email = view.getEmail();
            String oldPassword = view.getOldPassword();
            String newPassword = view.getNewPassword();

            // 1. Kiểm tra từng trường trống
            if (username.isEmpty()) {
                view.showMessage("Tên đăng nhập không được để trống!", false);
                return;
            }
            if (email.isEmpty()) {
                view.showMessage("Email không được để trống!", false);
                return;
            }
            if (oldPassword.isEmpty()) {
                view.showMessage("Mật khẩu cũ không được để trống!", false);
                return;
            }
            if (newPassword.isEmpty()) {
                view.showMessage("Mật khẩu mới không được để trống!", false);
                return;
            }

            // 2. Validate định dạng dữ liệu (Email và Username)
            if (!ValidationUtil.isValidUsername(username)) {
                view.showMessage("Tên đăng nhập không hợp lệ (3-20 ký tự chữ/số)!", false);
                return;
            }
            if (!ValidationUtil.isValidEmail(email)) {
                view.showMessage("Định dạng Gmail không chính xác!", false);
                return;
            }
            if (newPassword.length() < 6) {
                view.showMessage("Mật khẩu mới phải từ 6 ký tự trở lên!", false);
                return;
            }

            // 3. Thực hiện khôi phục mật khẩu thông qua Service
            String errorMessage = forgotPasswordService.resetPassword(username, email, newPassword);

            if (errorMessage == null) {
                view.showMessage("Khôi phục mật khẩu thành công!", true);
                
                // Hiển thị hộp thoại thông báo thành công
                JOptionPane.showMessageDialog(view, 
                    "Mật khẩu đã được thay đổi thành công!", 
                    "Thông báo", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Quay lại màn hình đăng nhập
                navigateToLogin();
            } else {
                view.showMessage(errorMessage, false);
            }
        });

        // Sự kiện khi nhấn nút "Đăng nhập" (Quay lại)
        this.view.addBackListener(e -> {
            navigateToLogin();
        });
    }

    // Hàm điều hướng quay lại màn hình Đăng nhập
    private void navigateToLogin() {
        view.dispose(); // Đóng/Giải phóng tài nguyên màn hình quên mật khẩu
        LoginForm loginForm = new LoginForm();
        new LoginController(loginForm);
        loginForm.setLocationRelativeTo(null);
        loginForm.setVisible(true);
    }
}
