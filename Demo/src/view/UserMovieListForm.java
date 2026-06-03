package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class UserMovieListForm extends JFrame {

    // Các biến phục vụ hiển thị và phân trang
    private List<model.Movie> allMovies = new ArrayList<>();
    private JPanel gridPanel;
    private JPanel paginationPanel;
    private int currentPage = 1;
    private final int itemsPerPage = 6; // Số phim tối đa trên 1 trang

    public UserMovieListForm() {
        setTitle("Dashboard Khách Hàng - Danh sách phim");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(248, 249, 250));

        // 1. Thêm Sidebar
        add(createSidebar(), BorderLayout.WEST);

        // 2. Thêm Khu vực nội dung (Danh sách phim + Phân trang)
        add(createMainContent(), BorderLayout.CENTER);

        // 3. Tải dữ liệu từ DB và render
        loadMoviesFromDatabase();
    }

    // ==========================================
    // TẠO SIDEBAR (GIỮ NGUYÊN HOÀN TOÀN)
    // ==========================================
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(230, 230, 230)));

        JLabel lblLogo = new JLabel("HIT CINEMA");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 24));
        lblLogo.setForeground(new Color(255, 102, 0));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        model.User currentUser = utils.Session.getCurrentUser();
        String displayName = "Khách Hàng";
        String displayEmail = null;

        if (currentUser != null) {
            if (currentUser.getUsername() != null && !currentUser.getUsername().trim().isEmpty()) {
                displayName = currentUser.getUsername();
            }
            if (currentUser.getEmail() != null && !currentUser.getEmail().trim().isEmpty()) {
                displayEmail = currentUser.getEmail();
            }
        }

        JLabel lblAvatar = new JLabel(new ImageIcon("path_to_avatar.png"));
        lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblName = new JLabel(displayName);
        lblName.setFont(new Font("Arial", Font.BOLD, 18));
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnProfile = createMenuButton("Chỉnh sửa profile");
        JButton btnFavorite = createMenuButton("Danh sách yêu thích");
        JButton btnHistory = createMenuButton("Lịch sử đặt vé");
        JButton btnLogout = createMenuButton("Đăng xuất");
        btnLogout.setForeground(new Color(255, 102, 0));

        btnProfile.addActionListener(e -> {
            if (currentUser != null) {
                ProfileForm profileForm = new ProfileForm();
                profileForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                new controller.ProfileController(profileForm, currentUser);
                profileForm.setLocationRelativeTo(this);
                profileForm.setVisible(true);
            }
        });

        btnFavorite.addActionListener(e -> {
            FavoriteForm favForm = new FavoriteForm();
            favForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            favForm.setLocationRelativeTo(this);
            favForm.setVisible(true);
        });

        btnHistory.addActionListener(e -> {
            BookingHistoryFrame historyFrame = new BookingHistoryFrame();
            historyFrame.setLocationRelativeTo(this);
            historyFrame.setVisible(true);
        });

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                utils.Session.logout();
                this.dispose();
                LoginForm loginForm = new LoginForm();
                new controller.LoginController(loginForm);
                loginForm.setVisible(true);
            }
        });

        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(lblLogo);
        sidebar.add(Box.createVerticalStrut(40));
        sidebar.add(lblAvatar);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(lblName);

        if (displayEmail != null) {
            JLabel lblEmail = new JLabel(displayEmail);
            lblEmail.setForeground(Color.GRAY);
            lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
            sidebar.add(lblEmail);
        }

        sidebar.add(Box.createVerticalStrut(40));
        sidebar.add(btnProfile);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnFavorite);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnHistory);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);
        sidebar.add(Box.createVerticalStrut(30));

        return sidebar;
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ==========================================
    // TẠO NỘI DUNG CHÍNH (ĐÃ FIX LỖI GIÃN ẢNH)
    // ==========================================
    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Danh sách phim");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconPanel.setOpaque(false);
        iconPanel.add(new JLabel("🔍"));
        iconPanel.add(new JLabel("⏳"));

        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(iconPanel, BorderLayout.EAST);

        // Lưới thẻ phim
        gridPanel = new JPanel(new GridLayout(0, 3, 25, 25));
        gridPanel.setOpaque(false);

        // MẸO CỐT LÕI CHỐNG GIÃN KHUNG: Bọc gridPanel vào NORTH của một panel khác
        JPanel gridWrapper = new JPanel(new BorderLayout());
        gridWrapper.setOpaque(false);
        gridWrapper.add(gridPanel, BorderLayout.NORTH); // Giúp giữ đúng form của thẻ phim, ko bị kéo dài

        JScrollPane scrollPane = new JScrollPane(gridWrapper);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Khung phân trang
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        paginationPanel.setOpaque(false);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(paginationPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    // ==========================================
    // LOGIC LOAD DỮ LIỆU & PHÂN TRANG
    // ==========================================
    private void loadMoviesFromDatabase() {
        try {
            dao.MovieDAO movieDAO = new dao.MovieDAO();
            this.allMovies = movieDAO.findAll(); // Lấy tất cả phim
            this.currentPage = 1; // Khởi tạo về trang 1
            renderCurrentPage();  // Vẽ giao diện
        } catch (Exception e) {
            e.printStackTrace();
            gridPanel.add(new JLabel("Lỗi tải dữ liệu!"));
        }
    }

    private void renderCurrentPage() {
        gridPanel.removeAll();
        paginationPanel.removeAll();

        if (allMovies == null || allMovies.isEmpty()) {
            JLabel lblEmpty = new JLabel("Hiện tại chưa có phim nào trong hệ thống.");
            lblEmpty.setFont(new Font("Arial", Font.ITALIC, 16));
            gridPanel.add(lblEmpty);
        } else {
            // Tính toán tổng số trang
            int totalPages = (int) Math.ceil((double) allMovies.size() / itemsPerPage);

            // Xử lý logic cắt danh sách (List.subList)
            int startIndex = (currentPage - 1) * itemsPerPage;
            int endIndex = Math.min(startIndex + itemsPerPage, allMovies.size());

            // Vẽ các thẻ phim trong phạm vi trang hiện tại
            for (int i = startIndex; i < endIndex; i++) {
                model.Movie m = allMovies.get(i);
                gridPanel.add(createMovieCard(m.getId(), m.getTitle(), m.getDuration() + " phút", m.getDirector()));
            }

            // ==========================================
            // VẼ THANH PHÂN TRANG ĐỘNG
            // ==========================================
            // Nút "Lùi"
            JButton btnPrev = new JButton("<");
            btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnPrev.setEnabled(currentPage > 1);
            btnPrev.addActionListener(e -> {
                currentPage--;
                renderCurrentPage();
            });
            paginationPanel.add(btnPrev);

            // Các nút Số trang
            for (int i = 1; i <= totalPages; i++) {
                JButton btnPage = new JButton(String.valueOf(i));
                btnPage.setCursor(new Cursor(Cursor.HAND_CURSOR));

                if (i == currentPage) {
                    btnPage.setBackground(new Color(255, 102, 0)); // Màu cam cho trang đang chọn
                    btnPage.setForeground(Color.WHITE);
                } else {
                    btnPage.setBackground(Color.WHITE);
                    btnPage.setForeground(Color.BLACK);
                }

                int pageToGo = i;
                btnPage.addActionListener(e -> {
                    currentPage = pageToGo;
                    renderCurrentPage();
                });
                paginationPanel.add(btnPage);
            }

            // Nút "Tiến"
            JButton btnNext = new JButton(">");
            btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnNext.setEnabled(currentPage < totalPages);
            btnNext.addActionListener(e -> {
                currentPage++;
                renderCurrentPage();
            });
            paginationPanel.add(btnNext);
        }

        // Cập nhật lại giao diện ngay lập tức
        gridPanel.revalidate();
        gridPanel.repaint();
        paginationPanel.revalidate();
        paginationPanel.repaint();
    }

    // ==========================================
    // TẠO THẺ PHIM (GIỮ NGUYÊN)
    // ==========================================
    private JPanel createMovieCard(String movieId, String title, String time, String director) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

        // Khóa cứng chiều cao thẻ để tránh bị kéo giãn
        card.setPreferredSize(new Dimension(240, 350));
        card.setMinimumSize(new Dimension(240, 350));
        card.setMaximumSize(new Dimension(240, 350));

        JLayeredPane imageContainer = new JLayeredPane();
        imageContainer.setPreferredSize(new Dimension(240, 220));
        imageContainer.setMaximumSize(new Dimension(240, 220));

        JButton btnHeart = new JButton();
        ImageIcon heartIcon = new ImageIcon("image/heart.png");
        Image heartImg = heartIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        btnHeart.setIcon(new ImageIcon(heartImg));
        btnHeart.setBackground(new Color(178, 53, 228));
        btnHeart.setFocusPainted(false);
        btnHeart.setBorder(BorderFactory.createEmptyBorder());
        btnHeart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHeart.setBounds(195, 10, 32, 32);

        btnHeart.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đã thêm " + title + " vào danh sách yêu thích!");
        });

        // ẢNH GỐC
        ImageIcon originalIcon = new ImageIcon("image/img.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(240, 220, Image.SCALE_SMOOTH);
        JLabel lblImage = new JLabel(new ImageIcon(scaledImage));
        lblImage.setBounds(0, 0, 240, 220);

        // NÚT ĐẶT VÉ BỌC ẨN TRÊN ẢNH
        JButton btnBookTicket = new JButton();
        btnBookTicket.setOpaque(false);
        btnBookTicket.setContentAreaFilled(false);
        btnBookTicket.setBorderPainted(false);
        btnBookTicket.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBookTicket.setBounds(0, 0, 240, 220);

        btnBookTicket.addActionListener(e -> {
            new SelectShowTimeFrame(movieId).setVisible(true);
        });

        imageContainer.add(lblImage, Integer.valueOf(0));
        imageContainer.add(btnBookTicket, Integer.valueOf(1));
        imageContainer.add(btnHeart, Integer.valueOf(2));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblName = new JLabel(title);
        lblName.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblTime = new JLabel("⏱ " + time);
        lblTime.setForeground(Color.GRAY);

        JLabel lblDirector = new JLabel("👤 " + (director != null ? director : "Đang cập nhật"));
        lblDirector.setForeground(Color.GRAY);

        infoPanel.add(lblName);
        infoPanel.add(lblTime);
        infoPanel.add(lblDirector);

        card.add(imageContainer);
        card.add(infoPanel);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserMovieListForm().setVisible(true);
        });
    }
}