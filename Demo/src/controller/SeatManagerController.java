package controller;

import model.Seat;
import service.SeatManagerService;
import java.util.List;

public class SeatManagerController {
    private SeatManagerService seatManagerService = new SeatManagerService();

    // TODO: Sinh viên tự code logic: Nhận sự kiện từ View cần tải danh sách ghế của một phòng nhất định, gọi seatManagerService.getSeatsByRoom(roomId) để trả về danh sách ghế và cập nhật lên View (sơ đồ ghế).
    public List<Seat> handleGetSeatsByRoom(String roomId) {
        return null;
    }
}
