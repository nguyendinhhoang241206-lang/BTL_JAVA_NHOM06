package utils;

import model.User;

public class Session {

    // Biến tĩnh lưu trữ người dùng đang đăng nhập hiện tại
    private static User currentUser = null;

    // ==========================================
    // CÁC HÀM XỬ LÝ PHIÊN ĐĂNG NHẬP
    // ==========================================

    /**
     * Gọi hàm này khi người dùng đăng nhập THÀNH CÔNG.
     * @param user Đối tượng User được lấy ra từ Database/File
     */
    public static void login(User user) {
        currentUser = user;
        System.out.println("Đã ghi nhận phiên đăng nhập cho user: " + user.getUsername());
    }

    /**
     * Gọi hàm này khi người dùng nhấn nút ĐĂNG XUẤT.
     * Xóa sạch thông tin người dùng hiện tại.
     */
    public static void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUsername() + " đã đăng xuất.");
        }
        currentUser = null;
    }

    /**
     * Lấy thông tin người dùng đang đăng nhập để sử dụng cho các chức năng khác.
     * @return Đối tượng User hiện tại, hoặc null nếu chưa đăng nhập.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Kiểm tra trạng thái xem ứng dụng đã có người đăng nhập chưa.
     * @return true nếu đã đăng nhập, ngược lại false.
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
