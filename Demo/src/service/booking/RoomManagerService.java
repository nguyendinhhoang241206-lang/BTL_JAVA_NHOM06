package service.booking;

import dao.RoomDAO;
import model.Room;

import java.util.List;

public class RoomManagerService {
    private RoomDAO roomDAO = new RoomDAO();
    private SeatManagerService seatManagerService = new SeatManagerService();

    public boolean addRoom(Room room) {
        if (roomDAO.findById(room.getId()) != null) {
            return false;
        }

        boolean isRoomSaved = roomDAO.add(room);

        if (isRoomSaved) {
            return seatManagerService.generateSeatsForRoom(room.getId(), room.getTotalSeats());
        }

        return false;
    }

    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }
}