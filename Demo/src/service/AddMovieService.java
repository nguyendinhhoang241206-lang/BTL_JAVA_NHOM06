package service;

import dao.MovieDAO;
import model.Movie;

public class AddMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Nhận đối tượng Movie, tiến hành kiểm tra trùng lặp ID hoặc tên phim nếu cần, sau đó gọi movieDAO.add(movie) để ghi dữ liệu. Trả về true nếu thêm thành công.
    public boolean addMovie(Movie movie) {
        return false;
    }
}
