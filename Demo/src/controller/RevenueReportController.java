package controller;

import java.util.List;
import service.RevenueReportService;

public class RevenueReportController {
    private RevenueReportService revenueReportService = new RevenueReportService();

    // TODO: Sinh viên tự code logic: Khi Admin truy cập vào tab/màn hình "Thống kê doanh thu" ở View, gọi revenueReportService.calculateTotalRevenue() để lấy số liệu tổng doanh thu của rạp và đổ số liệu hiển thị lên nhãn (JLabel) hoặc vẽ biểu đồ tương ứng.
//    public double handleGetTotalRevenue() {
//        return revenueReportService.calculateTotalRevenue();
//    }
    
    public List<Object[]> handleGetRevenueByMovie() {
        return revenueReportService.getRevenueByMovie();
    }
}
