package demo;

import controller.authentication.LoginController;
import utils.FakeData;
import view.authentication.LoginForm;

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