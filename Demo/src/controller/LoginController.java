package controller;

import model.User;
import service.LoginService;
import view.LoginForm;
import view.RegisterForm;
import view.ForgotPassword;
import utils.ValidationUtil;
import javax.swing.JOptionPane;

public class LoginController {

    private LoginService loginService = new LoginService();
    private LoginForm view;
    private User sessionUser;

    public LoginController(LoginForm view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Sự kiện khi nhấn nút "Xác nhận" (Đăng nhập)
        this.view.addConfirmListener(e -> {
            String username = view.getUsername();
            String password = view.getPassword();

            try {

                User user = loginService.getLoggedInUser(
                        username,
                        password);

                this.sessionUser = user;
                utils.Session.login(user);

                view.showMessage(
                        "Đăng nhập thành công!",
                        true);

            } catch (IllegalArgumentException ex) {

                view.showMessage(
                        ex.getMessage(),
                        false);

                return;

            } catch (Exception ex) {

                view.showMessage(
                        "Có lỗi xảy ra!",
                        false);

                return;
            }

            // Hiển thị thông báo chào mừng bằng Popup
            JOptionPane.showMessageDialog(view,
                    "Chào mừng " + sessionUser.getUsername() + " quay trở lại!",
                    "Đăng nhập thành công",
                    JOptionPane.INFORMATION_MESSAGE);

            if (sessionUser.getRole() != null
                    && (sessionUser.getRole().toString().equalsIgnoreCase("ADMIN")
                    || sessionUser.getRole().toString().equalsIgnoreCase("STAFF"))) {

                // 1. TẠO VỎ JFRAME ẢO CHO TRANG CHỦ (DASHBOARD)
                javax.swing.JFrame mainFrame = new javax.swing.JFrame("Trang chủ Quản trị - Cinema System");
                mainFrame.setSize(1000, 600);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

                // 2. TẠO THANH MENU TỔNG HỢP (CHỈ DASHBOARD MỚI CÓ)
                javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();

                javax.swing.JMenu menuNav = new javax.swing.JMenu("Chức năng Hệ thống");

                // -- Nút Lịch chiếu
                javax.swing.JMenuItem itemSchedule = new javax.swing.JMenuItem("📅 Quản lý Lịch chiếu");
                itemSchedule.addActionListener(evt -> {
                    new view.showtimeroom_schedule().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemSchedule);

                // -- Nút Phòng chiếu
                javax.swing.JMenuItem itemRoom = new javax.swing.JMenuItem("🏢 Quản lý Phòng chiếu");
                itemRoom.addActionListener(evt -> {
                    new view.show_time_room_infrForm().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemRoom);

                // -- Nút Doanh thu
                javax.swing.JMenuItem itemRevenue = new javax.swing.JMenuItem("📈 Báo cáo Doanh thu");
                itemRevenue.addActionListener(evt -> {
                    new view.RevenueForm().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemRevenue);

                // -- Đăng xuất
                javax.swing.JMenu menuSystem = new javax.swing.JMenu("Tài khoản");
                javax.swing.JMenuItem itemLogout = new javax.swing.JMenuItem("🚪 Đăng xuất");
                itemLogout.addActionListener(evt -> {
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(mainFrame, "Đăng xuất tài khoản?", "Xác nhận", javax.swing.JOptionPane.YES_NO_OPTION);
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        view.LoginForm loginForm = new view.LoginForm();
                        new controller.LoginController(loginForm);
                        loginForm.setVisible(true);
                        mainFrame.dispose();
                    }
                });
                menuSystem.add(itemLogout);

                menuBar.add(menuNav);
                menuBar.add(menuSystem);
                mainFrame.setJMenuBar(menuBar); // Đính Menu lên Dashboard

                // 3. NHÚNG MẢNH GHÉP QUẢN LÝ PHIM VÀO DASHBOARD
                view.ShowlistmovieForm showListPanel = new view.ShowlistmovieForm();
                new controller.MovieController(showListPanel); // Kích hoạt nút bấm phim

                mainFrame.add(showListPanel);
                mainFrame.setVisible(true);
                view.dispose();
            } else {
                view.UserMovieListForm userDashboard = new view.UserMovieListForm();
                userDashboard.setVisible(true);
                view.dispose(); // Đóng màn hình Login

            }
        });

        // Sự kiện khi nhấn nút "Đăng ký"
        this.view.addRegisterListener(e -> {
            view.setVisible(false); // Ẩn màn hình đăng nhập
            RegisterForm registerForm = new RegisterForm();
            new RegisterController(registerForm);
            registerForm.setLocationRelativeTo(null); // Hiển thị giữa màn hình
            registerForm.setVisible(true); // Hiển thị màn hình đăng ký
        });

        // Sự kiện khi nhấn nút "Quên mật khẩu?"
        this.view.addForgotPasswordListener(e -> {
            view.setVisible(false); // Ẩn màn hình đăng nhập
            ForgotPassword forgotForm = new ForgotPassword();
            new ForgotPasswordController(forgotForm);
            forgotForm.setLocationRelativeTo(null); // Hiển thị giữa màn hình
            forgotForm.setVisible(true); // Hiển thị màn hình quên mật khẩu
        });
    }

    public User getSessionUser() {
        return sessionUser;
    }
}
