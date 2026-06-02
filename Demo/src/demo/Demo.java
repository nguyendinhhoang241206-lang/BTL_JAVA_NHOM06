/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo;

import controller.LoginController;
import utils.FakeData;
import view.LoginForm;

/**
 *
 * @author DINH HOANG
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1. Khởi tạo dữ liệu giả vào file users.dat (Nếu file đã có data, bạn có thể comment dòng này lại)
        FakeData.main(new String[]{}); 

        // 2. Khởi tạo UI trên luồng đồ họa của Swing
        java.awt.EventQueue.invokeLater(() -> {
            // Khởi tạo View
            LoginForm view = new LoginForm();
            
            // Khởi tạo Controller và bơm View vào. 
            // Lúc này Controller mới bắt đầu "lắng nghe" nút Xác nhận trên View.
            LoginController controller = new LoginController(view);
            
            // Hiển thị UI ra giữa màn hình
            view.setLocationRelativeTo(null); 
            view.setVisible(true);
        });
    }
    
}
