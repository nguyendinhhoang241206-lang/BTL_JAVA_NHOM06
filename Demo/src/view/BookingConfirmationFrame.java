package view;

import controller.BookingController;
import model.Booking;
import model.ShowTime;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

public class BookingConfirmationFrame extends JFrame {
    private ShowTime currentShowTime;
    private List<String> selectedSeats;
    private BookingController bookingController = new BookingController();

    private double totalTicketPrice = 0.0;
    private double comboPrice = 0.0;
    private double discountValue = 0.0;
    private String selectedComboName = "Không mua";

    private JLabel lblTotalFinal;
    private JLabel lblTotalTicketDisplay;

    private final Color COLOR_BG = new Color(248, 249, 250);
    private final Color COLOR_CARD = Color.WHITE;
    private final Color COLOR_PRIMARY = new Color(220, 53, 69);
    private final Color COLOR_SUCCESS = new Color(40, 167, 69);
    private final Color COLOR_TEXT_DARK = new Color(33, 37, 41);
    private final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
    private final Color COLOR_RED_BG = new Color(255, 240, 243);

    public BookingConfirmationFrame(ShowTime showTime, List<String> selectedSeats) {
        this.currentShowTime = showTime;
        this.selectedSeats = selectedSeats;

        calculateTicketPrice();

        setTitle("Xác nhận đặt vé");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BG);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private void calculateTicketPrice() {
        totalTicketPrice = 0;
        for (String seatId : selectedSeats) {
            String seatCode = seatId.split("_")[1];
            char row = seatCode.charAt(0);
            if (row == 'A' || row == 'B') totalTicketPrice += 50000;
            else if (row == 'C' || row == 'D') totalTicketPrice += 70000;
            else if (row == 'E') totalTicketPrice += 100000;
        }
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_BG);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        RoundedButton btnBack = new RoundedButton("⬅ Quay lại", Color.WHITE, COLOR_PRIMARY);
        btnBack.setPreferredSize(new Dimension(110, 35));
        btnBack.setBorderColor(new Color(220, 220, 220));
        btnBack.addActionListener(e -> {
            new SelectSeatFrame(currentShowTime, selectedSeats).setVisible(true);
            this.dispose();
        });

        JPanel titleBox = new JPanel(new GridLayout(2, 1));
        titleBox.setBackground(COLOR_BG);
        JLabel lblTitle = new JLabel("🎟️ XÁC NHẬN ĐẶT VÉ", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(COLOR_PRIMARY);

        JLabel lblSub = new JLabel("Vui lòng kiểm tra thông tin và lựa chọn trước khi đặt vé", SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(COLOR_TEXT_MUTED);

        titleBox.add(lblTitle);
        titleBox.add(lblSub);

        headerPanel.add(btnBack, BorderLayout.WEST);
        headerPanel.add(titleBox, BorderLayout.CENTER);

        JLabel emptyLbl = new JLabel("");
        emptyLbl.setPreferredSize(new Dimension(110, 35));
        headerPanel.add(emptyLbl, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 25, 0));
        centerPanel.setBackground(COLOR_BG);
        centerPanel.setBorder(new EmptyBorder(10, 30, 20, 30));

        centerPanel.add(createLeftColumn());
        centerPanel.add(createRightColumn());

        return centerPanel;
    }

