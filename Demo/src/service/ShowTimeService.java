package service;

import dao.ShowTimeDAO;
import model.ShowTime;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeService {
    private ShowTimeDAO showTimeDAO = new ShowTimeDAO();

    // Lọc suất chiếu theo mã phim
    public List<ShowTime> getShowTimesByMovie(String movieId) {
        List<ShowTime> allShowTimes = showTimeDAO.findAll(); // Gọi DAO của bạn
        List<ShowTime> result = new ArrayList<>();

        for (ShowTime st : allShowTimes) {
            // Lấy ra các suất chiếu khớp với movieId
            if (st.getMovieId().equals(movieId)) {
                result.add(st);
            }
        }
        return result;
    }
}