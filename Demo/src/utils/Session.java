package utils;

import model.User;

public class Session {

    private static User currentUser = null;
    public static void login(User user) {
        currentUser = user;
        System.out.println("Đã ghi nhận phiên đăng nhập cho user: " + user.getUsername());
    }

    public static void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUsername() + " đã đăng xuất.");
        }
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
