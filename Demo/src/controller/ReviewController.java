package controller;

import model.Review;
import service.ReviewService;
import java.util.List;
import javax.swing.JOptionPane;
import utils.Session;
import view.ReviewForm;

public class ReviewController {
    private ReviewService reviewService = new ReviewService();

    // TODO: Sinh viên tự code logic: Khi người dùng submit đánh giá (chọn số sao, nhập bình luận) trên View, nhận thông tin, gọi reviewService.addReview(review) và hiển thị thông báo kết quả.
    public void handleAddReview(String movieId, String ratingStr, String comment, ReviewForm view) {
        try {
            // 1. Kiểm tra dữ liệu phim
            if (movieId == null || movieId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Lỗi: Không có dữ liệu phim!", "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Kiểm tra phiên đăng nhập
            if (!Session.isLoggedIn()) {
                JOptionPane.showMessageDialog(view, "Vui lòng đăng nhập để đánh giá phim!", "Yêu cầu", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Kiểm tra rỗng bình luận
            if (comment == null || comment.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập nội dung đánh giá!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // 4. Bóc tách dữ liệu và đóng gói thành Model
                String userId = Session.getCurrentUser().getId();
                int rating = Integer.parseInt(ratingStr.substring(0, 1)); // Cắt "5 Sao..." lấy số 5
            
                Review newReview = new Review(null, rating, comment, userId, movieId);

                // 5. Gọi Service xử lý nghiệp vụ lưu file
                boolean isSuccess = reviewService.addReview(newReview);
            
                if (isSuccess) {
                    // 6. Thành công -> Ra lệnh cho View dọn dẹp form và tải lại bảng
                    view.clearInputs();
                    view.loadReviews();
                } else {
                    JOptionPane.showMessageDialog(view, "Lỗi hệ thống: Không thể lưu đánh giá lúc này.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi hệ thống: " + e.getMessage(), "Lỗi nghiêm trọng", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // TODO: Sinh viên tự code logic: Khi người dùng xem chi tiết một bộ phim ở View, gọi reviewService.getReviewsByMovie(movieId) để lấy toàn bộ các bình luận và hiển thị lên UI phần Đánh giá khách hàng.
    public List<Review> handleGetReviewsByMovie(String movieId) {
        return reviewService.getReviewsByMovie(movieId);
    }
}
