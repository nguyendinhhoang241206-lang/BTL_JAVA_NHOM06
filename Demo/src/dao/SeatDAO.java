package dao;

import model.Seat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Seat.Type;

public class SeatDAO {
    private List<Seat> seats = new ArrayList<>();
    private static final String FILE_PATH = "data/seats.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách ghế từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Seat.
    public List<Seat> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.seats = new ArrayList<>();
            return this.seats;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.seats = (List<Seat>) obj;
            } else {
                this.seats = new ArrayList<>();
            }
        } catch (Exception e) {
            this.seats = new ArrayList<>();
        }
        return this.seats;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách ghế xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Seat> list) {
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
            this.seats = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Sinh viên tự code logic: Thêm một ghế mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Seat seat) {
        if (seat == null) {
            return false;
        }
        readFromFile();
        this.seats.add(seat);
        return writeToFile(this.seats);
    }

    // TODO: Sinh viên tự code logic: Tìm ghế theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Seat seat) {
        if (seat == null || seat.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.seats.size(); i++) {
            if (this.seats.get(i).getId().equals(seat.getId())) {
                this.seats.set(i, seat);
                return writeToFile(this.seats);
            }
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa ghế khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.seats.size(); i++) {
            if (this.seats.get(i).getId().equals(id)) {
                this.seats.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.seats);
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm ghế có id khớp với tham số truyền vào. Trả về đối tượng Seat hoặc null.
    public Seat findById(String id) {
        if (id == null) {
            return null;
        }
        
        readFromFile();
        
        for (Seat seat : this.seats) {
            if (seat.getId().equals(id)) {
                return seat;
            }
        }
        return null;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm và lọc ra tất cả các ghế thuộc về roomId được chỉ định. Trả về danh sách ghế của phòng đó.
    public List<Seat> findByRoomId(String roomId) {
        if (roomId == null) {
            return new ArrayList<>();
        }
        readFromFile();
        List<Seat> result = new ArrayList<>();
        for (Seat seat : this.seats) {
            if (seat.getRoomId() != null && seat.getRoomId().equals(roomId)) {
                result.add(seat);
            }
        }
        return result;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách ghế bằng cách gọi readFromFile().
    public List<Seat> findAll() {
        return readFromFile();
    }
    
}
