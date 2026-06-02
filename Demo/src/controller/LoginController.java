package controller;

import model.User;
import service.LoginService;
import view.LoginForm;
import view.RegisterForm;
import view.ForgotPassword;
import utils.ValidationUtil;
import javax.swing.JOptionPane;

public class LoginController {
    
    private LoginService loginService = new LoginService();
    private LoginForm view; 
    private User sessionUser;

    public LoginController(LoginForm view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Sự kiện khi nhấn nút "Xác nhận" (Đăng nhập)
        this.view.addConfirmListener(e -> {
            String username = view.getUsername();
            String password = view.getPassword();

            // 1. Kiểm tra định dạng dữ liệu (Validate Input)
            if (username.isEmpty()) {
                view.showMessage("Tên đăng nhập không được để trống!", false);
                return;
            }
            if (!ValidationUtil.isValidUsername(username)) {
                view.showMessage("Tên đăng nhập không hợp lệ (3-20 ký tự chữ/số)!", false);
                return;
            }
            if (password.isEmpty()) {
                view.showMessage("Mật khẩu không được để trống!", false);
                return;
            }

            // 2. Xử lý logic đăng nhập chi tiết
            User user = loginService.getLoggedInUser(username);
            if (user == null) {
                view.showMessage("Tên đăng nhập không tồn tại!", false);
                return;
            }
            if (user.getStatus() != model.enums.UserStatus.ACTIVE) {
                view.showMessage("Tài khoản của bạn đã bị khóa!", false);
                return;
            }
            if (user.getPassword() == null || !user.getPassword().equals(password)) {
                view.showMessage("Mật khẩu không chính xác!", false);
                return;
            }

            // Đăng nhập thành công
            this.sessionUser = user;
            view.showMessage("Đăng nhập thành công!", true);
            
            // Hiển thị thông báo chào mừng bằng Popup
            JOptionPane.showMessageDialog(view, 
                "Chào mừng " + sessionUser.getUsername() + " quay trở lại!", 
                "Đăng nhập thành công", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // --- CHỖ NÀY DÙNG ĐỂ CHUYỂN SANG MÀN HÌNH CHÍNH (DÀNH CHO SINH VIÊN PHÁT TRIỂN TIẾP) ---
            System.out.println("Đăng nhập thành công với vai trò: " + sessionUser.getRole());
        });

        // Sự kiện khi nhấn nút "Đăng ký"
        this.view.addRegisterListener(e -> {
            view.setVisible(false); // Ẩn màn hình đăng nhập
            RegisterForm registerForm = new RegisterForm();
            new RegisterController(registerForm);
            registerForm.setLocationRelativeTo(null); // Hiển thị giữa màn hình
            registerForm.setVisible(true); // Hiển thị màn hình đăng ký
        });

        // Sự kiện khi nhấn nút "Quên mật khẩu?"
        this.view.addForgotPasswordListener(e -> {
            view.setVisible(false); // Ẩn màn hình đăng nhập
            ForgotPassword forgotForm = new ForgotPassword();
            new ForgotPasswordController(forgotForm);
            forgotForm.setLocationRelativeTo(null); // Hiển thị giữa màn hình
            forgotForm.setVisible(true); // Hiển thị màn hình quên mật khẩu
        });
    }

    public User getSessionUser() {
        return sessionUser;
    }
}