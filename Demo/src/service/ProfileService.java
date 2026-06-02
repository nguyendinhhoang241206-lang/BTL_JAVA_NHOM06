/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import model.User;
import model.enums.Gender;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfileService {
    private UserDAO userDAO;

    public ProfileService() {
        this.userDAO = new UserDAO();
    }

    // Xử lý logic ép kiểu và gọi DAO để ghi file
    public boolean updateProfile(User user, String email, String phone, String genderStr, String birthdayStr) throws Exception {
        user.setEmail(email);
        user.setPhone(phone);
        
        // Chuẩn hóa giới tính
        try {
            user.setGender(Gender.valueOf(genderStr.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new Exception("Giới tính không hợp lệ!");
        }

        // Chuẩn hóa ngày sinh
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(birthdayStr, formatter);
            user.setBirthday(dob);
        } catch (Exception e) {
            throw new Exception("Ngày sinh phải có định dạng yyyy-MM-dd!");
        }

        // userDAO.update() đã có sẵn logic gọi hàm writeToFile() bên trong nó
        return userDAO.update(user);
    }
}