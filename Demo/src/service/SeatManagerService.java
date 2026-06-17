package service;

import model.Seat;
import java.util.ArrayList;
import java.util.List;

public class SeatManagerService {

    public boolean generateSeatsForRoom(String roomId, int totalSeats) {
        List<Seat> generatedSeats = new ArrayList<>();
        int seatsPerRow = 10;

        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) ('A' + (i / seatsPerRow));
            int seatNumber = (i % seatsPerRow) + 1;
            String seatName = "" + rowChar + seatNumber;

            Seat seat = new Seat(
                    "SEAT_" + roomId + "_" + seatName,
                    seatName,
                    Seat.Type.NORMAL,
                    50000.0,
                    roomId
            );
            generatedSeats.add(seat);
        }

        System.out.println("Hệ thống đã tự động sinh " + generatedSeats.size() + " ghế cho phòng: " + roomId);
        
        return true; 
    }
}