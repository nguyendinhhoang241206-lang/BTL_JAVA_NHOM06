package dao;

import model.Seat;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
    private List<Seat> seats = new ArrayList<>();
    private static final String FILE_PATH = "data/seats.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách ghế từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Seat.
    public List<Seat> readFromFile() {
        return null;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách ghế xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Seat> list) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Thêm một ghế mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Seat seat) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Tìm ghế theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Seat seat) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa ghế khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm ghế có id khớp với tham số truyền vào. Trả về đối tượng Seat hoặc null.
    public Seat findById(String id) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm và lọc ra tất cả các ghế thuộc về roomId được chỉ định. Trả về danh sách ghế của phòng đó.
    public List<Seat> findByRoomId(String roomId) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách ghế bằng cách gọi readFromFile().
    public List<Seat> findAll() {
        return null;
    }
}
