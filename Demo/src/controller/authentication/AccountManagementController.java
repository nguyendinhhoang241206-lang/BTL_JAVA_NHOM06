package controller.authentication;

import view.authentication.AccountManagementForm;
import service.authentication.AccountManagementService;
import model.User;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AccountManagementController {
    private AccountManagementForm view;

    private AccountManagementService service; 
    private User currentUser;

    public AccountManagementController(AccountManagementForm view, User currentUser) {
        this.view = view;

        this.service = new AccountManagementService(); 
        this.currentUser = currentUser;
        initView();
        initController();
    }

    private void initView() {
        loadAccounts();
        view.getLblMessage().setText("");
        javax.swing.JTable table = view.getTblAccounts();
        table.getTableHeader().setReorderingAllowed(false);
    }

    private void loadAccounts() {
        List<User> users = service.getAllUsers();
        DefaultTableModel model = (DefaultTableModel) view.getTblAccounts().getModel();
        model.setRowCount(0);

        for (User user : users) {
            model.addRow(new Object[]{
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getRole() != null ? user.getRole().toString() : "",
                user.getStatus() != null ? user.getStatus().toString() : "",
                user.isRequestedAdmin() ? "Đang yêu cầu" : "Không"
            });
        }
    }

    private void initController() {

        view.getBtnGrantAdmin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTblAccounts().getSelectedRow();
                if (selectedRow == -1) {
                    showMessage("Vui lòng chọn một người dùng từ bảng!", Color.RED);
                    return;
                }

                String username = (String) view.getTblAccounts().getValueAt(selectedRow, 0);
                String role = (String) view.getTblAccounts().getValueAt(selectedRow, 3);

                if ("ADMIN".equalsIgnoreCase(role)) {
                    showMessage("Người dùng này đã là Admin!", Color.RED);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Bạn có chắc chắn muốn cấp quyền Admin cho tài khoản '" + username + "'?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        boolean success = service.grantAdmin(username);
                        if (success) {
                            showMessage("Cấp quyền Admin cho " + username + " thành công!", Color.GREEN);
                            loadAccounts();
                        } else {
                            showMessage("Cấp quyền thất bại!", Color.RED);
                        }
                    } catch (Exception ex) {
                        showMessage("Có lỗi xảy ra: " + ex.getMessage(), Color.RED);
                    }
                }
            }
        });


        view.getBtnToggleLock().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTblAccounts().getSelectedRow();
                if (selectedRow == -1) {
                    showMessage("Vui lòng chọn một người dùng từ bảng!", Color.RED);
                    return;
                }

                String username = (String) view.getTblAccounts().getValueAt(selectedRow, 0);

                if (username.equals(currentUser.getUsername())) {
                    showMessage("Không thể tự khóa tài khoản của chính mình!", Color.RED);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Bạn có muốn thay đổi trạng thái khóa/mở khóa của tài khoản '" + username + "'?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        boolean success = service.toggleUserStatus(username);
                        if (success) {
                            showMessage("Cập nhật trạng thái tài khoản thành công!", Color.GREEN);
                            loadAccounts();
                        } else {
                            showMessage("Cập nhật thất bại!", Color.RED);
                        }
                    } catch (IllegalArgumentException ex) {
                        showMessage(ex.getMessage(), Color.RED);
                    } catch (Exception ex) {
                        showMessage("Có lỗi xảy ra: " + ex.getMessage(), Color.RED);
                    }
                }
            }
        });


        view.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void showMessage(String msg, Color color) {
        view.getLblMessage().setForeground(color);
        view.getLblMessage().setText(msg);
    }
}