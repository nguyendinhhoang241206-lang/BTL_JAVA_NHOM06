package service;

import dao.RoomDAO;
import model.Room;
import java.util.List;

public class RoomManagerService {
    private RoomDAO roomDAO = new RoomDAO();
    private SeatManagerService seatManagerService = new SeatManagerService();

    // Hàm lưu phòng và kích hoạt sinh ghế (Đã điền logic cho TODO)
    public boolean addRoom(Room room) {
        // 1. Kiểm tra mã phòng đã tồn tại chưa để tránh trùng lặp
        if (roomDAO.findById(room.getId()) != null) {
            return false;
        }

        // 2. Lưu phòng vào file (gọi DAO)
        boolean isRoomSaved = roomDAO.add(room);

        // 3. Nếu lưu phòng thành công, tự động gọi Service khác để sinh ghế
        if (isRoomSaved) {
            return seatManagerService.generateSeatsForRoom(room.getId(), room.getTotalSeats());
        }

        return false;
    }

    // Hàm lấy danh sách phòng (Controller cần hàm này để hiển thị lên JTable)
    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }
}