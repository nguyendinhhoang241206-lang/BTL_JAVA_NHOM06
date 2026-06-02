package service;

import dao.MovieDAO;
import java.util.ArrayList;
import java.util.List;
import model.Movie;
import model.User;

public class ListFavoriteService {
    
    private MovieDAO movieDAO;

    public ListFavoriteService() {
        this.movieDAO = new MovieDAO();
    }

    // Nhận trực tiếp User từ Session truyền sang
    public List<Movie> getFavoriteMoviesByUser(User user) {
        List<Movie> favoriteMovies = new ArrayList<>();
        
        // Lấy thẳng danh sách ID từ đối tượng User đang lưu trong RAM (Session)
        if (user != null && user.getFavoriteMovieIds() != null) {
            List<String> movieIds = user.getFavoriteMovieIds();
            
            // Chỉ cần dùng MovieDAO xuống file movies.dat để lấy chi tiết phim
            for (String id : movieIds) {
                Movie movie = movieDAO.findById(id);
                if (movie != null) {
                    favoriteMovies.add(movie);
                }
            }
        }
        
        return favoriteMovies;
    }
}