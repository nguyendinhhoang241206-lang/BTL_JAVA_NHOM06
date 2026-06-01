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
        List<Movie> testList = new java.util.ArrayList<>();
        testList.add(new Movie("M01", "Mai", "Phim rạp Trấn Thành", "Trấn Thành", 120, java.time.LocalDate.of(2024, 2, 10)));
        testList.add(new Movie("M02", "Lật Mặt 7", "Một điều ước", "Lý Hải", 135, java.time.LocalDate.of(2024, 4, 26)));
        testList.add(new Movie("M03", "Đào, Phở và Piano", "Phim lịch sử", "Phi Tiến Sơn", 100, java.time.LocalDate.of(2024, 2, 10)));
        
        return testList;

//        return null;
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