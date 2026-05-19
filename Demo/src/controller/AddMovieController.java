package controller;

import model.Movie;
import service.AddMovieService;

public class AddMovieController {
    private AddMovieService addMovieService = new AddMovieService();

    // TODO: Sinh viên tự code logic: Nhận các thông tin phim từ View (Form thêm phim), validate các trường bắt buộc không được rỗng, sau đó gọi addMovieService.addMovie(movie) và phản hồi kết quả về UI.
    public boolean handleAddMovie(Movie movie) {
        return false;
    }
}
