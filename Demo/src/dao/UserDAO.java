package dao;

import model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users = new ArrayList<>();
    private static final String FILE_PATH = "data/users.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách User từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách User.
    public List<User> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.users = new ArrayList<>();
            return this.users;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.users = (List<User>) obj;
            } else {
                this.users = new ArrayList<>();
            }
        } catch (Exception e) {
            this.users = new ArrayList<>();
        }
        return this.users;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách User xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<User> list) {
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
            this.users = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Sinh viên tự code logic: Thêm một User mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(User user) {
        if (user == null) {
            return false;
        }
        readFromFile();
        this.users.add(user);
        return writeToFile(this.users);
    }

    // TODO: Sinh viên tự code logic: Tìm User theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId().equals(user.getId())) {
                this.users.set(i, user);
                return writeToFile(this.users);
            }
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa User khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId().equals(id)) {
                this.users.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.users);
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm User có id khớp với tham số truyền vào. Trả về đối tượng User hoặc null.
    public User findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (User user : this.users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm User có username khớp với tham số truyền vào. Trả về đối tượng User hoặc null.
    public User findByUsername(String username) {
        if (username == null) {
            return null;
        }
        readFromFile();
        for (User user : this.users) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
