/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.RevenueReportController;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class RevenueForm extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private RevenueReportController revenueController = new RevenueReportController();

    public RevenueForm() {
        initComponents();
        setLocationRelativeTo(null);

        tableModel = (DefaultTableModel) revenue.getModel();
        tableModel.setRowCount(0);

        loadRevenue();
    }

    
    private void loadRevenue() {
        List<Object[]> rows = revenueController.handleGetTotalRevenue();
        for (Object[] row : rows) {
            tableModel.addRow(row);
        }

        // 2. Lấy tổng doanh thu và hiển thị lên TextField
        double total = revenueController.getTotalRevenue();
        
        // Hiển thị số tiền kèm định dạng (VD: 450,000 VNĐ)
        lblTotalRevenue.setText(String.format("%,.0f VNĐ", total));
        
        // Khóa TextField lại để người dùng không thể tự gõ/sửa số tiền
        lblTotalRevenue.setEditable(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        revenue = new javax.swing.JTable();
        lblTotalRevenue = new javax.swing.JTextField();
        BacktoDashBoard = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("THỐNG KÊ DOANH THU");

        revenue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Phim", "Số vé bán", "Doanh thu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(revenue);

        BacktoDashBoard.setText("Back");
        BacktoDashBoard.addActionListener(this::BacktoDashBoardActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BacktoDashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BacktoDashBoard))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BacktoDashBoardActionPerformed(java.awt.event.ActionEvent evt) {
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
        itemSchedule.addActionListener(e -> {
            new view.showtimeroom_schedule().setVisible(true);
            mainFrame.dispose();
        });
        menuNav.add(itemSchedule);

        // -- Nút Phòng chiếu
        javax.swing.JMenuItem itemRoom = new javax.swing.JMenuItem("🏢 Quản lý Phòng chiếu");
        itemRoom.addActionListener(e -> {
            new view.show_time_room_infrForm().setVisible(true);
            mainFrame.dispose();
        });
        menuNav.add(itemRoom);

        // -- Nút Doanh thu
        javax.swing.JMenuItem itemRevenue = new javax.swing.JMenuItem("📈 Báo cáo Doanh thu");
        itemRevenue.addActionListener(e -> {
            new view.RevenueForm().setVisible(true);
            mainFrame.dispose();
        });
        menuNav.add(itemRevenue);

        // -- Đăng xuất
        javax.swing.JMenu menuSystem = new javax.swing.JMenu("Tài khoản");
        javax.swing.JMenuItem itemLogout = new javax.swing.JMenuItem("🚪 Đăng xuất");
        itemLogout.addActionListener(e -> {
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
        this.dispose();
    }

    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FavoriteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> new RevenueForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BacktoDashBoard;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lblTotalRevenue;
    private javax.swing.JTable revenue;
    // End of variables declaration//GEN-END:variables

  

    

}
