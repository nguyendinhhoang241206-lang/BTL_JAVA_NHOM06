package dao;

import model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private List<Review> reviews = new ArrayList<>();
    private static final String FILE_PATH = "data/reviews.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách đánh giá từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Review.
    public List<Review> readFromFile() {
        return null;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách đánh giá xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Review> list) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Thêm một Review mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Review review) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Tìm Review theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Review review) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa Review khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm Review có id khớp với tham số truyền vào. Trả về đối tượng Review hoặc null.
    public Review findById(String id) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm và lọc ra tất cả các Review thuộc về movieId được chỉ định. Trả về danh sách đánh giá của phim đó.
    public List<Review> findByMovieId(String movieId) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách đánh giá bằng cách gọi readFromFile().
    public List<Review> findAll() {
        return null;
    }
}
