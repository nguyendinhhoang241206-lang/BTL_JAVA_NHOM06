package controller;

import model.User;
import service.RegisterService;
import view.RegisterForm;
import view.LoginForm;
import javax.swing.JOptionPane;

public class RegisterController {

    private RegisterService registerService = new RegisterService();
    private RegisterForm view;

    public RegisterController(RegisterForm view) {
        this.view = view;
        initController();
    }

    private void initController() {

        // Sự kiện khi người dùng nhấn nút "Đăng ký"
        this.view.addRegisterListener(e -> {

            try {

                String username = view.getUsername();
                String email = view.getEmail();
                String password = view.getPassword();
                String confirmPassword = view.getConfirmPassword();

                // Kiểm tra mật khẩu xác nhận
                if (!password.equals(confirmPassword)) {
                    throw new IllegalArgumentException(
                            "Mật khẩu xác nhận không trùng khớp!");
                }

                // Tạo đối tượng User
                User user = new User();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);

                // Thực hiện đăng ký
                boolean success = registerService.register(user);

                if (success) {

                    view.showMessage(
                            "Đăng ký tài khoản thành công!",
                            true);

                    JOptionPane.showMessageDialog(
                            view,
                            "Đăng ký tài khoản thành công!",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);

                    navigateToLogin();

                } else {

                    view.showMessage(
                            "Đăng ký thất bại!",
                            false);
                }

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

        // Sự kiện khi nhấn nút quay lại đăng nhập
        this.view.addBackListener(e -> {
            navigateToLogin();
        });

        // Trong initController của RegisterController
        // Gắn sự kiện con mắt Mật khẩu
        this.view.addShowPassListener(new java.awt.event.MouseAdapter() {
            private boolean visible = false;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                visible = !visible;
                view.setPasswordVisible(visible);
            }
        });

        // Gắn sự kiện con mắt Xác nhận
        this.view.addShowConfirmPassListener(new java.awt.event.MouseAdapter() {
            private boolean visible = false;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                visible = !visible;
                view.setConfirmPasswordVisible(visible);
            }
        });
    }

    // Hàm điều hướng quay lại màn hình Đăng nhập
    private void navigateToLogin() {
        view.dispose(); // Đóng/Giải phóng tài nguyên màn hình đăng ký
        LoginForm loginForm = new LoginForm();
        new LoginController(loginForm);
        loginForm.setLocationRelativeTo(null);
        loginForm.setVisible(true);
    }

    public boolean handleRegister(User user) {
        return registerService.register(user);
    }
}
