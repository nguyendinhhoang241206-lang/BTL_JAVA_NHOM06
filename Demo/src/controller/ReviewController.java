package controller;

import model.Review;
import service.ReviewService;
import java.util.List;

public class ReviewController {
    private ReviewService reviewService = new ReviewService();

    // TODO: Sinh viên tự code logic: Khi người dùng submit đánh giá (chọn số sao, nhập bình luận) trên View, nhận thông tin, gọi reviewService.addReview(review) và hiển thị thông báo kết quả.
    public boolean handleAddReview(Review review) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Khi người dùng xem chi tiết một bộ phim ở View, gọi reviewService.getReviewsByMovie(movieId) để lấy toàn bộ các bình luận và hiển thị lên UI phần Đánh giá khách hàng.
    public List<Review> handleGetReviewsByMovie(String movieId) {
        return null;
    }
}
