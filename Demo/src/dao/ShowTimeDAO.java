package dao;

import model.ShowTime;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeDAO {
    private List<ShowTime> showTimes = new ArrayList<>();
    private static final String FILE_PATH = "data/showtimes.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách lịch chiếu từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách ShowTime.
    public List<ShowTime> readFromFile() {
        return null;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách lịch chiếu xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<ShowTime> list) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Thêm một lịch chiếu mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(ShowTime showTime) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Tìm lịch chiếu theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(ShowTime showTime) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa lịch chiếu khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm lịch chiếu có id khớp với tham số truyền vào. Trả về đối tượng ShowTime hoặc null.
    public ShowTime findById(String id) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách lịch chiếu bằng cách gọi readFromFile().
    public List<ShowTime> findAll() {
        return null;
    }
}
