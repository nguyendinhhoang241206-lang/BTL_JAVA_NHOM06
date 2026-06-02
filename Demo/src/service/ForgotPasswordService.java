package service;

import dao.UserDAO;
import model.User;

public class ForgotPasswordService {
    private UserDAO userDAO = new UserDAO();

    /**
     * Khôi phục mật khẩu mới dựa trên xác minh Tên đăng nhập và Gmail.
     * Vì là tính năng Quên mật khẩu nên sẽ bỏ qua việc so khớp mật khẩu cũ.
     * 
     * @param username Tên đăng nhập cần khôi phục
     * @param email Email đã đăng ký của tài khoản
     * @param oldPassword Mật khẩu cũ
     * @param newPassword Mật khẩu mới muốn đổi
     * @return null nếu thành công, hoặc chuỗi String thông báo lỗi chi tiết nếu thất bại
     */
    public String resetPassword(String username, String email, String oldPassword, String newPassword) {
        // 1. Tìm người dùng theo tên đăng nhập
        User user = userDAO.findByUsername(username);
        
        // 2. Kiểm tra nếu không tìm thấy người dùng
        if (user == null) {
            return "Tên đăng nhập không tồn tại!";
        }

        // 3. So khớp email đăng ký (không phân biệt hoa thường)
        if (user.getEmail() == null || !user.getEmail().equalsIgnoreCase(email)) {
            return "Email không trùng khớp với tài khoản!";
        }

        // 4. Kiểm tra mật khẩu cũ
        if (user.getPassword() == null || !user.getPassword().equals(oldPassword)) {
            return "Mật khẩu cũ không chính xác!";
        }

        // 5. Kiểm tra mật khẩu mới không được trùng với mật khẩu cũ
        if (newPassword.equals(oldPassword)) {
            return "Mật khẩu mới không được trùng với mật khẩu cũ!";
        }

        // 6. Thiết lập mật khẩu mới và lưu lại thông qua UserDAO.update()
        user.setPassword(newPassword);
        boolean updated = userDAO.update(user);
        if (!updated) {
            return "Lỗi cập nhật mật khẩu vào cơ sở dữ liệu!";
        }
        return null;
    }
}
