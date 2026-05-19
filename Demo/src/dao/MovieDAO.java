package dao;

import model.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private List<Movie> movies = new ArrayList<>();
    private static final String FILE_PATH = "data/movies.dat";

    // TODO: Tự code logic: Đọc danh sách phim từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Movie.
    public List<Movie> readFromFile() {
        return null;
    }

    // TODO: Tự code logic: Ghi danh sách phim xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Movie> list) {
        return false;
    }

    // TODO: Tự code logic: Thêm một phim mới vào danh sách hiện tại và lưu xuống file. Trả về true nếu thành công.
    public boolean add(Movie movie) {
        return false;
    }

    // TODO: Tự code logic: Tìm phim theo id, cập nhật thông tin mới và lưu xuống file. Trả về true nếu thành công.
    public boolean update(Movie movie) {
        return false;
    }

    // TODO: Tự code logic: Xóa phim khỏi danh sách theo id và lưu xuống file. Trả về true nếu thành công.
    public boolean delete(String id) {
        return false;
    }

    // TODO: Tự code logic: Tìm kiếm và trả về đối tượng Movie theo id. Trả về null nếu không tìm thấy.
    public Movie findById(String id) {
        return null;
    }

    // TODO: Tự code logic: Trả về toàn bộ danh sách phim hiện tại bằng cách gọi readFromFile().
    public List<Movie> findAll() {
        return null;
    }
}
