/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo;

import javax.swing.JFrame;
import view.ShowlistmovieForm;
import controller.MovieSearchController;

public class MainTest {
    public static void main(String[] args) {
        // Thiết lập giao diện Native (giao diện phẳng, hiện đại của Windows)
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Chạy Luồng UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Khởi tạo cửa sổ chính
                JFrame frame = new JFrame("Phần Mềm Quản Lý Phim - Chuẩn MVC");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // 1. Khởi tạo cái Xác (View rỗng)
                ShowlistmovieForm view = new ShowlistmovieForm();
                
                // 2. Khởi tạo Không gian và phân quyền (Controller)
                MovieSearchController controller = new MovieSearchController(view, "ADMIN");
                
                // 3. Đưa View vào khung và hiển thị lên giữa màn hình
                frame.add(view);
                frame.setSize(950, 650); 
                frame.setLocationRelativeTo(null); 
                frame.setVisible(true);
            }
        });
    }
}
