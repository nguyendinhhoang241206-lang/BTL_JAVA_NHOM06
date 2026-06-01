package service;

import dao.MovieDAO;
import model.Movie;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieSearchService {
    
    private MovieDAO movieDAO = new MovieDAO();

    // Hàm lấy toàn bộ danh sách (Đã xóa dữ liệu fake)
    public List<Movie> findAllMovies() {
        // TẠM ẨN DÒNG NÀY ĐỂ CHỜ LÀM XONG FILE .DAT
        // return movieDAO.findAll(); 

        return null;
    }

    // Hàm tìm kiếm
    public List<Movie> searchByTitle(String title) {
        List<Movie> allMovies = findAllMovies(); 
        List<Movie> result = new ArrayList<>();
        
        if (allMovies != null) {
            for (Movie m : allMovies) {
                if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    result.add(m);
                }
            }
        }
        return result;
    }
}