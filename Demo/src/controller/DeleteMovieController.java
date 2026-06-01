package controller;

import service.DeleteMovieService;

public class DeleteMovieController {
    
    private DeleteMovieService deleteMovieService = new DeleteMovieService();

    // Hàm này sẽ được giao diện (View) gọi khi bấm nút Xóa
    public boolean handleDeleteMovie(String id) {
        // Ném ID sang cho tầng Service xử lý và trả về kết quả (true/false)
        return deleteMovieService.deleteMovie(id);
    }
}