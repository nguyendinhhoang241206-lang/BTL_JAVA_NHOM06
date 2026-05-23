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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Test Phân Quyền Chuẩn MVC");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                ShowlistmovieForm view = new ShowlistmovieForm();
                
                // --- TEST TRƯỜNG HỢP 1: ĐĂNG NHẬP BẰNG TÀI KHOẢN USER/VIEWER ---
                // Ông truyền chữ "USER" hoặc "VIEWER" vào đây
                MovieSearchController controller = new MovieSearchController(view, "USER");
                
                // --- TEST TRƯỜNG HỢP 2: ĐĂNG NHẬP BẰNG TÀI KHOẢN ADMIN ---
                // Khi nào muốn test quyền Admin, ông đổi chữ "USER" ở trên thành "ADMIN" nhé:
                // MovieSearchController controller = new MovieSearchController(view, "ADMIN");
                
                frame.add(view);
                frame.setSize(950, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
