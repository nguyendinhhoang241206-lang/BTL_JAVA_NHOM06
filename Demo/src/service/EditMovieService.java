package service;

import dao.MovieDAO;
import model.Movie;

public class EditMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Nhận đối tượng Movie cần cập nhật, kiểm tra xem phim có tồn tại trong hệ thống không, sau đó gọi movieDAO.update(movie) để ghi đè dữ liệu mới. Trả về true nếu sửa thành công.
    public boolean editMovie(Movie movie) {
        return false;
    }
}
