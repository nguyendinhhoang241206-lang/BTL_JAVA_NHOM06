/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo;

import view.showtimeroom_schedule;

public class Demo {

    public static void main(String[] args) {
        // Khởi chạy giao diện an toàn theo chuẩn luồng sự kiện của Java Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Tạo một đối tượng của màn hình quản lý lịch chiếu và hiển thị lên
                showtimeroom_schedule showTimeForm = new showtimeroom_schedule();
                showTimeForm.setVisible(true);
            }
        });
    }
    
}