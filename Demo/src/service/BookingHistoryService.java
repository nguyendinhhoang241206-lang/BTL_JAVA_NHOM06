package service;

import dao.BookingDAO;
import dao.MovieDAO;
import dao.SeatDAO;
import dao.ShowTimeDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import model.Booking;
import java.util.List;
import model.Movie;
import model.Seat;
import model.ShowTime;

public class BookingHistoryService {
    private BookingDAO bookingDAO = new BookingDAO();
    private ShowTimeDAO showTimeDAO = new ShowTimeDAO();
    private MovieDAO movieDAO = new MovieDAO();
    private SeatDAO seatDAO = new SeatDAO();

    public List<Object[]> getHistoryByUserId(String userId) {
        List<Booking> listBookings = bookingDAO.findByUserId(userId);
        List<Object[]> resultList = new ArrayList<>();

        for (Booking b : listBookings) {
            String movieTitle = "N/A";
            String timeDetail = "N/A";
            String roomName = "N/A";
            
            if (b.getShowTimeId() != null) {
                ShowTime st = showTimeDAO.findById(b.getShowTimeId());
                if (st != null) {
                    timeDetail = st.getStartTime() + " - " + st.getShowDate();
                    roomName = st.getRoomId();
                    
                    Movie m = movieDAO.findById(st.getMovieId());
                    if (m != null) {
                        movieTitle = m.getTitle();
                    }
                }
            }

            StringBuilder seatBuilder = new StringBuilder();
            int ticketCount = 0;
            if (b.getBookedSeatIds() != null) {
                ticketCount = b.getBookedSeatIds().size();
                for (String seatId : b.getBookedSeatIds()) {
                    Seat seat = seatDAO.findById(seatId);
                    if (seat != null) {
                        seatBuilder.append(seat.getSeatName()).append(", ");
                    }
                }
            }
            String seatStr = seatBuilder.toString();
            if (seatStr.endsWith(", ")) {
                seatStr = seatStr.substring(0, seatStr.length() - 2);
            }

            Object[] row = new Object[] {
                b.getId(),
                movieTitle,
                timeDetail,
                roomName,
                seatStr,
                ticketCount,
                b.getTotalPrice(),
                b.getStatus(),
                b.getBookingDate()
            };
            
            resultList.add(row);
        }
        return resultList;
    }
}
