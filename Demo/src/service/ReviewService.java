package service;

import dao.ReviewDAO;
import model.Review;
import java.util.List;

public class ReviewService {
    private ReviewDAO reviewDAO = new ReviewDAO();

    // TODO: Sinh viên tự code logic: Nhận đối tượng Review từ người dùng, validate xem rating có nằm trong khoảng 1-5 sao không, comment có chứa từ ngữ thô tục không. Nếu hợp lệ, gọi reviewDAO.add(review) để lưu lại. Trả về true nếu thành công.
    public boolean addReview(Review review) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Gọi reviewDAO.findByMovieId(movieId) để lấy toàn bộ các đánh giá và nhận xét của phim cụ thể đó và trả về.
    public List<Review> getReviewsByMovie(String movieId) {
        return null;
    }
}
