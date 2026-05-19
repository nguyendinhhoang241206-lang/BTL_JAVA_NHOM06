package service;

import dao.RoomDAO;
import model.Room;

public class RoomManagerService {
    private RoomDAO roomDAO = new RoomDAO();
    private SeatManagerService seatManagerService = new SeatManagerService();

    // TODO: Sinh viên tự code logic: Gọi roomDAO.add(room) để thêm phòng chiếu mới. Sau khi lưu phòng thành công, tự động gọi seatManagerService.generateSeatsForRoom(room.getId(), room.getTotalSeats()) để tự động sinh các ghế thuộc phòng đó. Trả về true nếu thành công.
    public boolean addRoom(Room room) {
        return false;
    }
}
