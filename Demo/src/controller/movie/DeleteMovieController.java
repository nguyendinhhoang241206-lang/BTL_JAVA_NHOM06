package controller.movie;

import service.movie.DeleteMovieService;

public class DeleteMovieController {
    
    private DeleteMovieService deleteMovieService = new DeleteMovieService();

    public boolean handleDeleteMovie(String id) {

        return deleteMovieService.deleteMovie(id);
    }
}