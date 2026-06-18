package controller.authentication;

import controller.movie.MovieController;
import model.User;
import service.authentication.LoginService;
import view.authentication.LoginForm;
import view.authentication.RegisterForm;
import view.authentication.ForgotPassword;

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


            JOptionPane.showMessageDialog(view,
                    "Chào mừng " + sessionUser.getUsername() + " quay trở lại!",
                    "Đăng nhập thành công",
                    JOptionPane.INFORMATION_MESSAGE);

            if (sessionUser.getRole() != null
                    && sessionUser.getRole().toString().equalsIgnoreCase("ADMIN")) {


                javax.swing.JFrame mainFrame = new javax.swing.JFrame("Trang chủ Quản trị - Cinema System");
                mainFrame.setSize(1000, 600);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

                javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();

                javax.swing.JMenu menuNav = new javax.swing.JMenu("Chức năng Hệ thống");

                javax.swing.JMenuItem itemSchedule = new javax.swing.JMenuItem("📅 Quản lý Lịch chiếu");
                itemSchedule.addActionListener(evt -> {
                    new view.booking.showtimeroom_schedule().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemSchedule);


                javax.swing.JMenuItem itemRoom = new javax.swing.JMenuItem("🏢 Quản lý Phòng chiếu");
                itemRoom.addActionListener(evt -> {
                    new view.booking.show_time_room_infrForm().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemRoom);

                javax.swing.JMenuItem itemRevenue = new javax.swing.JMenuItem("📈 Báo cáo Doanh thu");
                itemRevenue.addActionListener(evt -> {
                    new view.revenue.RevenueForm().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemRevenue);

                javax.swing.JMenu menuSystem = new javax.swing.JMenu("Tài khoản");

                javax.swing.JMenuItem itemProfile = new javax.swing.JMenuItem("👤 Trang cá nhân");
                itemProfile.addActionListener(evt -> {
                    view.authentication.ProfileForm profileForm = new view.authentication.ProfileForm();
                    profileForm.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                    new ProfileController(profileForm, sessionUser);
                    profileForm.setLocationRelativeTo(mainFrame);
                    profileForm.setVisible(true);
                });
                menuSystem.add(itemProfile);

                javax.swing.JMenuItem itemLogout = new javax.swing.JMenuItem("🚪 Đăng xuất");
                itemLogout.addActionListener(evt -> {
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(mainFrame, "Đăng xuất tài khoản?", "Xác nhận", javax.swing.JOptionPane.YES_NO_OPTION);
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        view.authentication.LoginForm loginForm = new view.authentication.LoginForm();
                        new LoginController(loginForm);
                        loginForm.setVisible(true);
                        mainFrame.dispose();
                    }
                });
                menuSystem.add(itemLogout);

                menuBar.add(menuNav);
                menuBar.add(menuSystem);
                mainFrame.setJMenuBar(menuBar);


                view.booking.ShowlistmovieForm showListPanel = new view.booking.ShowlistmovieForm();
                new MovieController(showListPanel);

                mainFrame.add(showListPanel);
                mainFrame.setVisible(true);
                view.dispose();
            } else {

                view.booking.UserMovieListForm userDashboard = new view.booking.UserMovieListForm();
                userDashboard.setVisible(true);
                view.dispose();
            }
        });


        this.view.addRegisterListener(e -> {
            view.setVisible(false);
            RegisterForm registerForm = new RegisterForm();
            new RegisterController(registerForm);
            registerForm.setLocationRelativeTo(null);
            registerForm.setVisible(true);
        });

        this.view.addForgotPasswordListener(e -> {
            view.setVisible(false);
            ForgotPassword forgotForm = new ForgotPassword();
            new ForgotPasswordController(forgotForm);
            forgotForm.setLocationRelativeTo(null);
            forgotForm.setVisible(true);
        });

        this.view.addShowPasswordListener(new java.awt.event.MouseAdapter() {
            private boolean visible = false;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                visible = !visible;
                view.setPasswordVisible(visible);
            }
        });
    }

    public User getSessionUser() {
        return sessionUser;
    }
}