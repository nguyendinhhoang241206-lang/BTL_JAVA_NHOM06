package controller.movie;

import model.Movie;
import service.movie.AddMovieService;
import service.movie.DeleteMovieService;
import service.movie.EditMovieService;
import view.movie.AddEditMovieForm;
import view.booking.ShowlistmovieForm;

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
        loadTableData();
    }

    private void initController() {

        viewPanel.getBtnSearch().addActionListener(e -> {
            String keyword = viewPanel.getTxtSearch().getText().trim();
            searchTableData(keyword);
        });

        viewPanel.getTxtSearch().addActionListener(e -> {
            String keyword = viewPanel.getTxtSearch().getText().trim();
            searchTableData(keyword);
        });


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

    public void loadTableData() {
        searchTableData("");
    }


    public void searchTableData(String keyword) {
        DefaultTableModel model = (DefaultTableModel) viewPanel.getTableMovies().getModel();
        model.setRowCount(0);

        java.util.List<Movie> list = new dao.MovieDAO().findAll();
        if (list != null) {
            for (Movie m : list) {

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