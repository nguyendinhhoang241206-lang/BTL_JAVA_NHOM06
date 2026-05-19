package controller;

import model.Movie;
import service.MovieSearchService;
import java.util.List;

public class MovieSearchController {
    private MovieSearchService movieSearchService = new MovieSearchService();

    // TODO: Sinh viên tự code logic: Nhận từ khóa tìm kiếm từ View khi người dùng gõ hoặc nhấn Enter, kiểm tra validation, gọi movieSearchService.searchByTitle(title) để tìm kiếm và trả về danh sách phim thỏa mãn lên giao diện.
    public List<Movie> handleSearchByTitle(String title) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Gọi movieSearchService.findAllMovies() để lấy danh sách toàn bộ phim và trả về hiển thị lên giao diện View khi khởi chạy.
    public List<Movie> handleShowAllMovies() {
        return null;
    }
}
