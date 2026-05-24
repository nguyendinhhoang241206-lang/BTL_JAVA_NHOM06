package service;

import dao.MovieDAO;

public class DeleteMovieService {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean deleteMovie(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        
        if (movieDAO.findById(id) == null) {
            return false;
        }
        
        return movieDAO.delete(id);
    }
}
