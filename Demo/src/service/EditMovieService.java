package service;

import dao.MovieDAO;
import model.Movie;

public class EditMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean editMovie(Movie movie) {
        if (movie == null || movie.getId() == null) {
            return false;
        }
        
        if (movieDAO.findById(movie.getId()) == null) {
            System.out.println("Lỗi: Không tìm thấy phim cần sửa!");
            return false;
        }
        
        return movieDAO.update(movie);
    }
}
