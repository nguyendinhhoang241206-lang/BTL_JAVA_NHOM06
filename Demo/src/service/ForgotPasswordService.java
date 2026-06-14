package service;

import dao.UserDAO;
import model.User;

public class ForgotPasswordService {

    private final UserDAO userDAO = new UserDAO();

    public void resetPassword(String username,
            String email,
            String newPassword) {

        utils.ValidationUtil.validateUsername(username);
        utils.ValidationUtil.validateEmail(email);
        utils.ValidationUtil.validatePassword(newPassword);

        if (newPassword.length() < 6) {
            throw new IllegalArgumentException(
                    "Mật khẩu mới phải từ 6 ký tự trở lên!");
        }

        User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException(
                    "Tên đăng nhập không tồn tại!");
        }

        if (user.getEmail() == null
                || !user.getEmail().equalsIgnoreCase(email)) {

            throw new IllegalArgumentException(
                    "Email không trùng khớp với tài khoản!");
        }

        user.setPassword(newPassword);

        if (!userDAO.update(user)) {
            throw new IllegalArgumentException(
                    "Không thể cập nhật mật khẩu!");
        }
    }
}
