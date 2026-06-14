package service;

import dao.UserDAO;
import model.User;
import model.enums.Gender;
import java.time.LocalDate;
import utils.ValidationUtil;

public class ProfileService {

    private UserDAO userDAO;

    public ProfileService() {
        this.userDAO = new UserDAO();
    }

    public boolean updateProfile(User user,
            String email,
            String phone,
            String genderStr,
            LocalDate birthday) {

        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy người dùng!");
        }

        // Validate email
        ValidationUtil.validateEmail(email);

        // Check duplicate email (excluding current user)
        for (User u : userDAO.readFromFile()) {
            if (!u.getId().equals(user.getId()) && u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException("Email đã được sử dụng bởi tài khoản khác!");
            }
        }

        // Validate phone
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống!");
        }

        if (!phone.matches("^0\\d{9}$")) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ!");
        }

        user.setEmail(email);
        user.setPhone(phone);

        // Gender
        try {
            user.setGender(Gender.valueOf(genderStr.toUpperCase()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Giới tính không hợp lệ!");
        }

        // Birthday (đÃ FIX: không parse String nữa)
        if (birthday == null) {
            throw new IllegalArgumentException("Ngày sinh không được để trống!");
        }

        user.setBirthday(birthday);

        // Update DB
        if (!userDAO.update(user)) {
            throw new IllegalArgumentException("Cập nhật hồ sơ thất bại!");
        }

        return true;
    }

    public boolean requestAdmin(User user) {
        if (user == null) return false;
        user.setRequestedAdmin(true);
        return userDAO.update(user);
    }
}