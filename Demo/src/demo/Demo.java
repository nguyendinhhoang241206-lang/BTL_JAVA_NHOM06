/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo;

// THÊM 2 DÒNG IMPORT NÀY ĐỂ KẾT NỐI CÁC THƯ MỤC
import view.show_time_room_infrForm;
import controller.RoomManagerController;

public class Demo {

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // 1. Khởi tạo class giao diện
                show_time_room_infrForm view = new show_time_room_infrForm();
                
                // 2. Truyền đối tượng view vào Controller
                RoomManagerController controller = new RoomManagerController(view);
                
                // 3. Hiển thị màn hình
                view.setVisible(true);
            }
        });
    }
}