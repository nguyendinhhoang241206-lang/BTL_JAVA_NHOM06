package service;

import dao.UserDAO;
import model.User;
import model.enums.UserStatus;
import utils.ValidationUtil;

public class LoginService {
    private UserDAO userDAO = new UserDAO();

    public User getLoggedInUser(String username, String password) {

    ValidationUtil.validateUsername(username);
    ValidationUtil.validatePassword(password);

    User user = userDAO.findByUsername(username);

    if (user == null) {
        throw new IllegalArgumentException(
                "Tên đăng nhập không tồn tại");
    }

    if (user.getStatus() != UserStatus.ACTIVE) {
        throw new IllegalArgumentException(
                "Tài khoản đã bị khóa");
    }

    if (!user.getPassword().equals(password)) {
        throw new IllegalArgumentException(
                "Mật khẩu không chính xác");
    }

    return user;
}
}