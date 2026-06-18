package service.booking;

import dao.ShowTimeDAO;
import model.ShowTime;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeService {
    private ShowTimeDAO showTimeDAO = new ShowTimeDAO();

    public List<ShowTime> getShowTimesByMovie(String movieId) {
        List<ShowTime> allShowTimes = showTimeDAO.findAll();
        List<ShowTime> result = new ArrayList<>();

        for (ShowTime st : allShowTimes) {
            if (st.getMovieId().equals(movieId)) {
                result.add(st);
            }
        }
        return result;
    }
}