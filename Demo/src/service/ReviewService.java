package service;

import dao.ReviewDAO;
import model.Review;
import java.util.List;

public class ReviewService {
    private ReviewDAO reviewDAO = new ReviewDAO();

    public boolean addReview(Review review) {
        if (review == null || review.getMovieId() == null || review.getUserId() == null) {
            return false;
        }

        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Vui lòng chọn mức đánh giá từ 1 đến 5 sao!");
        }

        if (review.getComment() != null && !review.getComment().trim().isEmpty()) {
            String commentLower = review.getComment().toLowerCase();
            String[] badWords = {"dm", "vl", "ngu", "fuck", "shit", "đm", "vcl"};
            
            for (String badWord : badWords) {
                if (commentLower.contains(badWord)) {
                    throw new IllegalArgumentException("Bình luận chứa từ ngữ không phù hợp. Vui lòng thử lại!");
                }
            }
        }

        if (review.getId() == null || review.getId().trim().isEmpty()) {
            review.setId("REV_" + System.currentTimeMillis());
        }
        return reviewDAO.add(review);
    }

    public List<Review> getReviewsByMovie(String movieId) {
        return reviewDAO.findByMovieId(movieId);
    }
}
