package controller.review;

import java.util.ArrayList;
import model.Review;
import service.review.ReviewService;
import java.util.List;
import javax.swing.JOptionPane;
import utils.Session;
import view.review.ReviewForm;

public class ReviewController {
    private ReviewService reviewService = new ReviewService();

    public void handleAddReview(String movieId, String ratingStr, String comment, ReviewForm view) {
        try {
            if (movieId == null || movieId.trim().isEmpty()) {
                throw new NullPointerException("Lỗi: Không xác định được bộ phim đang đánh giá!");
            }
            if (!Session.isLoggedIn()) {
                throw new IllegalStateException("Vui lòng đăng nhập tài khoản trước khi viết đánh giá!");
            }
            if (comment == null || comment.trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng nhập nội dung bình luận!");
            }

            int rating = 0;
            try {
                rating = Integer.parseInt(ratingStr.substring(0, 1));
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                throw new NumberFormatException("Lỗi đọc định dạng sao. Vui lòng chọn lại mức đánh giá!");
            }

            String userId = Session.getCurrentUser().getId();
            Review newReview = new Review(null, rating, comment, userId, movieId);

            boolean isSuccess = reviewService.addReview(newReview);

            if (isSuccess) {
                JOptionPane.showMessageDialog(view, "Cảm ơn bạn đã gửi đánh giá!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                view.clearInputs(); 
                view.loadReviews(); 
            } else {
                throw new RuntimeException("Hệ thống không thể lưu file đánh giá lúc này.");
            }

        } catch (IllegalArgumentException | IllegalStateException | NullPointerException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi hệ thống: " + ex.getMessage(), "Lỗi nghiêm trọng", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public List<Review> handleGetReviewsByMovie(String movieId) {
        try {
            List<Review> reviews = reviewService.getReviewsByMovie(movieId);
            return (reviews != null) ? reviews : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi tải danh sách bình luận: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
