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

        if (!Session.isLoggedIn()) {
            System.out.println("Vui lòng đăng nhập!");
            return new ArrayList<>();
        }


        User currentUser = Session.getCurrentUser();

        return listFavoriteService.getFavoriteMoviesByUser(currentUser);
    }
}