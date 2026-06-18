package controller.movie;

import model.Movie;
import service.movie.MovieSearchService;
import service.movie.AddMovieService;
import service.movie.EditMovieService;
import view.booking.ShowlistmovieForm;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieSearchController {
    
    private MovieSearchService movieSearchService;
    private ShowlistmovieForm view;

    public MovieSearchController(ShowlistmovieForm view, String role) {
        this.view = view;
        this.movieSearchService = new MovieSearchService();

        boolean isAdmin = "ADMIN".equalsIgnoreCase(role);
        
        this.view.getBtnAddMovie().setVisible(isAdmin);
        this.view.getEditMovie().setVisible(isAdmin);
        this.view.getDeleteMovie().setVisible(isAdmin);


        loadAllMoviesToTable();
        

        this.view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });


        this.view.getBtnAddMovie().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDetailForm(null); 
            }
        });


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


        this.view.getDeleteMovie().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = view.getTableMovies().getSelectedRow();
                if (selectedRow < 0) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn một bộ phim trên bảng để xóa!");
                    return;
                }
                

                String id = view.getTableMovies().getValueAt(selectedRow, 0).toString();
                String title = view.getTableMovies().getValueAt(selectedRow, 1).toString();
                

                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        view, 
                        "Bạn có chắc chắn muốn xóa phim '" + title + "' (Mã: " + id + ") không?", 
                        "Xác nhận Xóa", 
                        javax.swing.JOptionPane.YES_NO_OPTION,
                        javax.swing.JOptionPane.WARNING_MESSAGE
                );
                

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {

                    DeleteMovieController deleteCtrl = new DeleteMovieController();
                    boolean isSuccess = deleteCtrl.handleDeleteMovie(id);
                    
                    if (isSuccess) {
                        javax.swing.JOptionPane.showMessageDialog(view, "Xóa phim thành công!");

                    } else {
                        javax.swing.JOptionPane.showMessageDialog(view, "Lỗi: Không thể xóa phim!");
                    }
                }
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

    private void openDetailForm(Movie movie) {
        view.movie.AddEditMovieForm detailView = new view.movie.AddEditMovieForm();
        
        detailView.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

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

        detailView.setSize(600, 500); 
        detailView.setLocationRelativeTo(null); 
        detailView.setVisible(true);

        detailView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailView.dispose();
            }
        });
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
                        EditMovieService editService = new EditMovieService();
                        isSuccess = editService.editMovie(finalMovie);
                    } else {
                        AddMovieService addService = new AddMovieService();
                        isSuccess = addService.addMovie(finalMovie);
                    }

                    if (isSuccess) {
                        javax.swing.JOptionPane.showMessageDialog(detailView, isEditMode ? "Cập nhật phim thành công!" : "Thêm phim mới thành công!");
                        detailView.dispose();
                        loadAllMoviesToTable();
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