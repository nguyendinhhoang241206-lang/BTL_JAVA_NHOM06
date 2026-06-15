package controller;

import java.util.ArrayList;
import java.util.List;
import service.RevenueReportService;

public class RevenueReportController {
    private RevenueReportService revenueReportService = new RevenueReportService();

    // TODO: Sinh viên tự code logic: Khi Admin truy cập vào tab/màn hình "Thống kê doanh thu" ở View, gọi revenueReportService.calculateTotalRevenue() để lấy số liệu tổng doanh thu của rạp và đổ số liệu hiển thị lên nhãn (JLabel) hoặc vẽ biểu đồ tương ứng.
    public List<Object[]> handleGetTotalRevenue() {
        try {
            List<Object[]> report = revenueReportService.getRevenueReport();
            if (report == null) {
                return new ArrayList<>(); 
            }
            return report;
        } catch (Exception e) {
            System.err.println("Lỗi tải dữ liệu bảng doanh thu: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
    
    public double getTotalRevenue() {
        try {
            return revenueReportService.calculateTotalRevenue();
        } catch (Exception e) {
            System.err.println("Lỗi tính tổng doanh thu: " + e.getMessage());
            return 0.0; 
        }
    }
    
    // Hàm này View sẽ gọi. Controller chỉ gọi tiếp sang Service và ném kết quả về cho View.
    public int handleGetTotalTickets() {
        try {
            return revenueReportService.calculateTotalTicketsSold();
        } catch (Exception e) {
            System.err.println("Lỗi đếm tổng vé: " + e.getMessage());
            return 0; 
        }
    }
    
    public String handleGetTopSellingMovie() {
        try {
            String topMovie = revenueReportService.getTopSellingMovieTitle();
            return (topMovie != null && !topMovie.isEmpty()) ? topMovie : "Chưa có dữ liệu";
        } catch (Exception e) {
            System.err.println("Lỗi tìm phim bán chạy: " + e.getMessage());
            return "Lỗi hiển thị";
        }
    }
}
