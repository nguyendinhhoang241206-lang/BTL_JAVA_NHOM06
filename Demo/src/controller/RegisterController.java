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


        this.view.addRegisterListener(e -> {

            try {

                String username = view.getUsername();
                String email = view.getEmail();
                String password = view.getPassword();
                String confirmPassword = view.getConfirmPassword();

                if (!password.equals(confirmPassword)) {
                    throw new IllegalArgumentException(
                            "Mật khẩu xác nhận không trùng khớp!");
                }

                User user = new User();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);


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


        this.view.addBackListener(e -> {
            navigateToLogin();
        });


        this.view.addShowPassListener(new java.awt.event.MouseAdapter() {
            private boolean visible = false;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                visible = !visible;
                view.setPasswordVisible(visible);
            }
        });


        this.view.addShowConfirmPassListener(new java.awt.event.MouseAdapter() {
            private boolean visible = false;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                visible = !visible;
                view.setConfirmPasswordVisible(visible);
            }
        });
    }


    private void navigateToLogin() {
        view.dispose();
        LoginForm loginForm = new LoginForm();
        new LoginController(loginForm);
        loginForm.setLocationRelativeTo(null);
        loginForm.setVisible(true);
    }

    public boolean handleRegister(User user) {
        return registerService.register(user);
    }
}
