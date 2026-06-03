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
import view.showtimeroom_schedule;

public class Demo {

    public static void main(String[] args) {
        FakeData.main(new String[]{});

        java.awt.EventQueue.invokeLater(() -> {

            LoginForm view = new LoginForm();

            LoginController controller = new LoginController(view);

            view.setLocationRelativeTo(null);
            view.setVisible(true);
        });
    }
}