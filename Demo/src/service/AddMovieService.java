package service;

import dao.MovieDAO;
import java.util.List;
import model.Movie;

public class AddMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean addMovie(Movie movie) {
        if (movie == null || movie.getId() == null || movie.getId().trim().isEmpty()) {
            return false; 
        }


        List<Movie> currentMovies = movieDAO.findAll();
        


        if (currentMovies != null) {
            for (Movie m : currentMovies) {

                if (m.getId().equalsIgnoreCase(movie.getId().trim())) {
                    return false;
                }
                

                if (m.getTitle().equalsIgnoreCase(movie.getTitle().trim())) {
                    return false;
                }
            }
        }

        movieDAO.add(movie);
        
        return true;
    }
    
}
