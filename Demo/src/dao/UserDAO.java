package dao;

import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users = new ArrayList<>();
    private static final String FILE_PATH = "data/users.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách User từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách User.
    public List<User> readFromFile() {
        return null;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách User xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<User> list) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Thêm một User mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(User user) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Tìm User theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(User user) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa User khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm User có id khớp với tham số truyền vào. Trả về đối tượng User hoặc null.
    public User findById(String id) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm User có username khớp với tham số truyền vào. Trả về đối tượng User hoặc null.
    public User findByUsername(String username) {
        return null;
    }
}
