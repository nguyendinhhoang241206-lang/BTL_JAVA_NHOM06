package dao;

import model.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private List<Booking> bookings = new ArrayList<>();
    private static final String FILE_PATH = "data/bookings.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách đặt vé từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Booking.
    public List<Booking> readFromFile() {
        return null;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách đặt vé xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Booking> list) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Thêm một Booking mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Booking booking) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Tìm Booking theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Booking booking) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa Booking khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm Booking có id khớp với tham số truyền vào. Trả về đối tượng Booking hoặc null.
    public Booking findById(String id) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Lọc ra tất cả các Booking thuộc về userId được chỉ định. Trả về danh sách đặt vé của User đó.
    public List<Booking> findByUserId(String userId) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Lọc ra tất cả các Booking thuộc về showTimeId được chỉ định. Trả về danh sách đặt vé của suất chiếu đó.
    public List<Booking> findByShowTimeId(String showTimeId) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách đặt vé bằng cách gọi readFromFile().
    public List<Booking> findAll() {
        return null;
    }
}
