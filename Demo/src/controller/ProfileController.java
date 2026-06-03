package controller;

import model.User;
import model.enums.Role;
import view.ProfileForm;
import service.ProfileService;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController {
    private ProfileForm view;
    private ProfileService profileService;
    private User loggedInUser;

    public ProfileController(ProfileForm view, User loggedInUser) {
        this.view = view;
        this.loggedInUser = loggedInUser;
        this.profileService = new ProfileService(); // Sử dụng ProfileService

        initView();
        initController();
    }

    // Khởi tạo giao diện ban đầu
    private void initView() {
        // Vô hiệu hóa việc chỉnh sửa Username và Role
        view.getTxtUsername().setEditable(false);
        view.getTxtRole().setEditable(false);

        // Đổ dữ liệu của user lên form
        view.getTxtUsername().setText(loggedInUser.getUsername());
        view.getTxtRole().setText(loggedInUser.getRole() != null ? loggedInUser.getRole().name() : "");
        view.getTxtEmail().setText(loggedInUser.getEmail() != null ? loggedInUser.getEmail() : "");
        view.getTxtPhone().setText(loggedInUser.getPhone() != null ? loggedInUser.getPhone() : "");
        view.getCbGender().setSelectedItem(loggedInUser.getGender() != null ? loggedInUser.getGender().name() : "MALE");
        view.getTxtBirthday().setText(loggedInUser.getBirthday() != null ? loggedInUser.getBirthday().toString() : "");
        view.getLblMessage().setText("");

        // KIỂM TRA ROLE: Nếu là USER thì ẩn phần Danh sách yêu cầu cấp quyền
        if (loggedInUser.getRole() == Role.CUSTOMER) {
            view.getLblListTitle().setVisible(false);
            view.getScrollPaneList().setVisible(false);
            view.getBtnRequestAdmin().setVisible(false); // Ẩn luôn nút yêu cầu (tuỳ chọn)
        }
    }

    // Lắng nghe các sự kiện (Click button)
    private void initController() {
        // Xử lý nút Lưu (Ghi file)
        view.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = view.getTxtEmail().getText();
                String phone = view.getTxtPhone().getText();
                String gender = view.getCbGender().getSelectedItem().toString();
                String birthday = view.getTxtBirthday().getText();

                try {
                    // Gọi sang ProfileService để xử lý và lưu file
                    boolean success = profileService.updateProfile(loggedInUser, email, phone, gender, birthday);
                    if (success) {
                        showMessage("Cập nhật thông tin thành công!", Color.GREEN);

                        // ==========================================
                        // RELOAD LẠI DASHBOARD (ĐÓNG CŨ MỞ MỚI)
                        // ==========================================
                        // Quét qua tất cả các cửa sổ đang mở trên màn hình
                        for (java.awt.Window window : java.awt.Window.getWindows()) {
                            // Nếu phát hiện ra cái Dashboard Khách Hàng đang nằm chìm bên dưới
                            if (window instanceof view.UserMovieListForm) {
                                window.dispose(); // Tắt cái cũ đi
                                new view.UserMovieListForm().setVisible(true); // Bật cái mới lên (sẽ tự động lấy tên/email mới nhất từ Session)
                                break;
                            }
                        }

                    } else {
                        showMessage("Cập nhật thất bại. Vui lòng thử lại!", Color.RED);
                    }
                } catch (Exception ex) {
                    showMessage(ex.getMessage(), Color.RED); // Bắt lỗi định dạng ngày/giới tính
                }
            }
        });

        // Xử lý nút Quay lại
        view.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng form Profile lại, trả lại màn hình Dashboard
                view.dispose();
            }
        });
    }

    // Hàm tiện ích hiển thị thông báo dưới đáy Form
    private void showMessage(String message, Color color) {
        view.getLblMessage().setForeground(color);
        view.getLblMessage().setText(message);
    }
}