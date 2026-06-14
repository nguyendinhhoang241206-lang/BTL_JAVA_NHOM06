package service;

import dao.ReviewDAO;
import model.Review;
import java.util.List;

public class ReviewService {
    private ReviewDAO reviewDAO = new ReviewDAO();

    // TODO: Sinh viên tự code logic: Nhận đối tượng Review từ người dùng, validate xem rating có nằm trong khoảng 1-5 sao không, comment có chứa từ ngữ thô tục không. Nếu hợp lệ, gọi reviewDAO.add(review) để lưu lại. Trả về true nếu thành công.
    public boolean addReview(Review review) {
        // 1. Kiểm tra an toàn dữ liệu cơ bản
        if (review == null || review.getMovieId() == null || review.getUserId() == null) {
            return false;
        }

        // 2. Validate số sao (từ 1 đến 5)
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Vui lòng chọn mức đánh giá từ 1 đến 5 sao!");
        }

        // 3. Lọc từ ngữ thô tục trong bình luận (Comment có thể để trống, nhưng nếu có thì phải sạch)
        if (review.getComment() != null && !review.getComment().trim().isEmpty()) {
            String commentLower = review.getComment().toLowerCase();
            String[] badWords = {"dm", "vl", "ngu", "fuck", "shit", "đm", "vcl"};
            
            for (String badWord : badWords) {
                if (commentLower.contains(badWord)) {
                    throw new IllegalArgumentException("Bình luận chứa từ ngữ không phù hợp. Vui lòng thử lại!");
                }
            }
        }

        // 4. Tự động sinh ID cho bài đánh giá nếu chưa có (Dùng thời gian thực để đảm bảo không trùng)
        if (review.getId() == null || review.getId().trim().isEmpty()) {
            review.setId("REV_" + System.currentTimeMillis());
        }

        // 5. Vượt qua mọi bài test -> Gọi DAO lưu xuống file reviews.dat
        return reviewDAO.add(review);
    }

    // TODO: Sinh viên tự code logic: Gọi reviewDAO.findByMovieId(movieId) để lấy toàn bộ các đánh giá và nhận xét của phim cụ thể đó và trả về.
    public List<Review> getReviewsByMovie(String movieId) {
        return reviewDAO.findByMovieId(movieId);
    }
}
