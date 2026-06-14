package utils;

public class ValidationUtil {

    public static void validateUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Tên đăng nhập không được để trống");
        }

        if (!username.matches("^[a-zA-Z0-9_]{3,20}$")) {
            throw new IllegalArgumentException(
                    "Tên đăng nhập không hợp lệ");
        }
    }

    public static void validatePassword(String password) {

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Mật khẩu không được để trống");
        }
    }

    public static void validateEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Email không được để trống");
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException(
                    "Email không đúng định dạng");
        }
    }
}