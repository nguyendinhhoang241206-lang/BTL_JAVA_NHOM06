package dao;

import model.Room;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private List<Room> rooms = new ArrayList<>();
    private static final String FILE_PATH = "data/rooms.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách phòng từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Room.
    public List<Room> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.rooms = new ArrayList<>();
            return this.rooms;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.rooms = (List<Room>) obj;
            } else {
                this.rooms = new ArrayList<>();
            }
        } catch (Exception e) {
            this.rooms = new ArrayList<>();
        }
        return this.rooms;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách phòng xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Room> list) {
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
            this.rooms = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Sinh viên tự code logic: Thêm một phòng mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Room room) {
        if (room == null) {
            return false;
        }
        readFromFile();
        this.rooms.add(room);
        return writeToFile(this.rooms);
    }

    // TODO: Sinh viên tự code logic: Tìm phòng theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Room room) {
        if (room == null || room.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).getId().equals(room.getId())) {
                this.rooms.set(i, room);
                return writeToFile(this.rooms);
            }
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa phòng khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).getId().equals(id)) {
                this.rooms.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.rooms);
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm phòng có id khớp với tham số truyền vào. Trả về đối tượng Room hoặc null.
    public Room findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (Room room : this.rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách phòng bằng cách gọi readFromFile().
    public List<Room> findAll() {
        return readFromFile();
    }
}
