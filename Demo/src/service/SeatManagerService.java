package service;

import model.Seat;
import java.util.ArrayList;
import java.util.List;

public class SeatManagerService {

    // Hàm tự động sinh ghế dựa theo tổng số ghế
    public boolean generateSeatsForRoom(String roomId, int totalSeats) {
        List<Seat> generatedSeats = new ArrayList<>();
        int seatsPerRow = 10; // Cứ 10 ghế sẽ đổi hàng (A -> B)

        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) ('A' + (i / seatsPerRow));
            int seatNumber = (i % seatsPerRow) + 1;
            String seatName = "" + rowChar + seatNumber;

            // Sử dụng constructor của model Seat bạn đã gửi trước đó
            Seat seat = new Seat(
                    "SEAT_" + roomId + "_" + seatName, // id ghế
                    seatName,                          // tên ghế (VD: A1, A2)
                    Seat.Type.NORMAL,                  // loại ghế mặc định
                    50000.0,                           // giá tiền mặc định
                    roomId                             // ID phòng mà ghế này thuộc về
            );
            generatedSeats.add(seat);
        }

        // TODO sau này: Thường thì ở đây sẽ gọi seatDAO.addList(generatedSeats) để lưu ghế vào file riêng (VD: seats.dat)
        // Hiện tại, mình in ra console và trả về true để luồng phần mềm chạy thông suốt.
        System.out.println("Hệ thống đã tự động sinh " + generatedSeats.size() + " ghế cho phòng: " + roomId);
        
        return true; 
    }
}