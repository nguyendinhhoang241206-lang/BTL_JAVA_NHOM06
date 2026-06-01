/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo;

import view.SelectShowTimeFrame;

import javax.swing.*;

/**
 *
 * @author DINH HOANG
 */
public class Demo {


    public static void main(String[] args) {
        // Chạy trong luồng an toàn của Java Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Truyền thử một mã phim giả lập (ví dụ "M001") vào Constructor
                SelectShowTimeFrame testFrame = new SelectShowTimeFrame("M001");

                // Lệnh quan trọng nhất để cửa sổ hiển thị lên màn hình
                testFrame.setVisible(true);
            }
        });
    }
}