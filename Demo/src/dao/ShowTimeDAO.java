package dao;

import model.ShowTime;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeDAO {
    private List<ShowTime> showTimes = new ArrayList<>();
    private static final String FILE_PATH = "data/showtimes.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách lịch chiếu từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách ShowTime.
    public List<ShowTime> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.showTimes = new ArrayList<>();
            return this.showTimes;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.showTimes = (List<ShowTime>) obj;
            } else {
                this.showTimes = new ArrayList<>();
            }
        } catch (Exception e) {
            this.showTimes = new ArrayList<>();
        }
        return this.showTimes;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách lịch chiếu xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<ShowTime> list) {
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
            this.showTimes = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Sinh viên tự code logic: Thêm một lịch chiếu mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(ShowTime showTime) {
        if (showTime == null) {
            return false;
        }
        readFromFile();
        this.showTimes.add(showTime);
        return writeToFile(this.showTimes);
    }

    // TODO: Sinh viên tự code logic: Tìm lịch chiếu theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(ShowTime showTime) {
        if (showTime == null || showTime.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.showTimes.size(); i++) {
            if (this.showTimes.get(i).getId().equals(showTime.getId())) {
                this.showTimes.set(i, showTime);
                return writeToFile(this.showTimes);
            }
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa lịch chiếu khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.showTimes.size(); i++) {
            if (this.showTimes.get(i).getId().equals(id)) {
                this.showTimes.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.showTimes);
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm lịch chiếu có id khớp với tham số truyền vào. Trả về đối tượng ShowTime hoặc null.
    public ShowTime findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (ShowTime showTime : this.showTimes) {
            if (showTime.getId().equals(id)) {
                return showTime;
            }
        }
        return null;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách lịch chiếu bằng cách gọi readFromFile().
    public List<ShowTime> findAll() {
        return readFromFile();
    }
}
