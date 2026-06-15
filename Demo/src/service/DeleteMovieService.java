package service;

import dao.MovieDAO;

public class DeleteMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Nhận ID phim cần xóa, kiểm tra...
    public boolean deleteMovie(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        // Gọi thẳng hàm delete của MovieDAO để xóa thật trong file dữ liệu
        return movieDAO.delete(id);
    }
}