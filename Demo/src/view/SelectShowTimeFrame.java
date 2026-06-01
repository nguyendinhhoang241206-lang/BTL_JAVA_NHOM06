package view;

import controller.ShowTimeController;
import model.ShowTime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SelectShowTimeFrame extends JFrame {
    private ShowTimeController controller = new ShowTimeController();
    private String currentMovieId;
    private JPanel centerGridPanel;

    // Bảng màu thiết kế
    private final Color COLOR_BG = new Color(248, 249, 250); // Nền xám cực nhạt
    private final Color COLOR_PRIMARY = new Color(220, 53, 69); // Đỏ chủ đạo
    private final Color COLOR_TEXT_MUTED = new Color(108, 117, 125);
    private final Color COLOR_INFO_BG = new Color(231, 241, 255); // Xanh nhạt cho box Lưu ý
    private final Color COLOR_INFO_TEXT = new Color(10, 88, 202); // Xanh đậm cho chữ Lưu ý

    public SelectShowTimeFrame(String movieId) {
        this.currentMovieId = movieId;

        // 1. THIẾT LẬP CƠ BẢN
        setTitle("Chọn Suất Chiếu");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BG);

        // --- GỌI CÁC HÀM XÂY DỰNG GIAO DIỆN ---
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        // Load dữ liệu
        loadShowTimesToView();
    }

    // ==========================================
    // 1. TẠO HEADER (Tiêu đề & Nút Quay lại)
    // ==========================================
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)), // Viền mờ dưới header
                new EmptyBorder(15, 20, 15, 20)
        ));

        // Nút quay lại (Bo góc)
        RoundedButton btnBack = new RoundedButton("⬅ Quay lại", Color.WHITE, COLOR_PRIMARY);
        btnBack.setPreferredSize(new Dimension(110, 35));
        btnBack.setBorderColor(new Color(230, 230, 230));
        btnBack.addActionListener(e -> {
            this.dispose();
            // TODO: Trở về danh sách phim
        });

        // Tiêu đề giữa
        JPanel titleBox = new JPanel(new GridLayout(2, 1));
        titleBox.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("📅 CHỌN GIỜ CHIẾU", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(COLOR_PRIMARY);

        JLabel lblSub = new JLabel("Vui lòng chọn giờ chiếu phù hợp để tiếp tục đặt vé", SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(COLOR_TEXT_MUTED);

        titleBox.add(lblTitle);
        titleBox.add(lblSub);

        headerPanel.add(btnBack, BorderLayout.WEST);
        headerPanel.add(titleBox, BorderLayout.CENTER);

        // Khối rỗng cân bằng layout
        JLabel emptyLbl = new JLabel("");
        emptyLbl.setPreferredSize(new Dimension(110, 35));
        headerPanel.add(emptyLbl, BorderLayout.EAST);

        return headerPanel;
    }

    // ==========================================
    // 2. TẠO KHU VỰC CENTER (Lưới thẻ suất chiếu)
    // ==========================================
    private JPanel createCenterPanel() {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(COLOR_BG);

        // Lưới 3 cột
        centerGridPanel = new JPanel(new GridLayout(0, 3, 25, 25));
        centerGridPanel.setBackground(COLOR_BG);
        centerGridPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Bọc Grid vào một Panel phụ (NORTH) để các thẻ không bị kéo giãn vô cực theo chiều dọc
        JPanel flowWrapper = new JPanel(new BorderLayout());
        flowWrapper.setBackground(COLOR_BG);
        flowWrapper.add(centerGridPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(flowWrapper);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Lăn chuột mượt

        wrapperPanel.add(scrollPane, BorderLayout.CENTER);
        return wrapperPanel;
    }

    // ==========================================
    // 3. TẠO FOOTER (Box Lưu ý)
    // ==========================================
    private JPanel createFooterPanel() {
        JPanel footerWrapper = new JPanel(new BorderLayout());
        footerWrapper.setBackground(COLOR_BG);
        footerWrapper.setBorder(new EmptyBorder(0, 40, 20, 40));

        RoundedPanel infoBox = new RoundedPanel(15, COLOR_INFO_BG);
        infoBox.setLayout(new BorderLayout(15, 0));
        infoBox.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblIcon = new JLabel("ℹ️");
        lblIcon.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        JLabel lblInfoTitle = new JLabel("Lưu ý");
        lblInfoTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblInfoTitle.setForeground(COLOR_INFO_TEXT);

        JLabel lblInfoDesc = new JLabel("Vui lòng đến rạp trước giờ chiếu ít nhất 15 phút để làm thủ tục.");
        lblInfoDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblInfoDesc.setForeground(COLOR_INFO_TEXT);

        textPanel.add(lblInfoTitle);
        textPanel.add(lblInfoDesc);

        infoBox.add(lblIcon, BorderLayout.WEST);
        infoBox.add(textPanel, BorderLayout.CENTER);

        footerWrapper.add(infoBox, BorderLayout.CENTER);
        return footerWrapper;
    }

    // ==========================================
    // ĐỔ DỮ LIỆU & VẼ THẺ SUẤT CHIẾU
    // ==========================================
    private void loadShowTimesToView() {
        List<ShowTime> list = controller.loadShowTimesForView(currentMovieId);

        if (list.isEmpty()) {
            JLabel lblEmpty = new JLabel("Hiện chưa có suất chiếu nào cho bộ phim này.", SwingConstants.CENTER);
            lblEmpty.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            lblEmpty.setForeground(Color.GRAY);
            centerGridPanel.add(lblEmpty);
            return;
        }

        for (ShowTime st : list) {
            // HTML Format chữ trong thẻ
            String htmlText = "<html><div style='text-align: center; width: 100%; padding: 5px;'>" +
                    "<font color='#dc3545' size='5'>🕒</font><br>" +
                    "<b style='font-size: 26px; color: #212529;'>" + st.getStartTime() + "</b><br>" +
                    "<font color='#dc3545'><b>&mdash;&mdash;</b></font><br><br>" +
                    "<font color='#6c757d' size='3'>📅 Ngày: " + st.getShowDate() + "</font><br>" +
                    "<font color='#0d6efd' size='3'>🛋️ Phòng: " + st.getRoomId() + "</font>" +
                    "</div></html>";

            // Khởi tạo thẻ Custom
            ShowtimeCardButton btnCard = new ShowtimeCardButton(htmlText);

            // Xử lý sự kiện click
            btnCard.addActionListener(e -> {
                new SelectSeatFrame(st).setVisible(true);
                this.dispose();
            });

            centerGridPanel.add(btnCard);
        }
    }

    // ==========================================
    // CÁC COMPONENT CUSTOM (VẼ ĐỒ HỌA UI)
    // ==========================================

    // 1. Thẻ Suất Chiếu (Có hiệu ứng Hover hiện Tick đỏ)
    class ShowtimeCardButton extends JButton {
        private boolean isHovered = false;

        public ShowtimeCardButton(String text) {
            super(text);
            setPreferredSize(new Dimension(280, 150));
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Bắt sự kiện lướt chuột
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint(); // Báo Java vẽ lại nút
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Đổi màu nền & viền khi Hover
            if (isHovered) {
                g2.setColor(new Color(255, 240, 243)); // Hồng nhạt
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.setColor(COLOR_PRIMARY); // Viền đỏ
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

                // Vẽ vòng tròn Tick Đỏ (✓) ở góc trên bên phải
                g2.fillOval(getWidth() - 32, 12, 20, 20);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                g2.drawString("✓", getWidth() - 27, 27);
            } else {
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.setColor(new Color(230, 230, 230)); // Viền xám nhạt
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }

            super.paintComponent(g);
            g2.dispose();
        }
    }

    // 2. Class tạo Box viền bo tròn (Cho hộp Lưu ý)
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

    // 3. Class Nút bấm thường (Cho nút Back)
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
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

            super.paintComponent(g);
            g2.dispose();
        }
    }

    // Hàm Main Test nhanh giao diện
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SelectShowTimeFrame("M001").setVisible(true);
        });
    }
}