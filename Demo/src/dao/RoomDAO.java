package dao;

import model.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private List<Room> rooms = new ArrayList<>();
    private static final String FILE_PATH = "data/rooms.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách phòng từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Room.
    public List<Room> readFromFile() {
        return null;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách phòng xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Room> list) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Thêm một phòng mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Room room) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Tìm phòng theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Room room) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa phòng khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm phòng có id khớp với tham số truyền vào. Trả về đối tượng Room hoặc null.
    public Room findById(String id) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách phòng bằng cách gọi readFromFile().
    public List<Room> findAll() {
        return null;
    }
}
