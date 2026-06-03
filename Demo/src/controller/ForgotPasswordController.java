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

            try {

                String username = view.getUsername();
                String email = view.getEmail();
                String oldPassword = view.getOldPassword();
                String newPassword = view.getNewPassword();

                forgotPasswordService.resetPassword(
                        username,
                        email,
                        newPassword);

                view.showMessage(
                        "Khôi phục mật khẩu thành công!",
                        true);

                JOptionPane.showMessageDialog(
                        view,
                        "Mật khẩu đã được thay đổi thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

                navigateToLogin();

            } catch (IllegalArgumentException ex) {

                view.showMessage(
                        ex.getMessage(),
                        false);

            } catch (Exception ex) {

                view.showMessage(
                        "Có lỗi xảy ra!",
                        false);

                ex.printStackTrace();
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
