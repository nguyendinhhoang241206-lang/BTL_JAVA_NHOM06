package utils;

import dao.*;
import model.*;
import model.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;

public class FakeData {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        MovieDAO movieDAO = new MovieDAO();
        RoomDAO roomDAO = new RoomDAO();
        SeatDAO seatDAO = new SeatDAO();
        ShowTimeDAO showTimeDAO = new ShowTimeDAO();
        BookingDAO bookingDAO = new BookingDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        System.out.println("⏳ Đang kiểm tra trạng thái dữ liệu...");

        if (!userDAO.readFromFile().isEmpty()) {
            System.out.println("✅ Dữ liệu mẫu (Fake Data) đã tồn tại sẵn trong hệ thống!");
            System.out.println("⛔ Bỏ qua bước tạo mới để tránh trùng lặp dữ liệu.");
            System.out.println("Bạn có thể bật form Login lên để test luồng Đặt vé và Lịch sử.");
            return;
        }

        System.out.println("⏳ Chưa có dữ liệu. Đang tiến hành tạo dữ liệu giả (Fake Data)...");

        Movie m1 = new Movie("M01", "Hành Trình Cô Độc", "Một bộ phim phiêu lưu đầy cảm xúc.", "Nguyễn Hoàng Anh", 132, LocalDate.of(2026, 5, 20));
        Movie m2 = new Movie("M02", "Avenger: Hồi Kết", "Siêu anh hùng tập hợp bảo vệ trái đất.", "Anh em Russo", 181, LocalDate.of(2025, 4, 25));
        Movie m3 = new Movie("M03", "Lật Mặt 7", "Câu chuyện gia đình cảm động của điện ảnh Việt.", "Lý Hải", 120, LocalDate.of(2026, 4, 30));

        movieDAO.add(m1);
        movieDAO.add(m2);
        movieDAO.add(m3);

        Room r1 = new Room("R01", "Phòng chiếu 1 (2D)", 50);
        Room r2 = new Room("R02", "Phòng chiếu 2 (IMAX)", 80);

        roomDAO.add(r1);
        roomDAO.add(r2);

        Seat s1 = new Seat("R01_A01", "A01", Seat.Type.NORMAL, 50000, "R01");
        Seat s2 = new Seat("R01_A02", "A02", Seat.Type.NORMAL, 50000, "R01");
        Seat s3 = new Seat("R01_C01", "C01", Seat.Type.VIP, 70000, "R01");
        Seat s4 = new Seat("R01_E01", "E01", Seat.Type.COUPLE, 100000, "R01");

        seatDAO.add(s1); seatDAO.add(s2); seatDAO.add(s3); seatDAO.add(s4);

        ShowTime st1 = new ShowTime("ST01", LocalDate.now().plusDays(1), LocalTime.of(19, 30), LocalTime.of(21, 42), "M01", "R01");
        ShowTime st2 = new ShowTime("ST02", LocalDate.now().plusDays(1), LocalTime.of(20, 15), LocalTime.of(23, 16), "M02", "R02");
        ShowTime st3 = new ShowTime("ST03", LocalDate.now().plusDays(2), LocalTime.of(14, 0), LocalTime.of(16, 0), "M03", "R01");

        showTimeDAO.add(st1);
        showTimeDAO.add(st2);
        showTimeDAO.add(st3);

        User admin = new User(
                "U01", "admin", "123456", Role.ADMIN, UserStatus.ACTIVE,
                "admin@gmail.com", "0987654321", Gender.MALE,
                LocalDate.of(2000, 1, 1), Arrays.asList("M01", "M02")
        );

        User normalUser = new User(
                "U02", "datnguyen", "password123", Role.USER, UserStatus.ACTIVE,
                "dat@gmail.com", "0123456789", Gender.MALE,
                LocalDate.of(2002, 5, 15), Arrays.asList("M03", "M01")
        );

        User lockedUser = new User(
                "U03", "badboy", "111222", Role.USER, UserStatus.LOCKED,
                "badboy@gmail.com", "0999888777", Gender.OTHER,
                LocalDate.of(2005, 10, 10), new ArrayList<>()
        );

        userDAO.add(admin);
        userDAO.add(normalUser);
        userDAO.add(lockedUser);

        Booking b1 = new Booking(
                "B1001", LocalDateTime.now().minusDays(2), "Combo 1", 0,
                100000, Booking.Status.SUCCESS, "U02", "ST01",
                Arrays.asList("R01_A01", "R01_A02")
        );

        Booking b2 = new Booking(
                "B1002", LocalDateTime.now(), "Không mua", 20000,
                50000, Booking.Status.PENDING, "U01", "ST02",
                Arrays.asList("R02_C01")
        );

        bookingDAO.add(b1);
        bookingDAO.add(b2);

        Review rv1 = new Review("RV01", 5, "Phim rất hay, kỹ xảo xuất sắc!", "U02", "M02");
        Review rv2 = new Review("RV02", 4, "Diễn viên đóng đạt nhưng kết hơi buồn.", "U01", "M01");

        reviewDAO.add(rv1);
        reviewDAO.add(rv2);

        System.out.println("✅ Đã tạo Fake Data thành công vào các file .dat!");
        System.out.println("Bạn có thể bật form Login lên để test luồng Đặt vé và Lịch sử.");
    }
}