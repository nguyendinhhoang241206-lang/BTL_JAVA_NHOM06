package controller;

import model.Movie;
import service.AddMovieService;
import service.DeleteMovieService;
import service.EditMovieService;
import view.AddEditMovieForm;
import view.ShowlistmovieForm;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MovieController {
    private ShowlistmovieForm viewPanel;
    private AddMovieService addService = new AddMovieService();
    private EditMovieService editService = new EditMovieService();
    private DeleteMovieService deleteService = new DeleteMovieService();

    public MovieController(ShowlistmovieForm viewPanel) {
        this.viewPanel = viewPanel;
        initController();
        loadTableData(); // Tải toàn bộ dữ liệu lên bảng khi vừa mở màn hình
    }

    private void initController() {
        // ==========================================
        // KHU VỰC: XỬ LÝ TÌM KIẾM PHIM (MỚI BỔ SUNG)
        // ==========================================

        // 1. Sự kiện khi click vào nút "Search"
        viewPanel.getBtnSearch().addActionListener(e -> {
            String keyword = viewPanel.getTxtSearch().getText().trim();
            searchTableData(keyword);
        });

        // 2. Sự kiện khi nhấn phím Enter ngay trong ô nhập tìm kiếm
        viewPanel.getTxtSearch().addActionListener(e -> {
            String keyword = viewPanel.getTxtSearch().getText().trim();
            searchTableData(keyword);
        });


        // ==========================================
        // KHU VỰC: CÁC TÍNH NĂNG KHÁC (GIỮ NGUYÊN)
        // ==========================================

        // Sự kiện: NÚT THÊM PHIM
        viewPanel.getBtnAddMovie().addActionListener(e -> {
            AddEditMovieForm form = new AddEditMovieForm();
            form.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            form.setVisible(true);

            form.getBtnSave().addActionListener(evt -> {
                try {
                    String id = form.getTxtId().getText().trim();
                    String title = form.getTxtTitle().getText().trim();
                    String description = form.getTxtDescription().getText().trim();
                    String director = form.getTxtDirector().getText().trim();
                    int duration = Integer.parseInt(form.getTxtDuration().getText().trim());
                    LocalDate releaseDate = LocalDate.parse(form.getTxtReleaseDate().getText().trim());

                    Movie newMovie = new Movie(id, title, description, director, duration, releaseDate);

                    if (addService.addMovie(newMovie)) {
                        JOptionPane.showMessageDialog(form, "Thêm phim thành công!");
                        form.dispose();
                        loadTableData();
                    } else {
                        JOptionPane.showMessageDialog(form, "Thêm thất bại (Trùng ID hoặc Tên)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(form, "Thời lượng phim phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(form, "Ngày chiếu phải đúng định dạng YYYY-MM-DD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
            form.getBtnCancel().addActionListener(evt -> form.dispose());
        });

        // Sự kiện: NÚT SỬA PHIM
        viewPanel.getEditMovie().addActionListener(e -> {
            int selectedRow = viewPanel.getTableMovies().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(viewPanel, "Vui lòng chọn một bộ phim để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            AddEditMovieForm form = new AddEditMovieForm();
            form.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            form.getTxtId().setText(viewPanel.getTableMovies().getValueAt(selectedRow, 0).toString());
            form.getTxtId().setEditable(false);
            form.getTxtTitle().setText(viewPanel.getTableMovies().getValueAt(selectedRow, 1).toString());
            form.getTxtDescription().setText(viewPanel.getTableMovies().getValueAt(selectedRow, 2) != null ? viewPanel.getTableMovies().getValueAt(selectedRow, 2).toString() : "");
            form.getTxtDirector().setText(viewPanel.getTableMovies().getValueAt(selectedRow, 3).toString());
            form.getTxtDuration().setText(viewPanel.getTableMovies().getValueAt(selectedRow, 4).toString());
            form.getTxtReleaseDate().setText(viewPanel.getTableMovies().getValueAt(viewPanel.getTableMovies().getSelectedRow(), 5).toString());
            form.setVisible(true);

            form.getBtnSave().addActionListener(evt -> {
                try {
                    String id = form.getTxtId().getText().trim();
                    String title = form.getTxtTitle().getText().trim();
                    String description = form.getTxtDescription().getText().trim();
                    String director = form.getTxtDirector().getText().trim();
                    int duration = Integer.parseInt(form.getTxtDuration().getText().trim());
                    LocalDate releaseDate = LocalDate.parse(form.getTxtReleaseDate().getText().trim());

                    Movie updatedMovie = new Movie(id, title, description, director, duration, releaseDate);

                    if (editService.editMovie(updatedMovie)) {
                        JOptionPane.showMessageDialog(form, "Cập nhật thành công!");
                        form.dispose();
                        loadTableData();
                    } else {
                        JOptionPane.showMessageDialog(form, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(form, "Thời lượng phim phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(form, "Ngày chiếu phải đúng định dạng YYYY-MM-DD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
            form.getBtnCancel().addActionListener(evt -> form.dispose());
        });

        // Sự kiện: NÚT XÓA PHIM
        viewPanel.getDeleteMovie().addActionListener(e -> {
            int selectedRow = viewPanel.getTableMovies().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(viewPanel, "Vui lòng chọn một bộ phim từ bảng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String idToDelete = viewPanel.getTableMovies().getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(viewPanel, "Bạn có chắc chắn muốn xóa phim " + idToDelete + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (deleteService.deleteMovie(idToDelete)) {
                    JOptionPane.showMessageDialog(viewPanel, "Xóa thành công!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(viewPanel, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Hàm load toàn bộ danh sách phim từ file lên bảng
    public void loadTableData() {
        searchTableData(""); // Gọi hàm tìm kiếm với từ khóa rỗng để hiển thị hết
    }

    // Hàm lọc dữ liệu theo từ khóa tìm kiếm (Không phân biệt chữ hoa, chữ thường)
    public void searchTableData(String keyword) {
        DefaultTableModel model = (DefaultTableModel) viewPanel.getTableMovies().getModel();
        model.setRowCount(0); // Xóa sạch bảng để hiển thị kết quả lọc mới

        java.util.List<Movie> list = new dao.MovieDAO().findAll(); // Đọc dữ liệu chuẩn từ file lên
        if (list != null) {
            for (Movie m : list) {
                // Kiểm tra: Nếu từ khóa trống, hoặc Tên phim chứa từ khóa, hoặc Mã phim chứa từ khóa
                if (keyword.isEmpty() ||
                        m.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        m.getId().toLowerCase().contains(keyword.toLowerCase())) {

                    model.addRow(new Object[]{
                            m.getId(),
                            m.getTitle(),
                            m.getDescription(),
                            m.getDirector(),
                            m.getDuration(),
                            m.getReleaseDate()
                    });
                }
            }
        }
    }
}