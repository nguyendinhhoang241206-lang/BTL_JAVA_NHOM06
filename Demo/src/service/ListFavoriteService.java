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

    public List<Movie> getFavoriteMoviesByUser(User user) {
        List<Movie> favoriteMovies = new ArrayList<>();
        if (user != null && user.getFavoriteMovieIds() != null) {
            List<String> movieIds = user.getFavoriteMovieIds();
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