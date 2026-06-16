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
        loadSummaryData();
        
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

    private void loadSummaryData() {
        // 1. Phần Tổng vé đã bán (Bạn đang làm đúng rồi, giữ nguyên)
        int totalTickets = revenueController.handleGetTotalTickets();
        lblTotalTickets.setText(totalTickets + " vé");
        
        // 2. PHẦN TÌM PHIM BÁN CHẠY NHẤT (Dùng mẹo quét trực tiếp từ Bảng)
        int maxTickets = 0;
        String topMovieName = "Chưa có dữ liệu";

        // Quét lần lượt từng dòng trên cái bảng 'revenue' của bạn
        for (int i = 0; i < revenue.getRowCount(); i++) {
            try {
                // Cột 1 là 'Số vé bán' -> Lấy ra và ép kiểu về số nguyên
                int tickets = Integer.parseInt(revenue.getValueAt(i, 1).toString());
                
                // Nếu số vé dòng này lớn hơn kỷ lục hiện tại -> Cập nhật kỷ lục
                if (tickets > maxTickets) {
                    maxTickets = tickets;
                    // Cột 0 là 'Tên phim' -> Lấy tên phim tương ứng
                    topMovieName = revenue.getValueAt(i, 0).toString();
                }
            } catch (Exception e) {
                // Bỏ qua nếu có dòng bị lỗi định dạng
            }
        }
        
        // Bắn tên phim tìm được lên giao diện
        lblTopMovie.setText(topMovieName);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        revenue = new javax.swing.JTable();
        BacktoDashBoard = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTotalRevenue = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalTickets = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblTopMovie = new javax.swing.JTextField();

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

        BacktoDashBoard.setBackground(new java.awt.Color(255, 102, 51));
        BacktoDashBoard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BacktoDashBoard.setForeground(new java.awt.Color(255, 255, 255));
        BacktoDashBoard.setText("Back");
        BacktoDashBoard.addActionListener(this::BacktoDashBoardActionPerformed);

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tổng doanh thu");

        lblTotalRevenue.addActionListener(this::lblTotalRevenueActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("VNĐ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tổng vé đã bán");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Vé");

        lblTotalTickets.addActionListener(this::lblTotalTicketsActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTotalTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTotalTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phim bán chạy nhất");

        lblTopMovie.addActionListener(this::lblTopMovieActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTopMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTopMovie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(BacktoDashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BacktoDashBoard)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTotalRevenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTotalRevenueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTotalRevenueActionPerformed

    private void lblTotalTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTotalTicketsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTotalTicketsActionPerformed

    private void lblTopMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTopMovieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTopMovieActionPerformed

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

        // -- Đăng xuất & Tài khoản
        javax.swing.JMenu menuSystem = new javax.swing.JMenu("Tài khoản");
        
        // ==========================================
        // ĐÃ BỔ SUNG NÚT TRANG CÁ NHÂN VÀO ĐÂY
        // ==========================================
        javax.swing.JMenuItem itemProfile = new javax.swing.JMenuItem("👤 Trang cá nhân");
        itemProfile.addActionListener(e -> {
            view.ProfileForm profileForm = new view.ProfileForm();
            profileForm.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
            new controller.ProfileController(profileForm, utils.Session.getCurrentUser()); 
            profileForm.setLocationRelativeTo(mainFrame);
            profileForm.setVisible(true);
        });
        menuSystem.add(itemProfile);

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lblTopMovie;
    private javax.swing.JTextField lblTotalRevenue;
    private javax.swing.JTextField lblTotalTickets;
    private javax.swing.JTable revenue;
    // End of variables declaration//GEN-END:variables


  

    

}
