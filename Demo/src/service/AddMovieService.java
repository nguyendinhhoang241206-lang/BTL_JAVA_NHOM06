package service;

import dao.MovieDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Movie;

public class AddMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean addMovie(Movie movie) {
        if (movie == null || movie.getId() == null || movie.getId().trim().isEmpty()) {
            return false;
        }
        
        if (movieDAO.findById(movie.getId()) != null) {
            System.out.println("Lỗi: Mã phim đã tồn tại!");
            return false; 
        }
        
        return movieDAO.add(movie);
    }
    
}
