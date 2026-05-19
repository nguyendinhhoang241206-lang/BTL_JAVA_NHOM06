package controller;

import model.User;
import service.LogoutService;

public class LogoutController {
    private LogoutService logoutService = new LogoutService();

    // TODO: Sinh viên tự code logic: Nhận sự kiện nhấn nút Đăng xuất từ View, gọi logoutService.performLogout để xử lý phiên đăng xuất, và điều hướng người dùng quay trở lại màn hình Đăng nhập.
    public boolean handleLogout(User currentUser) {
        return false;
    }
}
