package service;

import dao.UserDAO;
import model.User;

public class LoginService {
    private UserDAO userDAO = new UserDAO();

    // TODO: Sinh viên tự code logic: Kiểm tra sự tồn tại của tài khoản và so khớp thông tin mật khẩu có đúng với mật khẩu đã lưu không. Trả về true nếu hợp lệ.
    public boolean checkCredentials(String username, String password) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Trả về đối tượng User đăng nhập thành công để lưu session sử dụng sau này.
    public User getLoggedInUser(String username) {
        return null;
    }
}
