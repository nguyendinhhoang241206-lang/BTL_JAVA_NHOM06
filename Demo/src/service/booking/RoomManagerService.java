package service.booking;

import dao.RoomDAO;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomManagerService {
    private RoomDAO roomDAO = new RoomDAO();
    private SeatManagerService seatManagerService = new SeatManagerService();

    public boolean addRoom(Room room) {
        try {
            if (room == null) {
                throw new IllegalArgumentException("Dữ liệu phòng không hợp lệ!");
            }

            String roomName = room.getName();
            if (roomName == null || roomName.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên phòng không được để trống!");
            }

            List<Room> allRooms = roomDAO.findAll();

            System.out.println("\n--- KIỂM TRA TRÙNG TÊN PHÒNG ---");
            System.out.println("-> Tên phòng bạn VỪA NHẬP: [" + roomName.trim() + "]");
            if (allRooms == null || allRooms.isEmpty()) {
                System.out.println("-> CẢNH BÁO: Danh sách phòng đọc từ file đang rỗng (null hoặc empty)!");
            } else {
                System.out.println("-> Tìm thấy " + allRooms.size() + " phòng trong file. Bắt đầu đối chiếu...");
            }


            if (allRooms != null) {
                for (Room existingRoom : allRooms) {
                    if (existingRoom.getName() != null) {
   
                        System.out.println("   So sánh với phòng cũ trong file: [" + existingRoom.getName().trim() + "]");
                        if (existingRoom.getName().trim().equalsIgnoreCase(roomName.trim())) {
                            System.out.println("❌ PHÁT HIỆN TRÙNG NHAU! Hệ thống sẽ chặn và ném lỗi.");
                            throw new IllegalArgumentException("Tên phòng '" + roomName.trim() + "' đã tồn tại!");
                        }
                    }
                }
            }

     
            if (roomDAO.findById(room.getId()) != null) {
                System.out.println("❌ Trùng ID phòng.");
                return false;
            }

            boolean isRoomSaved = roomDAO.add(room);
            if (isRoomSaved) {
                System.out.println("✅ Ghi file thành công!");
                return seatManagerService.generateSeatsForRoom(room.getId(), room.getTotalSeats());
            }

            return false;

        } catch (IllegalArgumentException e) {
            throw e; 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Room> getAllRooms() {
        try {
            List<Room> rooms = roomDAO.findAll();
            return rooms != null ? rooms : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Lỗi khi tải danh sách phòng: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}