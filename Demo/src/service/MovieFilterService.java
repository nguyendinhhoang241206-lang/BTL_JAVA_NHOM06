package service;

import dao.MovieDAO;
import model.Movie;
import java.time.LocalDate;
import java.util.List;

public class MovieFilterService {
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Lấy danh sách toàn bộ phim bằng movieDAO.findAll(). Duyệt qua từng phim, phân tích chuỗi mô tả (description) hoặc so khớp từ khóa để tìm thể loại tương ứng. Trả về danh sách phim thỏa mãn.
    public List<Movie> filterByGenre(String genre) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Lấy danh sách toàn bộ phim bằng movieDAO.findAll(). Lọc ra các phim có ngày phát hành (releaseDate) trùng khớp với ngày được truyền vào. Trả về danh sách phim thỏa mãn.
    public List<Movie> filterByReleaseDate(LocalDate date) {
        return null;
    }
}
