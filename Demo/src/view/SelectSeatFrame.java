package view;

import controller.SeatController;
import model.ShowTime;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SelectSeatFrame extends JFrame {
    private ShowTime currentShowTime;
    private SeatController seatController = new SeatController();
    private List<String> selectedSeats = new ArrayList<>();

    private final Color COLOR_BG = new Color(26, 26, 26);         // Đen nhám
    private final Color COLOR_NORMAL = new Color(114, 9, 183);    // Tím (Ghế thường)
    private final Color COLOR_VIP = new Color(230, 57, 70);       // Đỏ (Ghế VIP)
    private final Color COLOR_COUPLE = new Color(247, 37, 133);   // Hồng (Ghế đôi)
    private final Color COLOR_BOOKED = new Color(73, 80, 87);     // Xám đậm (Đã đặt)
    private final Color COLOR_SELECTED = new Color(6, 214, 160);  // Xanh Cyan (Đang chọn)

    private JLabel lblTotalPrice;

    public SelectSeatFrame(ShowTime showTime) {
        this(showTime, new ArrayList<>());
    }

    public SelectSeatFrame(ShowTime showTime, List<String> previousSelectedSeats) {
        this.currentShowTime = showTime;
        if (previousSelectedSeats != null) {
            this.selectedSeats.addAll(previousSelectedSeats);
        }

        setTitle("Sơ đồ chọn ghế (Dark Mode)");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BG);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_BG);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JButton btnBack = new JButton("⬅ Quay lại");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(COLOR_BG);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> {
            new SelectShowTimeFrame(currentShowTime.getMovieId()).setVisible(true);
            this.dispose();
        });

        JLabel lblTitle = new JLabel("CHỌN GHẾ - PHÒNG " + currentShowTime.getRoomId(), SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        headerPanel.add(btnBack, BorderLayout.WEST);
        headerPanel.add(lblTitle, BorderLayout.CENTER);
        headerPanel.add(new JLabel("              "), BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(COLOR_BG);

        JPanel screenPanel = new JPanel(new BorderLayout());
        screenPanel.setBackground(COLOR_BG);
        screenPanel.setBorder(new EmptyBorder(10, 150, 20, 150)); // Thụt vào 2 bên

        JLabel lblScreen = new JLabel("MÀN HÌNH", SwingConstants.CENTER);
        lblScreen.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblScreen.setForeground(Color.LIGHT_GRAY);
        lblScreen.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));
        screenPanel.add(lblScreen, BorderLayout.CENTER);

        centerWrapper.add(screenPanel, BorderLayout.NORTH);

        JPanel seatPanel = new JPanel(new GridLayout(5, 10, 12, 12)); // Khoảng cách giữa các ghế là 12px
        seatPanel.setBackground(COLOR_BG);
        seatPanel.setBorder(new EmptyBorder(20, 60, 30, 60));

        List<String> bookedSeats = seatController.loadBookedSeats(currentShowTime.getId());

        char[] rows = {'A', 'B', 'C', 'D', 'E'};
        for (char row : rows) {
            for (int col = 1; col <= 10; col++) {
                String seatName = String.format("%c%02d", row, col);
                String fullSeatId = currentShowTime.getRoomId() + "_" + seatName;

                Color baseColor = COLOR_NORMAL;
                if (row == 'C' || row == 'D') baseColor = COLOR_VIP;
                else if (row == 'E') baseColor = COLOR_COUPLE;

                RoundedButton btnSeat = new RoundedButton(seatName, baseColor);

                if (bookedSeats.contains(fullSeatId)) {
                    btnSeat.setBgColor(COLOR_BOOKED);
                    btnSeat.setEnabled(false);
                } else {
                    if (this.selectedSeats.contains(fullSeatId)) {
                        btnSeat.setBgColor(COLOR_SELECTED);
                    }

                    Color finalBaseColor = baseColor;
                    btnSeat.addActionListener(e -> {
                        if (selectedSeats.contains(fullSeatId)) {
                            selectedSeats.remove(fullSeatId);
                            btnSeat.setBgColor(finalBaseColor);
                        } else {
                            selectedSeats.add(fullSeatId);
                            btnSeat.setBgColor(COLOR_SELECTED);
                        }
                        updateTotalPrice();
                    });
                }
                seatPanel.add(btnSeat);
            }
        }
        centerWrapper.add(seatPanel, BorderLayout.CENTER);
        add(centerWrapper, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(33, 33, 33));
        footerPanel.setBorder(new EmptyBorder(15, 30, 20, 30));

        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        legendPanel.setBackground(new Color(33, 33, 33));
        legendPanel.add(createLegendItem("Thường (50K)", COLOR_NORMAL));
        legendPanel.add(createLegendItem("VIP (70K)", COLOR_VIP));
        legendPanel.add(createLegendItem("Đôi (100K)", COLOR_COUPLE));
        legendPanel.add(createLegendItem("Đang chọn", COLOR_SELECTED));
        legendPanel.add(createLegendItem("Đã đặt", COLOR_BOOKED));
        footerPanel.add(legendPanel, BorderLayout.WEST);

        JPanel checkoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        checkoutPanel.setBackground(new Color(33, 33, 33));

        lblTotalPrice = new JLabel("Tổng: 0 VNĐ");
        lblTotalPrice.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTotalPrice.setForeground(Color.WHITE);

        updateTotalPrice();

        JButton btnContinue = new JButton("TIẾP TỤC");
        btnContinue.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnContinue.setBackground(COLOR_SELECTED);
        btnContinue.setForeground(Color.BLACK);
        btnContinue.setFocusPainted(false);
        btnContinue.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnContinue.addActionListener(e -> {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất 1 ghế để tiếp tục!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            new BookingConfirmationFrame(currentShowTime, selectedSeats).setVisible(true);
            this.dispose();
        });

        checkoutPanel.add(lblTotalPrice);
        checkoutPanel.add(btnContinue);
        footerPanel.add(checkoutPanel, BorderLayout.EAST);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createLegendItem(String text, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(new Color(33, 33, 33));
        JLabel colorBox = new JLabel("   ");
        colorBox.setOpaque(true);
        colorBox.setBackground(color);
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(colorBox);
        panel.add(lbl);
        return panel;
    }

    private void updateTotalPrice() {
        double total = 0;
        for (String seatId : selectedSeats) {
            String seatCode = seatId.split("_")[1];
            char row = seatCode.charAt(0);

            if (row == 'A' || row == 'B') total += 50000;
            else if (row == 'C' || row == 'D') total += 70000;
            else if (row == 'E') total += 100000;
        }
        DecimalFormat df = new DecimalFormat("#,### VNĐ");
        lblTotalPrice.setText("Tổng: " + df.format(total));
    }

    class RoundedButton extends JButton {
        private Color bgColor;

        public RoundedButton(String text, Color bgColor) {
            super(text);
            this.bgColor = bgColor;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 12));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        public void setBgColor(Color color) {
            this.bgColor = color;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            super.paintComponent(g);
            g2.dispose();
        }
    }
}