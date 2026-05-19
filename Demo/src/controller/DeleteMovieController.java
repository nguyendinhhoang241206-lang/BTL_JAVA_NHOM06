package controller;

import service.DeleteMovieService;

public class DeleteMovieController {
    private DeleteMovieService deleteMovieService = new DeleteMovieService();

    // TODO: Sinh viên tự code logic: Nhận sự kiện yêu cầu xóa phim (ví dụ: người dùng nhấn nút Xóa trên bảng), hiển thị hộp thoại xác nhận (Confirm Dialog) tại View, nếu người dùng đồng ý thì gọi deleteMovieService.deleteMovie(id).
    public boolean handleDeleteMovie(String id) {
        return false;
    }
}
