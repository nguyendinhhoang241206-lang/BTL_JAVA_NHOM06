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
    private ShowTimeDAO showTimeDAO = new ShowTimeDAO();
    private MovieDAO movieDAO = new MovieDAO();

    // TODO: Sinh viên tự code logic: Gọi bookingDAO.findAll() để lấy toàn bộ danh sách đặt vé trong hệ thống. Lọc ra các hóa đơn Booking có trạng thái là SUCCESS. Duyệt qua danh sách đó và cộng dồn thuộc tính totalPrice để tính toán tổng doanh thu. Trả về tổng tiền doanh thu.
    public double calculateTotalRevenue() {
        List<Booking> allBookings = bookingDAO.findAll();
        double totalRevenue = 0.0;
        
        for (Booking b : allBookings) {
            if (b.getStatus() == Booking.Status.SUCCESS) {
                totalRevenue += b.getTotalPrice();
            }
        }
        return totalRevenue;
    }

    public double calculateRevenueByMovie(String movieId) {
        List<Booking> allBookings = bookingDAO.findAll();
        
        return 0.0; 
    }
    
    public List<Object[]> getRevenueReport() {

        List<Object[]> rows = new ArrayList<>();

        List<Movie> movies = movieDAO.findAll();

        List<Booking> bookings = bookingDAO.findAll();

        for (Movie movie : movies) {

            int soldTickets = 0;
            double revenue = 0;

            for (Booking booking : bookings) {

                if (booking.getStatus() == Booking.Status.SUCCESS) {

                    ShowTime showTime = showTimeDAO.findById(booking.getShowTimeId());

                    if (showTime != null && showTime.getMovieId().equals(movie.getId())) {
                        soldTickets++;
                        revenue += booking.getTotalPrice();
                    }
                }
            }

            rows.add(new Object[]{
                movie.getTitle(),
                soldTickets,
                revenue
            });
        }
        return rows;
    }
}
