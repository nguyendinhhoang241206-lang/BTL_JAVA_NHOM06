package service;

import dao.SeatDAO;
import model.Seat;
import java.util.List;

public class SeatService {
    private SeatDAO seatDAO = new SeatDAO();

    public List<Seat> getSeatsByRoomId(String roomId) {
        return seatDAO.findByRoomId(roomId);
    }
}