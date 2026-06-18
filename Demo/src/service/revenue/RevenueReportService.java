package service.revenue;

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
    
    public int calculateTotalTicketsSold() {
        List<Booking> bookings = bookingDAO.findAll();
        int totalTickets = 0;

        if (bookings != null) {
            for (Booking b : bookings) {
                if (b.getStatus() == Booking.Status.SUCCESS) {
                    totalTickets += b.getBookedSeatIds().size(); 
                }
            }
        }
        return totalTickets;
    }
    
    public String getTopSellingMovieTitle() {
        List<Object[]> reportData = getRevenueReport();
        
        if (reportData == null || reportData.isEmpty()) {
            return "Chưa có dữ liệu";
        }

        String topMovie = "Chưa có dữ liệu";
        int maxTickets = 0;

        for (Object[] row : reportData) {
            int tickets = (int) row[1]; 
            
            if (tickets > maxTickets) {
                maxTickets = tickets;
                topMovie = (String) row[0];
            }
        }
        return topMovie;
    }
}
