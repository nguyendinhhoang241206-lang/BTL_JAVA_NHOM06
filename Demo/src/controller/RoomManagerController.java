package controller;

import model.Room;
import service.RoomManagerService;

public class RoomManagerController {
    private RoomManagerService roomManagerService = new RoomManagerService();

    // TODO: Sinh viên tự code logic: Nhận thông tin phòng chiếu từ View khi người dùng nhấn nút thêm phòng (ví dụ: tên phòng, số lượng ghế), gọi roomManagerService.addRoom(room) để xử lý thêm phòng cùng tự sinh ghế, sau đó hiển thị thông báo kết quả.
    public boolean handleAddRoom(Room room) {
        return false;
    }
}
