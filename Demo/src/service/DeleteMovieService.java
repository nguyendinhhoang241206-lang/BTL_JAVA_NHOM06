package service;

import dao.MovieDAO;

public class DeleteMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Nhận ID phim cần xóa, kiểm tra xem phim có lịch chiếu (ShowTime) nào sắp tới không (để tránh lỗi toàn vẹn dữ liệu), nếu an toàn thì gọi movieDAO.delete(id). Trả về true nếu xóa thành công.
    public boolean deleteMovie(String id) {
        return false;
    }
}
