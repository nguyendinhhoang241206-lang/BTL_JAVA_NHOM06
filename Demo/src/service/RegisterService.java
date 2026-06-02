package service;

import dao.UserDAO;
import model.User;

public class RegisterService {
    private UserDAO userDAO = new UserDAO();

    // Kiểm tra xem username đã tồn tại chưa. Trả về true nếu bị trùng.
    public boolean isUsernameDuplicate(String username) {
        java.util.List<User> list = userDAO.readFromFile();
        if (list == null) return false;
        for (User u : list) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra xem email đã tồn tại chưa. Trả về true nếu bị trùng.
    public boolean isEmailDuplicate(String email) {
        java.util.List<User> list = userDAO.readFromFile();
        if (list == null) return false;
        for (User u : list) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra xem username hoặc email đã tồn tại trong danh sách User chưa để tránh trùng lặp. Trả về true nếu bị trùng.
    public boolean checkDuplicate(String username, String email) {
        return isUsernameDuplicate(username) || isEmailDuplicate(email);
    }

    // Kiểm tra độ dài mật khẩu, định dạng email có hợp lệ hay không trước khi đăng ký. Trả về true nếu hợp lệ.
    public boolean validateData(User user) {
        if (user == null) return false;
        // Kiểm tra username (3-20 ký tự chữ/số)
        if (!utils.ValidationUtil.isValidUsername(user.getUsername())) {
            return false;
        }
        // Kiểm tra định dạng email
        if (!utils.ValidationUtil.isValidEmail(user.getEmail())) {
            return false;
        }
        // Kiểm tra độ dài mật khẩu phải từ 6 ký tự trở lên
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            return false;
        }
        return true;
    }

    // Thêm đối tượng User mới vào danh sách thông qua userDAO.add(). Trả về true nếu đăng ký thành công.
    public boolean register(User user) {
        if (user == null) return false;
        
        // 1. Kiểm tra trùng lặp
        if (checkDuplicate(user.getUsername(), user.getEmail())) {
            return false;
        }
        
        // 2. Kiểm tra tính hợp lệ của dữ liệu
        if (!validateData(user)) {
            return false;
        }
        
        // 3. Tự động sinh ID mới dựa trên số lượng người dùng hiện tại (ví dụ: U04, U05...)
        java.util.List<User> list = userDAO.readFromFile();
        int nextIndex = (list != null) ? list.size() + 1 : 1;
        String generatedId = String.format("U%02d", nextIndex);
        user.setId(generatedId);
        
        // 4. Gán vai trò mặc định là khách hàng CUSTOMER và trạng thái là ACTIVE
        user.setRole(model.enums.Role.CUSTOMER);
        user.setStatus(model.enums.UserStatus.ACTIVE);
        
        // 5. Khởi tạo danh sách phim yêu thích rỗng
        user.setFavoriteMovieIds(new java.util.ArrayList<>());
        
        // 6. Lưu xuống file thông qua DAO
        return userDAO.add(user);
    }

}
