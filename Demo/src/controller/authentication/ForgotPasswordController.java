package controller.authentication;

import service.authentication.ForgotPasswordService;
import view.authentication.ForgotPassword;
import view.authentication.LoginForm;

import javax.swing.JOptionPane;

public class ForgotPasswordController {

    private ForgotPasswordService forgotPasswordService = new ForgotPasswordService();
    private ForgotPassword view;

    public ForgotPasswordController(ForgotPassword view) {
        this.view = view;
        initController();
    }

    private void initController() {

        this.view.addResetListener(e -> {

            try {

                String username = view.getUsername();
                String email = view.getEmail();
                String newPassword = view.getNewPassword();
                String confirmPassword = view.getConfirmPassword();

                if (!newPassword.equals(confirmPassword)) {
                    throw new IllegalArgumentException(
                            "Mật khẩu xác nhận không trùng khớp!");
                }

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

        this.view.addShowNewPassListener(new java.awt.event.MouseAdapter() {
            private boolean visible = false;
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                visible = !visible;
                view.setNewPassVisible(visible);
            }
        });


        this.view.addShowConfirmPassListener(new java.awt.event.MouseAdapter() {
            private boolean visible = false;
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                visible = !visible;
                view.setConfirmPassVisible(visible);
            }
        });

        this.view.addBackListener(e -> {
            navigateToLogin();
        });
    }


    private void navigateToLogin() {
        view.dispose();
        LoginForm loginForm = new LoginForm();
        new LoginController(loginForm);
        loginForm.setLocationRelativeTo(null);
        loginForm.setVisible(true);
    }
}
