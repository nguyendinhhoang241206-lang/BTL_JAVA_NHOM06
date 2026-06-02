package util;

import model.User;

public class SessionUtil {
    
    // Biến static lưu trữ đối tượng User đang đăng nhập hiện tại
    private static User currentUser = null;

    // Gọi hàm này khi ĐĂNG NHẬP THÀNH CÔNG để lưu user vào hệ thống
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Gọi hàm này ở CÁC FORM KHÁC để lấy thông tin user ra dùng
    public static User getCurrentUser() {
        return currentUser;
    }

    // Hàm tiện ích kiểm tra xem có ai đang đăng nhập không
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // Gọi hàm này khi ĐĂNG XUẤT để xóa dữ liệu
    public static void clearSession() {
        currentUser = null;
    }
}