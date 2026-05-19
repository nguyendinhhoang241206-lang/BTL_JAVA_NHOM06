package service;

import dao.SeatDAO;
import model.Seat;
import java.util.List;

public class SeatManagerService {
    private SeatDAO seatDAO = new SeatDAO();

    // TODO: Sinh viên tự code logic: Tự động phát sinh danh sách ghế cho phòng dựa trên roomId và totalSeats. Ví dụ: Phát sinh các hàng ghế A, B, C... gắn với số thứ tự (A1, A2... B1, B2...) với giá tiền tương ứng theo loại VIP/NORMAL/COUPLE. Sau đó gọi seatDAO.add() để lưu từng ghế.
    public boolean generateSeatsForRoom(String roomId, int totalSeats) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Gọi seatDAO.findByRoomId(roomId) để lấy danh sách ghế thuộc về phòng chiếu cụ thể.
    public List<Seat> getSeatsByRoom(String roomId) {
        return null;
    }
}
