package controller;

import java.util.ArrayList;
import java.util.List;
import model.Movie;
import model.User;
import service.ListFavoriteService;
import utils.Session; // Đã sửa lại thành đúng class Session của bạn

public class ListFavoriteController {

    private ListFavoriteService listFavoriteService;

    public ListFavoriteController() {
        this.listFavoriteService = new ListFavoriteService();
    }

    public List<Movie> getMyFavoriteMovies() {
        // 1. Kiểm tra session
        if (!Session.isLoggedIn()) {
            System.out.println("Vui lòng đăng nhập!");
            return new ArrayList<>();
        }

        // 2. Lấy TOÀN BỘ đối tượng User đang đăng nhập từ Session
        User currentUser = Session.getCurrentUser();

        // 3. Truyền hẳn User đó cho Service xử lý
        return listFavoriteService.getFavoriteMoviesByUser(currentUser);
    }
}