package controller;

import model.Movie;
import service.MovieFilterService;
import java.time.LocalDate;
import java.util.List;

public class MovieFilterController {
    private MovieFilterService movieFilterService = new MovieFilterService();

    // TODO: Sinh viên tự code logic: Nhận sự kiện chọn thể loại (ví dụ: qua JComboBox), gọi movieFilterService.filterByGenre(genre) và trả kết quả hiển thị lên giao diện.
    public List<Movie> handleFilterByGenre(String genre) {
        return null;
    }

    // TODO: Sinh viên tự code logic: Nhận sự kiện chọn ngày chiếu từ View (ví dụ: thông qua JDatePicker), chuyển đổi kiểu dữ liệu phù hợp và gọi movieFilterService.filterByReleaseDate(date).
    public List<Movie> handleFilterByReleaseDate(LocalDate date) {
        return null;
    }
}
