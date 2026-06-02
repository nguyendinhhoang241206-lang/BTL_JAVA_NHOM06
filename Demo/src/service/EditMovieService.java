package service;

import dao.MovieDAO;
import java.util.List;
import model.Movie;

public class EditMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean editMovie(Movie movie) {
        // 1. Kiểm tra an toàn: Đối tượng null hoặc không có Mã phim thì chặn ngay
        if (movie == null || movie.getId() == null || movie.getId().trim().isEmpty()) {
            return false;
        }

        // 2. Lấy danh sách phim từ kho lên
        List<Movie> currentMovies = movieDAO.findAll();
        
        // 3. Quét xem cái mã phim (ID) này có thực sự tồn tại trong hệ thống không
        boolean isExist = false;
        if (currentMovies != null) {
            for (Movie m : currentMovies) {
                if (m.getId().equalsIgnoreCase(movie.getId().trim())) {
                    isExist = true;
                    break; // Tìm thấy rồi thì thoát vòng lặp luôn cho app nó chạy nhanh
                }
            }
        }

        // 4. Nếu tìm thấy phim trong kho -> Tiến hành gọi DAO để cập nhật
        if (isExist) {
            movieDAO.update(movie); // Nhớ ngó xem thằng Đạt nó đặt tên hàm bên DAO là update() hay updateMovie() nhé
            return true; // Báo cáo sửa thành công
        }

        // 5. Nếu quét hết list mà không thấy -> Báo lỗi
        return false;
    }
}
