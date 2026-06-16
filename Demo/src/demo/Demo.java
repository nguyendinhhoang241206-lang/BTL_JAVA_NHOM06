package demo;

import controller.LoginController;
import utils.FakeData;
import view.LoginForm;
import view.showtimeroom_schedule; // Gom import lên đầu file cho chuẩn

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