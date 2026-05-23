package service;

import dao.MovieDAO;
import model.Movie;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieSearchService {
    
    private MovieDAO movieDAO = new MovieDAO();

    // Hàm lấy toàn bộ danh sách (Đã bơm Fake Data)
    public List<Movie> findAllMovies() {
        // TẠM ẨN DÒNG NÀY ĐỂ CHỜ LÀM XONG FILE .DAT
        // return movieDAO.findAll(); 

        // TẠO DỮ LIỆU FAKE ĐỂ TEST GIAO DIỆN
        List<Movie> fakeList = new ArrayList<>();
        fakeList.add(new Movie("M01", "Mai", "Phim tâm lý tình cảm", "Trấn Thành", 131, LocalDate.of(2024, 2, 10)));
        fakeList.add(new Movie("M02", "Lật Mặt 7", "Phim gia đình cảm động", "Lý Hải", 138, LocalDate.of(2024, 4, 26)));
        fakeList.add(new Movie("M03", "Deadpool & Wolverine", "Hành động hài hước", "Shawn Levy", 127, LocalDate.of(2024, 7, 26)));
        fakeList.add(new Movie("M04", "Avenger: Endgame", "Siêu phẩm viễn tưởng", "Russo Brothers", 181, LocalDate.of(2019, 4, 26)));
        fakeList.add(new Movie("M05", "Dune: Part Two", "Hành động viễn tưởng", "Denis", 166, LocalDate.of(2024, 3, 1)));
        
        return fakeList;
    }

    // Hàm tìm kiếm (Lấy luôn cái Fake Data ở trên xuống để lọc)
    public List<Movie> searchByTitle(String title) {
        List<Movie> allMovies = findAllMovies(); 
        List<Movie> result = new ArrayList<>();
        
        if (allMovies != null) {
            for (Movie m : allMovies) {
                // Tìm kiếm không phân biệt chữ hoa chữ thường
                if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    result.add(m);
                }
            }
        }
        return result;
    }
}
