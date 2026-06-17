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
        try {
            if (!Session.isLoggedIn()) {
                System.err.println("Cảnh báo: Khách hàng chưa đăng nhập!");
                return new ArrayList<>();
            }

            User currentUser = Session.getCurrentUser();
            
            if (currentUser == null) {
                System.err.println("Lỗi: Phiên đăng nhập bị hỏng không lấy được thông tin User.");
                return new ArrayList<>();
            }

            List<Movie> favoriteMovies = listFavoriteService.getFavoriteMoviesByUser(currentUser);
            
            return (favoriteMovies != null) ? favoriteMovies : new ArrayList<>();
            
        } catch (Exception e) {
            System.err.println("Lỗi hệ thống khi tải danh sách phim yêu thích: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
}