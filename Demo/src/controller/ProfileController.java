package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import model.enums.Role;
import service.ProfileService;
import view.ProfileForm;

public class ProfileController {

    private ProfileForm view;
    private ProfileService profileService;
    private User loggedInUser;

    public ProfileController(ProfileForm view, User loggedInUser) {
        this.view = view;
        this.loggedInUser = loggedInUser;
        this.profileService = new ProfileService();

        initView();
        initController();
    }

    private void initView() {

        view.getTxtUsername().setEditable(false);
        view.getTxtRole().setEditable(false);

        view.getTxtUsername().setText(
                loggedInUser.getUsername());

        view.getTxtRole().setText(
                loggedInUser.getRole() != null
                ? loggedInUser.getRole().name()
                : "");

        view.getTxtEmail().setText(
                loggedInUser.getEmail() != null
                ? loggedInUser.getEmail()
                : "");

        view.getTxtPhone().setText(
                loggedInUser.getPhone() != null
                ? loggedInUser.getPhone()
                : "");

        view.getCbGender().setSelectedItem(
                loggedInUser.getGender() != null
                ? loggedInUser.getGender().name()
                : "MALE");

        if (loggedInUser.getBirthday() != null) {
            view.getTxtBirthday().setDate(
                    java.sql.Date.valueOf(loggedInUser.getBirthday())
            );
        }

        view.getLblMessage().setText("");

        if (loggedInUser.getRole() == Role.USER) {
            if (loggedInUser.isRequestedAdmin()) {
                view.getBtnRequestAdmin().setText("Đang chờ duyệt...");
                view.getBtnRequestAdmin().setEnabled(false);
            } else {
                view.getBtnRequestAdmin().setText("Yêu cầu cấp quyền");
                view.getBtnRequestAdmin().setEnabled(true);
            }
        } else if (loggedInUser.getRole() == Role.ADMIN) {
            view.getBtnRequestAdmin().setText("Quản lý tài khoản");
            view.getBtnRequestAdmin().setEnabled(true);
        }
    }

    private void initController() {

        view.getBtnSave().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String email = view.getTxtEmail().getText().trim();
                String phone = view.getTxtPhone().getText().trim();
                String gender = String.valueOf(view.getCbGender().getSelectedItem());

                java.util.Date date = view.getTxtBirthday().getDate();

                if (date == null) {
                    showMessage("Ngày sinh không được để trống!", Color.RED);
                    return;
                }

                java.time.LocalDate birthday = date.toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();

                try {

                    boolean success = profileService.updateProfile(
                            loggedInUser,
                            email,
                            phone,
                            gender,
                            birthday
                    );

                    if (success) {
                        showMessage("Cập nhật thông tin thành công!", Color.GREEN);
                    } else {
                        showMessage("Cập nhật thất bại!", Color.RED);
                    }

                } catch (IllegalArgumentException ex) {
                    showMessage(ex.getMessage(), Color.RED);

                } catch (Exception ex) {
                    showMessage("Có lỗi xảy ra!", Color.RED);
                    ex.printStackTrace();
                }
            }
        });

        view.getBtnBack().addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });


        view.getBtnRequestAdmin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedInUser.getRole() == Role.ADMIN) {

                    view.AccountManagementForm manageForm = new view.AccountManagementForm();
                    manageForm.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                    new controller.AccountManagementController(manageForm, loggedInUser);
                    manageForm.setLocationRelativeTo(view);
                    manageForm.setVisible(true);
                } else {

                    try {
                        boolean success = profileService.requestAdmin(loggedInUser);
                        if (success) {
                            showMessage("Gửi yêu cầu cấp quyền Admin thành công!", Color.GREEN);
                            view.getBtnRequestAdmin().setText("Đang chờ duyệt...");
                            view.getBtnRequestAdmin().setEnabled(false);
                        } else {
                            showMessage("Gửi yêu cầu cấp quyền thất bại!", Color.RED);
                        }
                    } catch (Exception ex) {
                        showMessage("Có lỗi xảy ra khi gửi yêu cầu!", Color.RED);
                    }
                }
            }
        });
    }

    private void showMessage(String message,
            Color color) {

        view.getLblMessage().setForeground(color);
        view.getLblMessage().setText(message);
    }
}
