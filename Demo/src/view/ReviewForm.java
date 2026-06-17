package view;

import controller.ReviewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import model.Movie;
import model.Review;
import utils.Session;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author nguyen tien dat
 */
public class ReviewForm extends javax.swing.JFrame {
    private Movie currentMovie;
    private ReviewController reviewController = new ReviewController();
    private DefaultTableModel tableModel;   
    

    public ReviewForm(Movie movie) {
        initComponents();
        
        this.currentMovie = movie;
        setTitle("Cinema Pro - Đánh giá phim: " + (movie != null ? movie.getTitle() : ""));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initCustomTable();
        loadReviews();
    }

    private void initCustomTable() {
        tableModel = (DefaultTableModel) tableReviews.getModel();

        cbRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "5 Sao ⭐⭐⭐⭐⭐", "4 Sao ⭐⭐⭐⭐", "3 Sao ⭐⭐⭐", "2 Sao ⭐⭐", "1 Sao ⭐" 
        }));
        txtComment.setText("");
    }

    public void loadReviews() {
        if (tableModel == null) return;
        tableModel.setRowCount(0);
        
        if (currentMovie == null || currentMovie.getId() == null) {
            return; 
        }
        
        List<Review> reviews = reviewController.handleGetReviewsByMovie(currentMovie.getId());
        if (reviews != null) {
            for (Review r : reviews) {
                tableModel.addRow(new Object[]{
                        r.getUserId(), 
                        r.getRating() + " Sao", 
                        r.getComment()
                });
            }
        }
    }

    public void clearInputs() {
        txtComment.setText(""); 
        cbRating.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbRating = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        btnSubmit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableReviews = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        BacktoDashBoard = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("VIẾT ĐÁNH GIÁ CỦA BẠN");
        jLabel1.setFocusTraversalPolicyProvider(true);

        jLabel2.setText("Đánh giá: ");
        jLabel2.setFocusTraversalPolicyProvider(true);

        cbRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtComment.setColumns(20);
        txtComment.setRows(5);
        txtComment.setText("Nhập bình luận của bạn tại đây...");
        jScrollPane1.setViewportView(txtComment);

        btnSubmit.setBackground(new java.awt.Color(0, 0, 255));
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Gửi đánh giá");
        btnSubmit.addActionListener(this::btnSubmitActionPerformed);

        tableReviews.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tài khoản ", "Đánh giá", "Bình luận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableReviews);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("LỊCH SỬ ĐÁNH GIÁ");
        jLabel3.setFocusTraversalPolicyProvider(true);

        BacktoDashBoard.setBackground(new java.awt.Color(255, 102, 51));
        BacktoDashBoard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BacktoDashBoard.setForeground(new java.awt.Color(255, 255, 255));
        BacktoDashBoard.setText("Back");
        BacktoDashBoard.addActionListener(this::BacktoDashBoardActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSubmit))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbRating, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(BacktoDashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BacktoDashBoard)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String movieId = (currentMovie != null) ? currentMovie.getId() : null;
        String ratingStr = cbRating.getSelectedItem().toString();
        String comment = txtComment.getText();

        // Ném dữ liệu thô (String) và chính cái View này (this) sang Controller
        reviewController.handleAddReview(movieId, ratingStr, comment, this);
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void BacktoDashBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BacktoDashBoardActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
        // Quay lại màn hình danh sách phim của User
        view.UserMovieListForm userList = new view.UserMovieListForm();
        userList.setVisible(true);
    }//GEN-LAST:event_BacktoDashBoardActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BacktoDashBoard;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> cbRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableReviews;
    private javax.swing.JTextArea txtComment;
    // End of variables declaration//GEN-END:variables
}
