package dao;

import model.Review;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private List<Review> reviews = new ArrayList<>();
    private static final String FILE_PATH = "data/reviews.dat";

    // TODO: Sinh viên tự code logic: Đọc danh sách đánh giá từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Review.
    public List<Review> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.reviews = new ArrayList<>();
            return this.reviews;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.reviews = (List<Review>) obj;
            } else {
                this.reviews = new ArrayList<>();
            }
        } catch (Exception e) {
            this.reviews = new ArrayList<>();
        }
        return this.reviews;
    }

    // TODO: Sinh viên tự code logic: Ghi danh sách đánh giá xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Review> list) {
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
            this.reviews = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Sinh viên tự code logic: Thêm một Review mới vào danh sách hiện tại, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean add(Review review) {
        if (review == null) {
            return false;
        }
        readFromFile();
        this.reviews.add(review);
        return writeToFile(this.reviews);
    }

    // TODO: Sinh viên tự code logic: Tìm Review theo id trong danh sách, cập nhật thông tin mới, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean update(Review review) {
        if (review == null || review.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.reviews.size(); i++) {
            if (this.reviews.get(i).getId().equals(review.getId())) {
                this.reviews.set(i, review);
                return writeToFile(this.reviews);
            }
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Xóa Review khỏi danh sách theo id, sau đó gọi writeToFile để lưu thay đổi. Trả về true nếu thành công.
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.reviews.size(); i++) {
            if (this.reviews.get(i).getId().equals(id)) {
                this.reviews.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.reviews);
        }
        return false;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm Review có id khớp với tham số truyền vào. Trả về đối tượng Review hoặc null.
    public Review findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (Review review : this.reviews) {
            if (review.getId().equals(id)) {
                return review;
            }
        }
        return null;
    }

    // TODO: Sinh viên tự code logic: Duyệt danh sách tìm và lọc ra tất cả các Review thuộc về movieId được chỉ định. Trả về danh sách đánh giá của phim đó.
    public List<Review> findByMovieId(String movieId) {
        if (movieId == null) {
            return new ArrayList<>();
        }
        readFromFile();
        List<Review> result = new ArrayList<>();
        for (Review review : this.reviews) {
            if (review.getMovieId() != null && review.getMovieId().equals(movieId)) {
                result.add(review);
            }
        }
        return result;
    }

    // TODO: Sinh viên tự code logic: Trả về toàn bộ danh sách đánh giá bằng cách gọi readFromFile().
    public List<Review> findAll() {
        return readFromFile();
    }
}
