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

    // CONSTRUCTOR MỚI: Nhận thêm biến vai trò (role) từ hệ thống đăng nhập
    public MovieSearchController(ShowlistmovieForm view, String role) {
        this.view = view;
        this.movieSearchService = new MovieSearchService();
        
        // ---- XỬ LÝ PHÂN QUYỀN (HÌNH ẨN / HIỆN NÚT) ----
        // Kiểm tra xem role truyền vào có phải là ADMIN không (không phân biệt chữ hoa/thường)
        boolean isAdmin = "ADMIN".equalsIgnoreCase(role);
        
        // Dùng hàm setVisible(true/false) để ẩn/hiện các nút chức năng quản lý
        this.view.getBtnAddMovie().setVisible(isAdmin);
        this.view.getEditMovie().setVisible(isAdmin);
        this.view.getDeleteMovie().setVisible(isAdmin);
        // -----------------------------------------------

        // Tự động đổ dữ liệu và lắng nghe nút tìm kiếm như cũ
        loadAllMoviesToTable();
        
        this.view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
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
}