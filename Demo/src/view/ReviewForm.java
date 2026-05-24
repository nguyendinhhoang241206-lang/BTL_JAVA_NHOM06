/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.BookingHistoryController;
import javax.swing.DefaultButtonModel;
import javax.swing.table.DefaultTableModel;
import model.Booking;
import model.ShowTime;
import model.Movie;
import model.Seat;
import java.util.List;

/**
 *
 * @author nguyen tien dat
 */
public class ReviewForm extends javax.swing.JFrame {
    
    private DefaultTableModel tableModel;
    private BookingHistoryController historyController = new BookingHistoryController();

    /**
     * Creates new form ReviewForm
     */
    public ReviewForm(String userId) {
        initComponents();
        
        tableModel = (DefaultTableModel) history_Ticket.getModel();
        tableModel.setRowCount(0);
        
        loadDataToTable(userId);
    }

    private void loadDataToTable(String currentUserId) {
        List<Object[]> rows = historyController.handleGetHistoryByUserId(currentUserId);
        
        for (Object[] row : rows) {
            tableModel.addRow(row);
        }
    }
    
    //lịch sử đặt vé
//    private void loadMoviesToTable() {
//        tableModel.setRowCount(0); // Xóa bảng cũ
////        List<Movie> list = movieDAO.findAll();
//        List<Movie> list = movieDAO.getFakeMovies();
//        
//        for (Movie m : list) {
//            Object[] row = new Object[]{
//                m.getId(), 
//                m.getTitle(), 
//                m.getDirector(), 
//                m.getDuration(), 
//                m.getReleaseDate(),
//                m.getDescription()
//            };
//            tableModel.addRow(row);
//        }
//    }
    
//    private void tblPhimMouseClicked(java.awt.event.MouseEvent evt) {                                     
//        int selectedRow = tblPhim.getSelectedRow();
//        if (selectedRow >= 0) {
//            // Lấy dữ liệu từ các cột tương ứng (thứ tự 0, 1, 2... giống hàm loadMoviesToTable)
//            String id = tableModel.getValueAt(selectedRow, 0).toString();
//            String title = tableModel.getValueAt(selectedRow, 1).toString();
//            String director = tableModel.getValueAt(selectedRow, 2).toString();
//            String duration = tableModel.getValueAt(selectedRow, 3).toString();
//            String releaseDate = tableModel.getValueAt(selectedRow, 4).toString();
//            String description = tableModel.getValueAt(selectedRow, 5).toString();
//
//            // Đẩy lên các ô nhập liệu (Đổi txt... thành tên biến ô text của bạn)
//            txtId.setText(id);
//            txtTitle.setText(title);
//            txtDirector.setText(director);
//            txtDuration.setText(duration);
//            txtReleaseDate.setText(releaseDate); // Định dạng nhập phải chuẩn yyyy-MM-dd
//            txtDescription.setText(description);
//        }
//    }
//    
//    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {                                       
//        try {
//            // 1. Thu thập dữ liệu từ Form
//            String id = txtId.getText();
//            String title = txtTitle.getText();
//            String director = txtDirector.getText();
//            String description = txtDescription.getText();
//            int duration = Integer.parseInt(txtDuration.getText()); // Ép kiểu số
//            LocalDate releaseDate = LocalDate.parse(txtReleaseDate.getText()); // Ép kiểu ngày (VD: 2026-05-23)
//
//            // 2. Đóng gói thành đối tượng Movie
//            Movie newMovie = new Movie(id, title, description, director, duration, releaseDate);
//
//            // 3. Gọi Service xử lý
//            if (addService.addMovie(newMovie)) {
//                JOptionPane.showMessageDialog(this, "Thêm phim thành công!");
//                loadMoviesToTable(); // Load lại bảng
//            } else {
//                JOptionPane.showMessageDialog(this, "Thêm thất bại (Có thể do trùng mã ID)!");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho Thời lượng và Ngày (yyyy-MM-dd)");
//        }
//    }
//    
//    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {                                        
//        try {
//            String id = txtId.getText(); // ID là mốc để tìm kiếm nên không được sửa trên form
//            String title = txtTitle.getText();
//            String director = txtDirector.getText();
//            String description = txtDescription.getText();
//            int duration = Integer.parseInt(txtDuration.getText());
//            LocalDate releaseDate = LocalDate.parse(txtReleaseDate.getText());
//
//            Movie editedMovie = new Movie(id, title, description, director, duration, releaseDate);
//
//            if (editService.editMovie(editedMovie)) {
//                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
//                loadMoviesToTable();
//            } else {
//                JOptionPane.showMessageDialog(this, "Sửa thất bại (Không tìm thấy mã phim)!");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!");
//        }
//    }
//    
//    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
//        String id = txtId.getText();
//        
//        if (id.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phim trên bảng để xóa!");
//            return;
//        }
//
//        // Hộp thoại xác nhận trước khi xóa
//        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa phim này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            if (deleteService.deleteMovie(id)) {
//                JOptionPane.showMessageDialog(this, "Xóa thành công!");
//                loadMoviesToTable(); // Load lại bảng
//                
//                // Xóa trắng các ô nhập liệu sau khi xóa xong
//                txtId.setText("");
//                txtTitle.setText("");
//                // ... set rỗng cho các ô khác ...
//            } else {
//                JOptionPane.showMessageDialog(this, "Lỗi: Không thể xóa (Phim không tồn tại hoặc đang có lịch chiếu)!");
//            }
//        }
//    }
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        history_Ticket = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("LỊCH SỬ ĐẶT VÉ");

        history_Ticket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã đặt vé", "Phim", "Suất chiếu", "Rạp", "Ghế", "Số vé", "Tổng tiền", "Trạng thái", "Ngày đặt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(history_Ticket);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("DANH SÁCH ĐẶT VÉ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> new ReviewForm("U01").setVisible(true));
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable history_Ticket;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
