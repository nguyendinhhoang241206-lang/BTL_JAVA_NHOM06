package service.authentication;

import dao.UserDAO;
import model.User;
import utils.ValidationUtil;

public class RegisterService {

    private UserDAO userDAO = new UserDAO();

    public boolean isUsernameDuplicate(String username) {
        java.util.List<User> list = userDAO.readFromFile();

        for (User u : list) {
            if (u.getUsername() != null
                    && u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    public boolean isEmailDuplicate(String email) {
        java.util.List<User> list = userDAO.readFromFile();

        for (User u : list) {
            if (u.getEmail() != null
                    && u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public boolean register(User user) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "Thông tin người dùng không hợp lệ");
        }

        ValidationUtil.validateUsername(user.getUsername());
        ValidationUtil.validateEmail(user.getEmail());
        ValidationUtil.validatePassword(user.getPassword());

        if (user.getPassword().length() < 6) {
            throw new IllegalArgumentException(
                    "Mật khẩu phải từ 6 ký tự trở lên");
        }

        if (isUsernameDuplicate(user.getUsername())) {
            throw new IllegalArgumentException(
                    "Tên đăng nhập đã tồn tại");
        }

        if (isEmailDuplicate(user.getEmail())) {
            throw new IllegalArgumentException(
                    "Email đã tồn tại");
        }

        java.util.List<User> list = userDAO.readFromFile();

        int nextIndex = list.size() + 1;

        user.setId(String.format("U%02d", nextIndex));
        user.setRole(model.enums.Role.USER);
        user.setStatus(model.enums.UserStatus.ACTIVE);
        user.setFavoriteMovieIds(new java.util.ArrayList<>());

        return userDAO.add(user);
    }
}
