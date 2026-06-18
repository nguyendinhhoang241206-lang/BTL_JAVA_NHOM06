/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.booking;

import controller.authentication.LoginController;
import controller.authentication.ProfileController;
import controller.booking.ShowTimeManagerController;
import controller.movie.MovieController;

/**
 *
 * @author ADMIN
 */
public class showtimeroom_schedule extends javax.swing.JFrame {
    private ShowTimeManagerController controller = new ShowTimeManagerController();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(showtimeroom_schedule.class.getName());
    
    public showtimeroom_schedule() {
        initComponents(); 
        tblShowTime.getTableHeader().setReorderingAllowed(false);
        setLocationRelativeTo(null);
        setupUIDesign();
        controller.initComboBoxes(cbMovie, cbRoom);

        txtId1.setEditable(false);
        txtId1.setText(controller.getNextShowTimeId());

        javax.swing.SpinnerDateModel startModel = new javax.swing.SpinnerDateModel();
        spinStartTime.setModel(startModel);
        javax.swing.JSpinner.DateEditor startEditor = new javax.swing.JSpinner.DateEditor(spinStartTime, "HH:mm");
        spinStartTime.setEditor(startEditor);

        javax.swing.SpinnerDateModel endModel = new javax.swing.SpinnerDateModel();
        spinEndTime.setModel(endModel);
        javax.swing.JSpinner.DateEditor endEditor = new javax.swing.JSpinner.DateEditor(spinEndTime, "HH:mm");
        spinEndTime.setEditor(endEditor);
        controller.loadDataToTable((javax.swing.table.DefaultTableModel) tblShowTime.getModel());
        backtodashboard.addActionListener(e -> {
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
                new ProfileController(profileForm, utils.Session.getCurrentUser());
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
            this.dispose();
        });

        controller.loadDataToTable((javax.swing.table.DefaultTableModel) tblShowTime.getModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbMovie = new javax.swing.JComboBox<>();
        cbRoom = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtId1 = new javax.swing.JTextField();
        backtodashboard = new javax.swing.JButton();
        spinStartTime = new javax.swing.JSpinner();
        spinEndTime = new javax.swing.JSpinner();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShowTime = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Showtime Management Dashboard");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Create Showtime"));

        jLabel1.setText("Showtime ID");

        jLabel2.setText("Movie Name");

        jLabel3.setText("Screen Room");

        jLabel4.setText("Date (yyyy-MM-dd)");

        jLabel5.setText("Start Time");

        jLabel6.setText("End Time");

        cbMovie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMovie.addActionListener(this::cbMovieActionPerformed);

        cbRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAdd.setText("ADD");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnClear.setText("CLEAR");
        btnClear.addActionListener(this::btnClearActionPerformed);

        txtId1.addActionListener(this::txtId1ActionPerformed);

        backtodashboard.setText("Back");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbMovie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtId1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinStartTime)
                            .addComponent(spinEndTime))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(backtodashboard)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(cbMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
                .addComponent(spinEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(backtodashboard)
                .addGap(15, 15, 15))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Current Showtimes"));

        tblShowTime.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Showtime ID", "Movie Name", "Room", "Date", "Start Time", "End Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblShowTime);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(365, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMovieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMovieActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            spinStartTime.commitEdit();
            spinEndTime.commitEdit();

            String result = controller.handleAddShowTime(
                    txtId1.getText(),
                    dateChooser.getDate(),
                    (java.util.Date) spinStartTime.getValue(),
                    (java.util.Date) spinEndTime.getValue(),
                    cbMovie.getSelectedItem().toString(),
                    cbRoom.getSelectedItem().toString()
            );

            if ("SUCCESS".equals(result)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Xếp lịch chiếu thành công!");
                controller.loadDataToTable((javax.swing.table.DefaultTableModel) tblShowTime.getModel());

                btnClearActionPerformed(null);
            } else {
                String errorMsg = result.split(":")[1];
                javax.swing.JOptionPane.showMessageDialog(this, errorMsg, "Thông báo", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void txtId1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId1ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        txtId1.setText(controller.getNextShowTimeId());

        dateChooser.setDate(null);
        spinStartTime.setValue(new java.util.Date());
        spinEndTime.setValue(new java.util.Date());
        if (cbMovie.getItemCount() > 0) cbMovie.setSelectedIndex(0);
        if (cbRoom.getItemCount() > 0) cbRoom.setSelectedIndex(0);
        tblShowTime.clearSelection();
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblShowTime.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng click chọn một suất chiếu trên bảng để xóa!", "Thông báo", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idToDelete = tblShowTime.getValueAt(selectedRow, 0).toString();

        if (javax.swing.JOptionPane.showConfirmDialog(this, "Xóa vĩnh viễn suất chiếu '" + idToDelete + "'?", "Xác nhận", javax.swing.JOptionPane.YES_NO_OPTION) == javax.swing.JOptionPane.YES_OPTION) {
            if (controller.handleDeleteShowTime(idToDelete)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
                controller.loadDataToTable((javax.swing.table.DefaultTableModel) tblShowTime.getModel());
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                         

    /**
     * @param args the command line arguments
     */

private void setupUIDesign() {
        java.awt.Color whiteColor = new java.awt.Color(255, 255, 255);
        getContentPane().setBackground(whiteColor);
        jPanel1.setBackground(whiteColor);
        jPanel2.setBackground(whiteColor);

        java.awt.Font mainFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        java.awt.Color textColor = new java.awt.Color(51, 51, 51); 

        javax.swing.JLabel[] labels = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6};
        for (javax.swing.JLabel lbl : labels) {
            lbl.setFont(mainFont);
            lbl.setForeground(textColor);
        }

        java.awt.Font btnFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14);
        java.awt.Color btnBgColor = new java.awt.Color(255, 102, 0); 
        java.awt.Color btnTextColor = new java.awt.Color(255, 255, 255); 

        javax.swing.JButton[] buttons = {btnAdd, btnDelete, btnClear, backtodashboard};
        for (javax.swing.JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setBackground(btnBgColor);
            btn.setForeground(btnTextColor);
            
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
        javax.swing.table.JTableHeader header = tblShowTime.getTableHeader(); 

        header.setReorderingAllowed(false);

        header.setForeground(new java.awt.Color(255, 255, 255)); 
        header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14)); 

        header.setBackground(new java.awt.Color(255, 102, 0)); 

        ((javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer()).setBackground(new java.awt.Color(255, 102, 0));
    }   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backtodashboard;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox<String> cbMovie;
    private javax.swing.JComboBox<String> cbRoom;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spinEndTime;
    private javax.swing.JSpinner spinStartTime;
    private javax.swing.JTable tblShowTime;
    private javax.swing.JTextField txtId1;
    // End of variables declaration//GEN-END:variables
}
