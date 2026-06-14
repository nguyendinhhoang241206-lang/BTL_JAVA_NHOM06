package controller;

import java.util.List;
import service.RevenueReportService;

public class RevenueReportController {
    private RevenueReportService revenueReportService = new RevenueReportService();

    // TODO: Sinh viên tự code logic: Khi Admin truy cập vào tab/màn hình "Thống kê doanh thu" ở View, gọi revenueReportService.calculateTotalRevenue() để lấy số liệu tổng doanh thu của rạp và đổ số liệu hiển thị lên nhãn (JLabel) hoặc vẽ biểu đồ tương ứng.
    public List<Object[]> handleGetTotalRevenue() {
        return revenueReportService.getRevenueReport();
    }
    
    public double getTotalRevenue() {
        return revenueReportService.calculateTotalRevenue();
    }
    
    // Hàm này View sẽ gọi. Controller chỉ gọi tiếp sang Service và ném kết quả về cho View.
    public int handleGetTotalTickets() {
        try {
            return revenueReportService.calculateTotalTicketsSold();
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Trả về 0 nếu có lỗi đọc file để View không bị crash
        }
    }
    
    public String handleGetTopSellingMovie() {
        try {
            return revenueReportService.getTopSellingMovieTitle();
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi hiển thị";
        }
    }
}
