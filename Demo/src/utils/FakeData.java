/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import dao.UserDAO;
import model.User;
import model.enums.Gender;
import model.enums.Role;
import model.enums.UserStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;

public class FakeData{
    
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // 1. Tài khoản Admin (Đăng nhập bình thường)
        User admin = new User(
                "U01", 
                "admin", 
                "123456", 
                Role.ADMIN, // Giả sử enum của bạn có ADMIN
                UserStatus.ACTIVE, 
                "admin@gmail.com", 
                "0987654321", 
                Gender.MALE, 
                LocalDate.of(2000, 1, 1), 
                Arrays.asList("M1", "M2")
        );

        // 2. Tài khoản User thường (Đăng nhập bình thường)
        User normalUser = new User(
                "U02", 
                "datnguyen", 
                "password123", 
                Role.CUSTOMER, // Giả sử enum của bạn có CUSTOMER
                UserStatus.ACTIVE, 
                "dat@gmail.com", 
                "0123456789", 
                Gender.MALE, 
                LocalDate.of(2002, 5, 15), 
                Arrays.asList("M3", "M5")
        );

        // 3. Tài khoản bị khóa (Dùng để test thông báo lỗi của View)
        User lockedUser = new User(
                "U03", 
                "badboy", 
                "111222", 
                Role.CUSTOMER, 
                UserStatus.LOCKED, // Trạng thái khóa
                "badboy@gmail.com", 
                "0999888777", 
                Gender.OTHER, 
                LocalDate.of(2005, 10, 10), 
                new ArrayList<>()
        );

        // Ghi vào file
        dao.add(admin);
        dao.add(normalUser);
        dao.add(lockedUser);

        System.out.println("✅ Đã tạo Fake Data thành công vào file users.dat!");
        System.out.println("Bạn có thể bật form Login lên để test.");
    }
}