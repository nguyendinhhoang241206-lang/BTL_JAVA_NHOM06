package dao;

import model.Booking;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingDAO {
    private List<Booking> bookings = new ArrayList<>();
    private static final String FILE_PATH = "data/bookings.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách đặt vé từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Booking.
    public List<Booking> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.bookings = new ArrayList<>();
            return this.bookings;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.bookings = (List<Booking>) obj;
            } else {
                this.bookings = new ArrayList<>();
            }
        } catch (Exception e) {
            this.bookings = new ArrayList<>();
        }
        return this.bookings;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách đặt vé xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Booking> list) {
        if (list == null) {
            return false;
        }
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
            this.bookings = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Sinh viên tự code logic: Thêm một Booking mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Booking booking) {
        if (booking == null) {
            return false;
        }
        readFromFile();
        this.bookings.add(booking);
        return writeToFile(this.bookings);
    }

    // TODO: Sinh viên tự code logic: Tìm Booking theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Booking booking) {
        if (booking == null || booking.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.bookings.size(); i++) {
            if (this.bookings.get(i).getId().equals(booking.getId())) {
                this.bookings.set(i, booking);
                return writeToFile(this.bookings);
            }
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa Booking khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.bookings.size(); i++) {
            if (this.bookings.get(i).getId().equals(id)) {
                this.bookings.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.bookings);
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm Booking có id khớp với tham số truyền vào. Trả về đối tượng Booking hoặc null.
    public Booking findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (Booking booking : this.bookings) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }
        return null;
    }

    // TODO: Sinh viên tự code logic: Lọc ra tất cả các Booking thuộc về userId được chỉ định. Trả về danh sách đặt vé của User đó.
//    public List<Booking> findByUserId(String userId) {
//        if (userId == null) {
//            return new ArrayList<>();
//        }
//        readFromFile();
//        List<Booking> result = new ArrayList<>();
//        for (Booking booking : this.bookings) {
//            if (booking.getUserId() != null && booking.getUserId().equals(userId)) {
//                result.add(booking);
//            }
//        }
//        return result;
//    }

    // TODO: Sinh viên tự code logic: Lọc ra tất cả các Booking thuộc về showTimeId được chỉ định. Trả về danh sách đặt vé của suất chiếu đó.
    public List<Booking> findByShowTimeId(String showTimeId) {
        if (showTimeId == null) {
            return new ArrayList<>();
        }
        readFromFile();
        List<Booking> result = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if (booking.getShowTimeId() != null && booking.getShowTimeId().equals(showTimeId)) {
                result.add(booking);
            }
        }
        return result;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách đặt vé bằng cách gọi readFromFile().
    public List<Booking> findAll() {
        return readFromFile();
    }
    
    public List<Booking> findByUserId(String userId) {
        List<Booking> fakeBookings = new ArrayList<>();

        fakeBookings.add(new Booking(
            "BK1001",
            LocalDateTime.now().minusDays(2),
            "Combo Bắp Nước 1",
            20000.0,
            150000.0,
            Booking.Status.SUCCESS,
            userId,
            "ST01",
            Arrays.asList("S01", "S02")
        ));

        fakeBookings.add(new Booking(
            "BK1002",
            LocalDateTime.now().minusDays(5),
            "Không kèm combo",
            0.0,
            85000.0,
            Booking.Status.CANCELLED,
            userId,
            "ST02",
            Arrays.asList("S03")
        ));

        fakeBookings.add(new Booking(
            "BK1003",
            LocalDateTime.now().minusDays(3),
            "Combo nước",
            10000.0,
            120000.0,
            Booking.Status.SUCCESS,
            userId,
            "ST03",
            Arrays.asList("S04")
        ));

        fakeBookings.add(new Booking(
            "BK1004",
            LocalDateTime.now().minusDays(1),
            "Không combo",
            0.0,
            90000.0,
            Booking.Status.SUCCESS,
            userId,
            "ST04",
            Arrays.asList("S01")
        ));

        return fakeBookings;
    }
}
