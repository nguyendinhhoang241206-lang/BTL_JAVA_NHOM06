/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.movie;

import controller.movie.ListFavoriteController;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.Movie;

public class FavoriteForm extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private ListFavoriteController listFavoriteController = new ListFavoriteController();

    public FavoriteForm() {
        initComponents();
        history_Ticket.getTableHeader().setReorderingAllowed(false);
        BacktoDashBoard.addActionListener(this::BacktoDashBoardActionPerformed);
        tableModel = (DefaultTableModel) history_Ticket.getModel();
        loadDataToTable();
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);
        List<Movie> movies = listFavoriteController.getMyFavoriteMovies();
        if (movies != null) {
            for (Movie movie : movies) {
                Object[] rowData = {
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getDirector(),
                    movie.getDuration() + " phút",
                    movie.getReleaseDate().toString()
                };
                tableModel.addRow(rowData);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        history_Ticket = new javax.swing.JTable();
        BacktoDashBoard = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        history_Ticket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tiêu đề", "Chi tiết phim", "Tác giả", "Thời gian", "Ngày phát hành"
            }
        ));
        jScrollPane1.setViewportView(history_Ticket);

        BacktoDashBoard.setBackground(new java.awt.Color(255, 102, 0));
        BacktoDashBoard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BacktoDashBoard.setForeground(new java.awt.Color(255, 255, 255));
        BacktoDashBoard.setText("Back");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("DANH SÁCH YÊU THÍCH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(BacktoDashBoard))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BacktoDashBoard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BacktoDashBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BacktoDashBoardActionPerformed
        this.dispose();
    }//GEN-LAST:event_BacktoDashBoardActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> {
            new FavoriteForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BacktoDashBoard;
    private javax.swing.JTable history_Ticket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}