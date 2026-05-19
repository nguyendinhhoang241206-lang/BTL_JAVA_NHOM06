package service;

import dao.MovieDAO;
import model.Movie;
import java.util.List;

public class MovieSearchService {
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Gọi movieDAO.findAll() để lấy danh sách phim, sau đó lọc và trả về danh sách các phim có tiêu đề (title) chứa từ khóa tìm kiếm (không phân biệt chữ hoa/chữ thường). Nếu từ khóa tìm kiếm trống hoặc null, trả về toàn bộ danh sách phim.
    public List<Movie> searchByTitle(String title) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Gọi movieDAO.findAll() để lấy toàn bộ danh sách phim trong hệ thống và trả về dữ liệu hiển thị cho View.
    public List<Movie> findAllMovies() {
        return null;
    }
}
