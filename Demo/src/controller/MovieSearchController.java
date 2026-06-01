package controller;

import model.Movie;
import service.MovieSearchService;
import view.ShowlistmovieForm;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieSearchController {
    
    private MovieSearchService movieSearchService;
    private ShowlistmovieForm view;

    // CONSTRUCTOR: Nhận View và Role
    public MovieSearchController(ShowlistmovieForm view, String role) {
        this.view = view;
        this.movieSearchService = new MovieSearchService();
        
        // ---- XỬ LÝ PHÂN QUYỀN (HÌNH ẨN / HIỆN NÚT) ----
        boolean isAdmin = "ADMIN".equalsIgnoreCase(role);
        
        this.view.getBtnAddMovie().setVisible(isAdmin);
        this.view.getEditMovie().setVisible(isAdmin);
        this.view.getDeleteMovie().setVisible(isAdmin);
        // -----------------------------------------------

        // Tự động đổ dữ liệu khi khởi chạy
        loadAllMoviesToTable();
        
        // --- BẮT SỰ KIỆN NÚT SEARCH ---
        this.view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });

        // --- BẮT SỰ KIỆN NÚT THÊM PHIM ---
        this.view.getBtnAddMovie().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDetailForm(null); 
            }
        });

        // --- BẮT SỰ KIỆN NÚT SỬA PHIM ---
        this.view.getEditMovie().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTableMovies().getSelectedRow();
                
                if (selectedRow < 0) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn một bộ phim trên bảng để sửa!");
                    return;
                }
                
                String id = view.getTableMovies().getValueAt(selectedRow, 0).toString();
                String title = view.getTableMovies().getValueAt(selectedRow, 1).toString();
                String desc = view.getTableMovies().getValueAt(selectedRow, 2).toString();
                String director = view.getTableMovies().getValueAt(selectedRow, 3).toString();
                int duration = Integer.parseInt(view.getTableMovies().getValueAt(selectedRow, 4).toString());
                java.time.LocalDate releaseDate = java.time.LocalDate.parse(view.getTableMovies().getValueAt(selectedRow, 5).toString());
                
                Movie selectedMovie = new Movie(id, title, desc, director, duration, releaseDate);
                openDetailForm(selectedMovie);
            }
        });
    }

    private void loadAllMoviesToTable() {
        List<Movie> list = movieSearchService.findAllMovies();
        updateTable(list);
    }

    private void handleSearch() {
        String keyword = view.getTxtSearch().getText().trim();
        List<Movie> result;
        if (keyword.isEmpty()) {
            result = movieSearchService.findAllMovies();
        } else {
            result = movieSearchService.searchByTitle(keyword);
        }
        updateTable(result);
    }

    private void updateTable(List<Movie> list) {
        DefaultTableModel model = (DefaultTableModel) view.getTableMovies().getModel();
        model.setRowCount(0);
        if (list != null) {
            for (Movie m : list) {
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

    // Hàm mở form chi tiết (Dùng chung cho cả Thêm và Sửa)
    private void openDetailForm(Movie movie) {
        // Form của ông đang là JFrame rồi, xài thẳng luôn
        view.AddEditMovieForm detailView = new view.AddEditMovieForm();
        
        // Sửa lỗi tắt form phụ bị tắt luôn app chính
        detailView.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        
        // Cờ đánh dấu xem đang ở chế độ nào
        boolean isEditMode = (movie != null);
        
        if (!isEditMode) {
            detailView.setTitle("Thêm Phim Mới");
        } else {
            detailView.setTitle("Cập Nhật Phim");
            detailView.getTxtId().setText(movie.getId());
            detailView.getTxtId().setEditable(false); 
            
            detailView.getTxtTitle().setText(movie.getTitle());
            detailView.getTxtDescription().setText(movie.getDescription());
            detailView.getTxtDirector().setText(movie.getDirector());
            detailView.getTxtDuration().setText(String.valueOf(movie.getDuration()));
            detailView.getTxtReleaseDate().setText(movie.getReleaseDate().toString());
        }

        // Cài đặt kích thước và cho hiển thị lên giữa màn hình
        detailView.setSize(600, 500); 
        detailView.setLocationRelativeTo(null); 
        detailView.setVisible(true);

        // =================================================================
        // --- XỬ LÝ SỰ KIỆN CHO FORM CHI TIẾT (THÊM / SỬA) ---
        // =================================================================

        // 1. Nút Hủy: Đóng cửa sổ lại
        detailView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailView.dispose(); // Tắt chính cái form detailView này đi
            }
        });

        // 2. Nút Lưu: Gom dữ liệu, Validate và Gọi Service
        detailView.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = detailView.getTxtId().getText().trim();
                    String title = detailView.getTxtTitle().getText().trim();
                    String desc = detailView.getTxtDescription().getText().trim();
                    String director = detailView.getTxtDirector().getText().trim();
                    String durationStr = detailView.getTxtDuration().getText().trim();
                    String dateStr = detailView.getTxtReleaseDate().getText().trim();

                    if (id.isEmpty() || title.isEmpty() || durationStr.isEmpty() || dateStr.isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(detailView, "Vui lòng nhập đầy đủ các trường bắt buộc!");
                        return;
                    }

                    int duration = Integer.parseInt(durationStr);
                    java.time.LocalDate releaseDate = java.time.LocalDate.parse(dateStr);

                    Movie finalMovie = new Movie(id, title, desc, director, duration, releaseDate);

                    boolean isSuccess;
                    if (isEditMode) {
                        service.EditMovieService editService = new service.EditMovieService();
                        isSuccess = editService.editMovie(finalMovie);
                    } else {
                        service.AddMovieService addService = new service.AddMovieService();
                        isSuccess = addService.addMovie(finalMovie);
                    }

                    if (isSuccess) {
                        javax.swing.JOptionPane.showMessageDialog(detailView, isEditMode ? "Cập nhật phim thành công!" : "Thêm phim mới thành công!");
                        detailView.dispose(); // Tắt form đi sau khi lưu thành công
                        loadAllMoviesToTable(); // TẢI LẠI BẢNG
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(detailView, isEditMode ? "Lỗi: Không tìm thấy phim để sửa!" : "Lỗi: Mã phim (ID) này đã tồn tại!");
                    }

                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(detailView, "Thời lượng phải là một số nguyên (Ví dụ: 120)");
                } catch (java.time.format.DateTimeParseException ex) {
                    javax.swing.JOptionPane.showMessageDialog(detailView, "Ngày chiếu sai định dạng! Chuẩn phải là: YYYY-MM-DD (Ví dụ: 2024-12-01)");
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(detailView, "Đã xảy ra lỗi: " + ex.getMessage());
                }
            }
        });
    }
}