package dao;

import model.Movie;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import service.AddMovieService;

public class MovieDAO {
    private List<Movie> movies = new ArrayList<>();
    private static final String FILE_PATH = "data/movies.dat";

    // TODO: Tự code logic: Đọc danh sách phim từ file nhị phân FILE_PATH bằng ObjectInputStream. Trả về danh sách Movie.
    public List<Movie> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.movies = new ArrayList<>();
            return this.movies;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.movies = (List<Movie>) obj;
            } else {
                this.movies = new ArrayList<>();
            }
        } catch (Exception e) {
            this.movies = new ArrayList<>();
        }
        return this.movies;
    }

    // TODO: Tự code logic: Ghi danh sách phim xuống file nhị phân FILE_PATH bằng ObjectOutputStream. Trả về true nếu thành công, false nếu thất bại.
    public boolean writeToFile(List<Movie> list) {
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
            this.movies = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Tự code logic: Thêm một phim mới vào danh sách hiện tại và lưu xuống file. Trả về true nếu thành công.
    public boolean add(Movie movie) {
        if (movie == null) {
            return false;
        }
        readFromFile();
        this.movies.add(movie);
        return writeToFile(this.movies);
    }

    // TODO: Tự code logic: Tìm phim theo id, cập nhật thông tin mới và lưu xuống file. Trả về true nếu thành công.
    public boolean update(Movie movie) {
        if (movie == null || movie.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getId().equals(movie.getId())) {
                this.movies.set(i, movie);
                return writeToFile(this.movies);
            }
        }
        return false;
    }

    // TODO: Tự code logic: Xóa phim khỏi danh sách theo id và lưu xuống file. Trả về true nếu thành công.
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getId().equals(id)) {
                this.movies.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.movies);
        }
        return false;
    }

//    // TODO: Tự code logic: Tìm kiếm và trả về đối tượng Movie theo id. Trả về null nếu không tìm thấy.
//    public Movie findById(String id) {
//        if (id == null) {
//            return null;
//        }
//        readFromFile();
//        for (Movie movie : this.movies) {
//            if (movie.getId().equals(id)) {
//                return movie;
//            }
//        }
//        return null;
//    }

    // TODO: Tự code logic: Trả về toàn bộ danh sách phim hiện tại bằng cách gọi readFromFile().
    public List<Movie> findAll() {
        return readFromFile();
    }
    
    public List<Movie> getFakeMovies() {

        List<Movie> fakeList = new ArrayList<>();

        fakeList.add(new Movie(
            "M01",
            "Mắt Biếc",
            "Phim tình cảm buồn",
            "Victor Vũ",
            117,
            LocalDate.of(2019, 12, 20)
        ));

        fakeList.add(new Movie(
            "M02",
            "Bố Già",
            "Phim gia đình hài hước",
            "Trấn Thành",
            128,
            LocalDate.of(2021, 3, 12)
        ));

        fakeList.add(new Movie(
            "M03",
            "Lật Mặt 6",
            "Hành động kịch tính",
            "Lý Hải",
            132,
            LocalDate.of(2023, 4, 28)
        ));

        fakeList.add(new Movie(
            "M04",
            "Mai",
            "Phim tâm lý tình cảm",
            "Trấn Thành",
            131,
            LocalDate.of(2024, 2, 10)
        ));

        return fakeList;
    }
    
    public Movie findById(String id) {

        List<Movie> list = getFakeMovies();

        for (Movie m : list) {

            if (m.getId().equals(id)) {
                return m;
            }
        }

        return null;
    }
    
    
}
