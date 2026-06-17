package view;

import controller.BookingHistoryController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class BookingHistoryFrame extends JFrame {

    private BookingHistoryController historyController = new BookingHistoryController();

    private JPanel listPanel;

    private final Color COLOR_BG = new Color(248, 249, 250);
    private final Color COLOR_CARD = Color.WHITE;
    private final Color COLOR_PRIMARY = new Color(220, 53, 69); // Đỏ hủy vé
    private final Color COLOR_SUCCESS = new Color(40, 167, 69); // Xanh lá thành công
    private final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
    private final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);

    public BookingHistoryFrame() {
        setTitle("Lịch sử đặt vé");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BG);

        add(createHeaderPanel(), BorderLayout.NORTH);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(COLOR_BG);
        listPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
        loadBookingHistory();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
                new EmptyBorder(15, 20, 15, 20)
        ));

        RoundedButton btnBack = new RoundedButton("⬅ Quay lại", Color.WHITE, COLOR_PRIMARY);
        btnBack.setPreferredSize(new Dimension(110, 35));
        btnBack.setBorderColor(new Color(230, 230, 230));
        btnBack.addActionListener(e -> this.dispose());

        JLabel lblTitle = new JLabel("🕒 LỊCH SỬ ĐẶT VÉ", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(COLOR_TEXT_DARK);

        headerPanel.add(btnBack, BorderLayout.WEST);
        headerPanel.add(lblTitle, BorderLayout.CENTER);

        JLabel emptyLbl = new JLabel("");
        emptyLbl.setPreferredSize(new Dimension(110, 35));
        headerPanel.add(emptyLbl, BorderLayout.EAST);

        return headerPanel;
    }

    private void loadBookingHistory() {
        listPanel.removeAll();

        List<Object[]> myBookings = historyController.getMyHistory();

        if (myBookings == null || myBookings.isEmpty()) {
            JLabel lblEmpty = new JLabel("Bạn chưa có lịch sử đặt vé nào.");
            lblEmpty.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            lblEmpty.setForeground(COLOR_TEXT_MUTED);
            lblEmpty.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalStrut(50));
            listPanel.add(lblEmpty);
        } else {
            for (int i = myBookings.size() - 1; i >= 0; i--) {
                Object[] rowData = myBookings.get(i);
                listPanel.add(createTicketCard(rowData));
                listPanel.add(Box.createVerticalStrut(20)); // Khoảng cách
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private JPanel createTicketCard(Object[] rowData) {

        String bookingId = String.valueOf(rowData[0]);
        String movieTitle = String.valueOf(rowData[1]);
        String timeDetail = String.valueOf(rowData[2]);
        String roomName = String.valueOf(rowData[3]);
        String seatStr = String.valueOf(rowData[4]);
        double totalPrice = Double.parseDouble(String.valueOf(rowData[6]));
        String status = String.valueOf(rowData[7]);
        String bookingDate = String.valueOf(rowData[8]);

        RoundedPanel card = new RoundedPanel(20, COLOR_CARD);
        card.setLayout(new BorderLayout(15, 15));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.setMaximumSize(new Dimension(700, 180));

        JPanel infoBox = new JPanel();
        infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.Y_AXIS));
        infoBox.setBackground(COLOR_CARD);

        JLabel lblId = new JLabel("Mã đơn: " + bookingId + " - " + movieTitle);
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblId.setForeground(COLOR_TEXT_DARK);

        JLabel lblDetails = new JLabel("<html>" +
                "Thời gian: <b>" + timeDetail + "</b><br>" +
                "Phòng: <b>" + roomName + "</b> | Ghế: <b>" + seatStr + "</b><br>" +
                "<font color='#6c757d'>Ngày đặt: " + bookingDate + "</font>" +
                "</html>");
        lblDetails.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDetails.setBorder(new EmptyBorder(10, 0, 0, 0));

        infoBox.add(lblId);
        infoBox.add(lblDetails);

        JPanel actionBox = new JPanel(new BorderLayout());
        actionBox.setBackground(COLOR_CARD);
        actionBox.setPreferredSize(new Dimension(200, 100));

        DecimalFormat df = new DecimalFormat("#,### VNĐ");
        JLabel lblPrice = new JLabel(df.format(totalPrice), SwingConstants.RIGHT);
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPrice.setForeground(COLOR_PRIMARY);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        statusPanel.setBackground(COLOR_CARD);

        if (status.equalsIgnoreCase("SUCCESS")) {
            RoundedButton btnCancel = new RoundedButton("Hủy vé", Color.WHITE, COLOR_PRIMARY);
            btnCancel.setBorderColor(COLOR_PRIMARY);
            btnCancel.setPreferredSize(new Dimension(100, 35));

            btnCancel.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc chắn muốn hủy vé này không?",
                        "Xác nhận hủy", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = historyController.cancelBooking(bookingId);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Hủy vé thành công!");
                        loadBookingHistory();
                    } else {
                        JOptionPane.showMessageDialog(this, "Tính năng hủy vé đang bảo trì!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            statusPanel.add(btnCancel);
        } else {
            JLabel lblStatus = new JLabel(status.toUpperCase(), SwingConstants.CENTER);
            lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblStatus.setForeground(Color.WHITE);
            lblStatus.setOpaque(true);
            lblStatus.setBackground(COLOR_TEXT_MUTED);
            lblStatus.setPreferredSize(new Dimension(100, 35));
            statusPanel.add(lblStatus);
        }

        actionBox.add(lblPrice, BorderLayout.NORTH);
        actionBox.add(statusPanel, BorderLayout.SOUTH);

        card.add(infoBox, BorderLayout.CENTER);
        card.add(actionBox, BorderLayout.EAST);

        return card;
    }

    class RoundedPanel extends JPanel {
        private int cornerRadius;
        private Color bgColor;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            this.cornerRadius = radius;
            this.bgColor = bgColor;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
    }

    class RoundedButton extends JButton {
        private Color bgColor, textColor, borderColor;

        public RoundedButton(String text, Color bgColor, Color textColor) {
            super(text);
            this.bgColor = bgColor;
            this.textColor = textColor;
            this.borderColor = bgColor;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        public void setBorderColor(Color color) { this.borderColor = color; }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            super.paintComponent(g);
            g2.dispose();
        }
    }
}