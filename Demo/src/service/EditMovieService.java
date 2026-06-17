package service;

import dao.MovieDAO;
import java.util.List;
import model.Movie;

public class EditMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean editMovie(Movie movie) {
        if (movie == null || movie.getId() == null || movie.getId().trim().isEmpty()) {
            return false;
        }

        List<Movie> currentMovies = movieDAO.findAll();

        boolean isExist = false;
        if (currentMovies != null) {
            for (Movie m : currentMovies) {
                if (m.getId().equalsIgnoreCase(movie.getId().trim())) {
                    isExist = true;
                    break;
                }
            }
        }

        if (isExist) {
            movieDAO.update(movie);
            return true;
        }
        return false;
    }
}
