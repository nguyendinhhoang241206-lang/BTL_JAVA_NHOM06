package demo;

import controller.LoginController;
import utils.FakeData;
import view.LoginForm;
import view.showtimeroom_schedule; // Gom import lên đầu file cho chuẩn

public class Demo {

    public static void main(String[] args) {
        // 1. Khởi tạo dữ liệu giả vào file users.dat (Nếu file đã có data, bạn có thể comment dòng này lại)
        FakeData.main(new String[]{});

        // 2. Khởi chạy giao diện an toàn theo chuẩn luồng sự kiện của Java Swing
        java.awt.EventQueue.invokeLater(() -> {

            // --- LUỒNG 1: MỞ MÀN HÌNH ĐĂNG NHẬP (LUỒNG CHÍNH) ---
            LoginForm view = new LoginForm();
            LoginController controller = new LoginController(view);

            view.setLocationRelativeTo(null); // Hiển thị ra giữa màn hình
            view.setVisible(true);

            // --------------------------------------------------------
            // --- LUỒNG 2: MỞ MÀN HÌNH QUẢN LÝ LỊCH CHIẾU ---
            // (Hiện tại mình đang đóng comment phần này lại để tránh việc
            // nó bật lên cùng lúc 2 cửa sổ. Nếu bạn muốn bỏ qua đăng nhập
            // để test thẳng luồng này thì mở comment đoạn dưới đây ra nhé)

            /*
            showtimeroom_schedule showTimeForm = new showtimeroom_schedule();
            showTimeForm.setLocationRelativeTo(null);
            showTimeForm.setVisible(true);
            */
        });
    }
}   