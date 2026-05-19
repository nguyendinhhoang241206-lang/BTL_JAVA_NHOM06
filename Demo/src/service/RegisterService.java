package service;

import dao.UserDAO;
import model.User;

public class RegisterService {
    private UserDAO userDAO = new UserDAO();

    // TODO: Sinh viên tự code logic: Kiểm tra xem username hoặc email đã tồn tại trong danh sách User của hệ thống chưa để tránh trùng lặp tài khoản. Trả về true nếu bị trùng lặp.
    public boolean checkDuplicate(String username, String email) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Kiểm tra độ dài mật khẩu, định dạng email, số điện thoại có hợp lệ hay không trước khi đăng ký. Trả về true nếu hợp lệ.
    public boolean validateData(User user) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Thêm đối tượng User mới vào danh sách thông qua userDAO.add(). Trả về true nếu thành công.
    public boolean register(User user) {
        return false;
    }
}
