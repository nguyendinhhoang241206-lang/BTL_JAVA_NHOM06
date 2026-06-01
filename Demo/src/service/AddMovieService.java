package service;

import dao.MovieDAO;
import java.util.List;
import model.Movie;

public class AddMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean addMovie(Movie movie) {
        if (movie == null || movie.getId() == null || movie.getId().trim().isEmpty()) {
            return false; 
        }

        // 2. Lấy danh sách phim hiện tại từ DAO lên để đối chiếu
        List<Movie> currentMovies = movieDAO.findAll();
        
        // 3. Quét một vòng xem có ông nào trùng ID hoặc Tên không
        if (currentMovies != null) {
            for (Movie m : currentMovies) {
                // Kiểm tra trùng Mã phim (ID) - Bắt buộc
                if (m.getId().equalsIgnoreCase(movie.getId().trim())) {
                    return false; // Phát hiện trùng mã -> Trả về false ngay lập tức
                }
                
                // Kiểm tra trùng Tên phim (Tùy chọn, nếu nhóm ông bắt gắt thì để nguyên)
                if (m.getTitle().equalsIgnoreCase(movie.getTitle().trim())) {
                    return false; // Phát hiện trùng tên -> Trả về false
                }
            }
        }

        // 4. Qua hết các ải kiểm tra an toàn -> Lưu xuống DB
        movieDAO.add(movie); // Chú ý: Nếu Đạt đặt tên hàm bên DAO là addMovie() thì ông sửa chữ add thành addMovie nhé
        
        return true; // Thêm thành công
    }
    
}
