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
            String username = view.getUsername();
            String email = view.getEmail();
            String password = view.getPassword();
            String confirmPassword = view.getConfirmPassword();

            // 1. Kiểm tra từng trường thông tin trống
            if (username.isEmpty()) {
                view.showMessage("Tên đăng nhập không được để trống!", false);
                return;
            }
            if (email.isEmpty()) {
                view.showMessage("Email không được để trống!", false);
                return;
            }
            if (password.isEmpty()) {
                view.showMessage("Mật khẩu không được để trống!", false);
                return;
            }
            if (confirmPassword.isEmpty()) {
                view.showMessage("Mật khẩu xác nhận không được để trống!", false);
                return;
            }

            // 2. Kiểm tra định dạng dữ liệu chi tiết
            if (!utils.ValidationUtil.isValidUsername(username)) {
                view.showMessage("Tên đăng nhập không hợp lệ (3-20 ký tự chữ/số)!", false);
                return;
            }
            if (!utils.ValidationUtil.isValidEmail(email)) {
                view.showMessage("Định dạng Gmail không chính xác!", false);
                return;
            }
            if (password.length() < 6) {
                view.showMessage("Mật khẩu phải từ 6 ký tự trở lên!", false);
                return;
            }

            // 3. Kiểm tra mật khẩu xác nhận khớp nhau
            if (!password.equals(confirmPassword)) {
                view.showMessage("Mật khẩu xác nhận không trùng khớp!", false);
                return;
            }

            // 4. Kiểm tra xem username hoặc email đã tồn tại hay chưa (phân nhỏ chi tiết)
            if (registerService.isUsernameDuplicate(username)) {
                view.showMessage("Tên đăng nhập đã tồn tại!", false);
                return;
            }
            if (registerService.isEmailDuplicate(email)) {
                view.showMessage("Email đã tồn tại!", false);
                return;
            }

            // 5. Tạo User để đăng ký
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            // 6. Thực hiện đăng ký
            boolean success = registerService.register(user);
            if (success) {
                view.showMessage("Đăng ký tài khoản thành công!", true);
                
                // Hiển thị hộp thoại popup thông báo đăng ký thành công
                JOptionPane.showMessageDialog(view, 
                    "Đăng ký tài khoản thành công!", 
                    "Thông báo", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Quay lại màn hình đăng nhập
                navigateToLogin();
            } else {
                view.showMessage("Đăng ký thất bại, đã xảy ra lỗi!", false);
            }
        });

        // Sự kiện khi người dùng nhấn nút "Đăng nhập" (Quay lại)
        this.view.addBackListener(e -> {
            navigateToLogin();
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

