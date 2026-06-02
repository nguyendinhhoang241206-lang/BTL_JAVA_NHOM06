package service;

import dao.UserDAO;
import model.User;
import model.enums.UserStatus;

public class LoginService {
    private UserDAO userDAO = new UserDAO();

    // Nhận password dạng String bình thường, bỏ qua kiểm tra email vì giao diện LoginForm không có ô nhập email
    public boolean checkCredentials(String username, String email, String password) {
        User user = userDAO.findByUsername(username);

        if (user == null) {
            return false;
        }

        // Kiểm tra trực tiếp password (plain text) và bắt buộc tài khoản phải ACTIVE
        return user.getStatus() == UserStatus.ACTIVE 
            && user.getPassword() != null 
            && user.getPassword().equals(password);
    }

    // Phương thức quá tải chỉ kiểm tra Tên đăng nhập và Mật khẩu
    public boolean checkCredentials(String username, String password) {
        return checkCredentials(username, "", password);
    }


    public User getLoggedInUser(String username) {
        return userDAO.findByUsername(username);
    }
}