    private JPanel createLeftColumn() {
        RoundedPanel panel = new RoundedPanel(20, COLOR_CARD);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("📄 THÔNG TIN VÉ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_PRIMARY);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(20));

        String seatStr = String.join(", ", selectedSeats).replace(currentShowTime.getRoomId() + "_", "");

        panel.add(createInfoRow("🎬", "Phim (Mã)", currentShowTime.getMovieId(), COLOR_PRIMARY));
        panel.add(createInfoRow("🚪", "Phòng chiếu", currentShowTime.getRoomId(), COLOR_PRIMARY));
        panel.add(createInfoRow("🕒", "Giờ chiếu", currentShowTime.getStartTime() + " | " + currentShowTime.getShowDate(), COLOR_TEXT_DARK));
        panel.add(createInfoRow("💺", "Ghế đã chọn", seatStr + " (" + selectedSeats.size() + " ghế)", COLOR_PRIMARY));

        panel.add(Box.createVerticalGlue());
        RoundedPanel priceBox = new RoundedPanel(15, COLOR_RED_BG);
        priceBox.setLayout(new BorderLayout());
        priceBox.setBorder(new EmptyBorder(15, 15, 15, 15));
        priceBox.setMaximumSize(new Dimension(500, 60));

        JLabel lblPriceTitle = new JLabel("🏷️ Tiền vé");
        lblPriceTitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPriceTitle.setForeground(COLOR_TEXT_DARK);

        DecimalFormat df = new DecimalFormat("#,### VNĐ");
        lblTotalTicketDisplay = new JLabel(df.format(totalTicketPrice));
        lblTotalTicketDisplay.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalTicketDisplay.setForeground(COLOR_PRIMARY);

        priceBox.add(lblPriceTitle, BorderLayout.WEST);
        priceBox.add(lblTotalTicketDisplay, BorderLayout.EAST);
        panel.add(priceBox);

        return panel;
    }

    private JPanel createInfoRow(String icon, String label, String value, Color valueColor) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(COLOR_CARD);
        row.setMaximumSize(new Dimension(500, 45));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240))); // Gạch chân mờ

        JLabel lblLeft = new JLabel(icon + "   " + label);
        lblLeft.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblLeft.setForeground(COLOR_TEXT_DARK);

        JLabel lblRight = new JLabel(value);
        lblRight.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblRight.setForeground(valueColor);

        row.add(lblLeft, BorderLayout.WEST);
        row.add(lblRight, BorderLayout.EAST);
        return row;
    }

    private JPanel createRightColumn() {
        JPanel rightWrapper = new JPanel();
        rightWrapper.setLayout(new BoxLayout(rightWrapper, BoxLayout.Y_AXIS));
        rightWrapper.setBackground(COLOR_BG);

        RoundedPanel comboPanel = new RoundedPanel(20, COLOR_CARD);
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));
        comboPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblComboTitle = new JLabel("🍿 CHỌN COMBO BẮP NƯỚC");
        lblComboTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblComboTitle.setForeground(new Color(25, 60, 100)); // Xanh navy đậm
        comboPanel.add(lblComboTitle);
        comboPanel.add(Box.createVerticalStrut(15));

        ButtonGroup group = new ButtonGroup();

        String htmlNone = "<html><table width='340'><tr><td width='200'><b>Không mua</b><br><font color='#757575' size='3'>Không chọn combo</font></td></tr></table></html>";
        String htmlCb1 = "<html><table width='340'><tr><td width='200'><b>Combo 1 (1 Bắp + 1 Nước)</b><br><font color='#757575' size='3'>1 bắp ngọt + 1 nước</font></td><td align='right'><font color='#D32F2F'><b>50,000 VNĐ</b></font></td></tr></table></html>";
        String htmlCb2 = "<html><table width='340'><tr><td width='200'><b>Combo 2 (1 Bắp + 2 Nước)</b><br><font color='#757575' size='3'>1 bắp ngọt + 2 nước</font></td><td align='right'><font color='#D32F2F'><b>75,000 VNĐ</b></font></td></tr></table></html>";
        String htmlCb3 = "<html><table width='340'><tr><td width='200'><b>Combo 3 (2 Bắp + 2 Nước)</b><br><font color='#757575' size='3'>2 bắp ngọt + 2 nước</font></td><td align='right'><font color='#D32F2F'><b>95,000 VNĐ</b></font></td></tr></table></html>";

        JRadioButton rbNone = createStyledRadio(htmlNone);
        JRadioButton rbCb1 = createStyledRadio(htmlCb1);
        JRadioButton rbCb2 = createStyledRadio(htmlCb2);
        JRadioButton rbCb3 = createStyledRadio(htmlCb3);

        rbNone.setSelected(true);
        group.add(rbNone); group.add(rbCb1); group.add(rbCb2); group.add(rbCb3);

        java.awt.event.ActionListener comboAction = e -> {
            if (rbCb1.isSelected()) { comboPrice = 50000; selectedComboName = "Combo 1"; }
            else if (rbCb2.isSelected()) { comboPrice = 75000; selectedComboName = "Combo 2"; }
            else if (rbCb3.isSelected()) { comboPrice = 95000; selectedComboName = "Combo 3"; }
            else { comboPrice = 0; selectedComboName = "Không mua"; }
            updateFinalTotal();
        };

        rbNone.addActionListener(comboAction); rbCb1.addActionListener(comboAction);
        rbCb2.addActionListener(comboAction); rbCb3.addActionListener(comboAction);

        comboPanel.add(rbNone); comboPanel.add(Box.createVerticalStrut(10));
        comboPanel.add(rbCb1); comboPanel.add(Box.createVerticalStrut(10));
        comboPanel.add(rbCb2); comboPanel.add(Box.createVerticalStrut(10));
        comboPanel.add(rbCb3);

        rightWrapper.add(comboPanel);
        rightWrapper.add(Box.createVerticalStrut(20));

        RoundedPanel discountPanel = new RoundedPanel(20, COLOR_CARD);
        discountPanel.setLayout(new BoxLayout(discountPanel, BoxLayout.Y_AXIS));
        discountPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblDiscTitle = new JLabel("🎟️ MÃ GIẢM GIÁ (TÙY CHỌN)");
        lblDiscTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblDiscTitle.setForeground(new Color(25, 60, 100));

        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        inputRow.setBackground(COLOR_CARD);

        JTextField txtDisc = new JTextField(15);
        txtDisc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtDisc.setPreferredSize(new Dimension(200, 35));

        RoundedButton btnApply = new RoundedButton("Áp dụng", COLOR_PRIMARY, Color.WHITE);
        btnApply.setPreferredSize(new Dimension(100, 35));
        btnApply.addActionListener(e -> {
            if (txtDisc.getText().trim().equalsIgnoreCase("HITCLUB")) {
                discountValue = 20000;
                JOptionPane.showMessageDialog(this, "Áp dụng mã HITCLUB! Giảm 20.000 VNĐ.");
            } else {
                discountValue = 0;
                JOptionPane.showMessageDialog(this, "Mã không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            updateFinalTotal();
        });

        inputRow.add(txtDisc);
        inputRow.add(btnApply);

        JLabel lblDiscNote = new JLabel("* Mỗi mã chỉ sử dụng 1 lần cho mỗi giao dịch");
        lblDiscNote.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblDiscNote.setForeground(COLOR_TEXT_MUTED);

        discountPanel.add(lblDiscTitle);
        discountPanel.add(Box.createVerticalStrut(10));
        discountPanel.add(inputRow);
        discountPanel.add(Box.createVerticalStrut(5));
        discountPanel.add(lblDiscNote);

        rightWrapper.add(discountPanel);

        return rightWrapper;
    }

    private JRadioButton createStyledRadio(String htmlText) {
        JRadioButton rb = new JRadioButton(htmlText);
        rb.setBackground(COLOR_CARD);
        rb.setFocusPainted(false);
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        rb.setBorderPainted(true);
        return rb;
    }

    private JPanel createFooterPanel() {
        RoundedPanel footerPanel = new RoundedPanel(20, COLOR_CARD);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBorder(new EmptyBorder(15, 30, 15, 30));

        JPanel leftBox = new JPanel(new GridLayout(2, 1));
        leftBox.setBackground(COLOR_CARD);
        JLabel lblTitle = new JLabel("💳 TỔNG CỘNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(COLOR_TEXT_DARK);

        lblTotalFinal = new JLabel();
        lblTotalFinal.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTotalFinal.setForeground(COLOR_PRIMARY);
        leftBox.add(lblTitle); leftBox.add(lblTotalFinal);

        updateFinalTotal();
        JPanel rightBox = new JPanel(new BorderLayout());
        rightBox.setBackground(COLOR_CARD);

        RoundedButton btnConfirm = new RoundedButton("✅ CHỐT ĐẶT VÉ    >", COLOR_SUCCESS, Color.WHITE);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnConfirm.setPreferredSize(new Dimension(280, 50));
        btnConfirm.addActionListener(e -> handleConfirmBooking());

        JLabel lblSecurity = new JLabel("🔒 Thông tin của bạn được bảo mật tuyệt đối", SwingConstants.CENTER);
        lblSecurity.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSecurity.setForeground(COLOR_TEXT_MUTED);
        lblSecurity.setBorder(new EmptyBorder(5, 0, 0, 0));

        rightBox.add(btnConfirm, BorderLayout.CENTER);
        rightBox.add(lblSecurity, BorderLayout.SOUTH);

        footerPanel.add(leftBox, BorderLayout.WEST);
        footerPanel.add(rightBox, BorderLayout.EAST);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(COLOR_BG);
        wrapper.setBorder(new EmptyBorder(0, 30, 20, 30));
        wrapper.add(footerPanel, BorderLayout.CENTER);

        return wrapper;
    }

    private void updateFinalTotal() {
        double finalPrice = totalTicketPrice + comboPrice - discountValue;
        if (finalPrice < 0) finalPrice = 0;
        DecimalFormat df = new DecimalFormat("#,### VNĐ");
        lblTotalFinal.setText(df.format(finalPrice));
    }

    private void handleConfirmBooking() {
        if (!utils.Session.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Bạn cần đăng nhập để đặt vé!");
            return;
        }

        String currentUserId = utils.Session.getCurrentUser().getId();

        double finalPrice = totalTicketPrice + comboPrice - discountValue;
        if (finalPrice < 0) finalPrice = 0;

        Booking newBooking = new Booking();
        newBooking.setId("B" + System.currentTimeMillis());
        newBooking.setComboName(selectedComboName);
        newBooking.setDiscountAmount(discountValue);
        newBooking.setTotalPrice(finalPrice);

        newBooking.setUserId(currentUserId);

        newBooking.setShowTimeId(currentShowTime.getId());
        newBooking.setBookedSeatIds(selectedSeats);

        boolean isSuccess = bookingController.confirmBooking(newBooking);

        if (isSuccess) {
            JOptionPane.showMessageDialog(this,
                    "🎉 ĐẶT VÉ THÀNH CÔNG!\nMã đơn hàng: " + newBooking.getId() + "\nCảm ơn bạn đã sử dụng dịch vụ.",
                    "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi hệ thống khi lưu vé!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
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

        public void setBorderColor(Color color) {
            this.borderColor = color;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

            super.paintComponent(g);
            g2.dispose();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShowTime fakeShow = new ShowTime();
            fakeShow.setId("ST001");
            fakeShow.setMovieId("M001");
            fakeShow.setRoomId("R001");
            fakeShow.setShowDate(java.time.LocalDate.now());
            fakeShow.setStartTime(java.time.LocalTime.of(19, 30));
            new BookingConfirmationFrame(fakeShow, java.util.Arrays.asList("R001_A01", "R001_A02")).setVisible(true);
        });
    }
}