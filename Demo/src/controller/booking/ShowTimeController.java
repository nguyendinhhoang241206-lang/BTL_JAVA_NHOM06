package controller.booking;

import service.booking.ShowTimeService;
import model.ShowTime;
import java.util.List;

public class ShowTimeController {
    private ShowTimeService showTimeService = new ShowTimeService();

    public List<ShowTime> loadShowTimesForView(String movieId) {
        return showTimeService.getShowTimesByMovie(movieId);
    }
}