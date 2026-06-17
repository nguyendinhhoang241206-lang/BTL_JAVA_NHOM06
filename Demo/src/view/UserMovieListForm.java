package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;

public class UserMovieListForm extends JFrame {

    private List<model.Movie> allMovies = new ArrayList<>();
    private JPanel gridPanel;
    private JPanel paginationPanel;
    private int currentPage = 1;
    private final int itemsPerPage = 6;

    public UserMovieListForm() {
        setTitle("Dashboard Khách Hàng - Danh sách phim");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(248, 249, 250));

        add(createSidebar(), BorderLayout.WEST);

        add(createMainContent(), BorderLayout.CENTER);

        loadMoviesFromDatabase();
    }
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

        JLabel lblAvatar = new JLabel();
        URL avatarUrl = getClass().getResource("/image/avatar.png");
        if (avatarUrl != null) {
            lblAvatar.setIcon(new ImageIcon(avatarUrl));
        } else {
            lblAvatar.setText("👤"); 
            lblAvatar.setFont(new Font("Arial", Font.PLAIN, 40));
        }
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

    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

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

        gridPanel = new JPanel(new GridLayout(0, 3, 25, 25));
        gridPanel.setOpaque(false);

        JPanel gridWrapper = new JPanel(new BorderLayout());
        gridWrapper.setOpaque(false);
        gridWrapper.add(gridPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(gridWrapper);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        paginationPanel.setOpaque(false);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(paginationPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private void loadMoviesFromDatabase() {
        try {
            dao.MovieDAO movieDAO = new dao.MovieDAO();
            this.allMovies = movieDAO.findAll();
            this.currentPage = 1;
            renderCurrentPage();
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
            int totalPages = (int) Math.ceil((double) allMovies.size() / itemsPerPage);
            int startIndex = (currentPage - 1) * itemsPerPage;
            int endIndex = Math.min(startIndex + itemsPerPage, allMovies.size());

            for (int i = startIndex; i < endIndex; i++) {
                model.Movie m = allMovies.get(i);
                gridPanel.add(createMovieCard(m.getId(), m.getTitle(), m.getDuration() + " phút", m.getDirector()));
            }

            JButton btnPrev = new JButton("<");
            btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnPrev.setEnabled(currentPage > 1);
            btnPrev.addActionListener(e -> {
                currentPage--;
                renderCurrentPage();
            });
            paginationPanel.add(btnPrev);

            for (int i = 1; i <= totalPages; i++) {
                JButton btnPage = new JButton(String.valueOf(i));
                btnPage.setCursor(new Cursor(Cursor.HAND_CURSOR));

                if (i == currentPage) {
                    btnPage.setBackground(new Color(255, 102, 0));
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

            JButton btnNext = new JButton(">");
            btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnNext.setEnabled(currentPage < totalPages);
            btnNext.addActionListener(e -> {
                currentPage++;
                renderCurrentPage();
            });
            paginationPanel.add(btnNext);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
        paginationPanel.revalidate();
        paginationPanel.repaint();
    }

    private JPanel createMovieCard(String movieId, String title, String time, String director) {
    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

    card.setPreferredSize(new Dimension(240, 350));
    card.setMinimumSize(new Dimension(240, 350));
    card.setMaximumSize(new Dimension(240, 350));

    JLayeredPane imageContainer = new JLayeredPane();
    imageContainer.setPreferredSize(new Dimension(240, 220));
    imageContainer.setMaximumSize(new Dimension(240, 220));

    JButton btnHeart = new JButton();
    URL heartUrl = getClass().getResource("/image/heart.png");

    if (heartUrl != null) {
        ImageIcon heartIcon = new ImageIcon(heartUrl);
        Image heartImg = heartIcon.getImage()
                .getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        btnHeart.setIcon(new ImageIcon(heartImg));
    } else {
        btnHeart.setText("❤");
        btnHeart.setForeground(Color.WHITE);
    }

    btnHeart.setFocusPainted(false);
    btnHeart.setBorder(BorderFactory.createEmptyBorder());
    btnHeart.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnHeart.setBounds(195, 10, 32, 32);

    Color colorNormal = new Color(178, 53, 228);
    Color colorFavorite = new Color(255, 102, 0);

    model.User currentUser = utils.Session.getCurrentUser();
    boolean isAlreadyFavorite = false;

    if (currentUser != null && currentUser.getFavoriteMovieIds() != null) {
        isAlreadyFavorite = currentUser.getFavoriteMovieIds().contains(movieId);
    }

    btnHeart.setBackground(isAlreadyFavorite ? colorFavorite : colorNormal);

    btnHeart.addActionListener(e -> {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng đăng nhập để thao tác!",
                    "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> favs = currentUser.getFavoriteMovieIds();

        if (favs == null) {
            favs = new ArrayList<>();
            currentUser.setFavoriteMovieIds(favs);
        } else if (!(favs instanceof ArrayList)) {
            favs = new ArrayList<>(favs);
            currentUser.setFavoriteMovieIds(favs);
        }

        if (favs.contains(movieId)) {
            favs.remove(movieId);
            btnHeart.setBackground(colorNormal);
            JOptionPane.showMessageDialog(
                    this,
                    "Đã XÓA phim khỏi danh sách yêu thích!");
        } else {
            favs.add(movieId);
            btnHeart.setBackground(colorFavorite);
            JOptionPane.showMessageDialog(
                    this,
                    "Đã THÊM phim vào danh sách yêu thích!");
        }

        dao.UserDAO userDAO = new dao.UserDAO();
        userDAO.update(currentUser);
    });

    JLabel lblImage = new JLabel();
    URL movieUrl = getClass().getResource("/image/img.png");

    if (movieUrl != null) {
        ImageIcon originalIcon = new ImageIcon(movieUrl);
        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(240, 220, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(scaledImage));
    } else {
        lblImage.setText("Không tải được ảnh");
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setOpaque(true);
        lblImage.setBackground(new Color(240, 240, 240));
    }

    lblImage.setBounds(0, 0, 240, 220);

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

    JPanel infoPanel = new JPanel(new GridLayout(4, 1, 0, 5));
    infoPanel.setBackground(Color.WHITE);
    infoPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

    JLabel lblName = new JLabel(title);
    lblName.setFont(new Font("Arial", Font.BOLD, 16));

    JLabel lblTime = new JLabel("⏱ " + time);
    lblTime.setForeground(Color.GRAY);

    JLabel lblDirector = new JLabel(
            "👤 " + (director != null ? director : "Đang cập nhật"));
    lblDirector.setForeground(Color.GRAY);

    JButton btnReview = new JButton("⭐ Đánh giá");
    btnReview.setBackground(new Color(255, 102, 0));
    btnReview.setForeground(Color.WHITE);
    btnReview.setFocusPainted(false);
    btnReview.setCursor(new Cursor(Cursor.HAND_CURSOR));

    btnReview.addActionListener(e -> {
        try {
            dao.MovieDAO movieDAO = new dao.MovieDAO();

            model.Movie movie = movieDAO.findById(movieId);

            if (movie != null) {
                ReviewForm reviewForm = new ReviewForm(movie);
                reviewForm.setLocationRelativeTo(this);
                reviewForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Không tìm thấy thông tin phim!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Có lỗi khi mở trang đánh giá!");
        }
    });

    infoPanel.add(lblName);
    infoPanel.add(lblTime);
    infoPanel.add(lblDirector);
    infoPanel.add(btnReview);

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