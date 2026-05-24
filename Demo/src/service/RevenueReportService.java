package service;

import dao.BookingDAO;
import dao.MovieDAO;
import dao.ShowTimeDAO;
import java.util.ArrayList;
import model.Booking;
import java.util.List;
import model.Movie;
import model.ShowTime;

public class RevenueReportService {
    private BookingDAO bookingDAO = new BookingDAO();

    // TODO: Sinh viên tự code logic: Gọi bookingDAO.findAll() để lấy toàn bộ danh sách đặt vé trong hệ thống. Lọc ra các hóa đơn Booking có trạng thái là SUCCESS. Duyệt qua danh sách đó và cộng dồn thuộc tính totalPrice để tính toán tổng doanh thu. Trả về tổng tiền doanh thu.
    public double calculateTotalRevenue() {
        List<Booking> bookings = bookingDAO.findAll();

        double totalRevenue = 0;

        for (Booking booking : bookings) {

            if (booking.getStatus() == Booking.Status.SUCCESS) {

                totalRevenue += booking.getTotalPrice();
            }
        }

        return totalRevenue;
    }
    
    public List<Object[]> getRevenueByMovie() {

        List<Object[]> result = new ArrayList<>();

        List<Booking> bookings = bookingDAO.findByUserId("U01");

        for (Booking booking : bookings) {

            // Chỉ tính vé SUCCESS
            if (booking.getStatus() != Booking.Status.SUCCESS) {
                continue;
            }

            ShowTimeDAO showTimeDAO = new ShowTimeDAO();
            MovieDAO movieDAO = new MovieDAO();

            ShowTime st = showTimeDAO.findById(booking.getShowTimeId());

            if (st == null) {
                continue;
            }

            Movie movie = movieDAO.findById(st.getMovieId());

            if (movie == null) {
                continue;
            }

            String movieTitle = movie.getTitle();

            int ticketCount = booking.getBookedSeatIds().size();

            double revenue = booking.getTotalPrice();

            Object[] row = new Object[] {
                movieTitle,
                ticketCount,
                revenue
            };

            result.add(row);
        }

        return result;
    }
}
