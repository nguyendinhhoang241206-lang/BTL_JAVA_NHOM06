package controller;

import model.Room;
import service.RoomManagerService;
import view.show_time_room_infrForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RoomManagerController {
    
    private RoomManagerService roomManagerService;
    private show_time_room_infrForm view;

    // Khởi tạo Controller nhận View vào để điều khiển
    public RoomManagerController(show_time_room_infrForm view) {
        this.view = view;
        this.roomManagerService = new RoomManagerService();

        // 1. Gắn tai nghe sự kiện cho nút bấm thông qua hàm Getter
        this.view.getBtnAddRoom().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddRoomEvent();
            }
        });

        // 2. Load dữ liệu lên bảng khi giao diện vừa bật
        refreshRoomTable();
    }

    // Nơi xử lý logic khi nút được bấm
    private void handleAddRoomEvent() {
        // Lấy dữ liệu qua các hàm Getter
        String roomId = view.getTxtRoomId().getText().trim();
        String roomName = view.getTxtRoomName().getText().trim();
        String totalSeatsStr = view.getTxtTotalSeats().getText().trim();

        // 1. Chỉ bắt buộc nhập Mã phòng và Tên phòng
        if (roomId.isEmpty() || roomName.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập Mã phòng và Tên phòng!");
            return;
        }

        // 2. Xử lý logic số ghế: Tối đa 50, mặc định 50
        int totalSeats;
        if (totalSeatsStr.isEmpty()) {
            totalSeats = 50; // Nếu người dùng không nhập gì, mặc định là 50 ghế
        } else {
            try {
                totalSeats = Integer.parseInt(totalSeatsStr);
                
                // Kiểm tra giới hạn Min - Max
                if (totalSeats <= 0) {
                    JOptionPane.showMessageDialog(view, "Số lượng ghế phải lớn hơn 0!");
                    return;
                }
                if (totalSeats > 50) {
                    JOptionPane.showMessageDialog(view, "Một phòng chiếu chỉ chứa tối đa 50 ghế!");
                    return;
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Tổng số ghế phải là số nguyên!");
                return;
            }
        }

        // 3. Tiến hành lưu file và sinh ghế
        Room newRoom = new Room(roomId, roomName, totalSeats);
        boolean isSuccess = roomManagerService.addRoom(newRoom);

        if (isSuccess) {
            JOptionPane.showMessageDialog(view, "Thêm phòng và sinh " + totalSeats + " ghế thành công!");
            refreshRoomTable();
            view.clearInputFields(); 
        } else {
            JOptionPane.showMessageDialog(view, "Thêm thất bại! Mã phòng đã tồn tại.");
        }
    }

    private void refreshRoomTable() {
        DefaultTableModel model = (DefaultTableModel) view.getTblRooms().getModel();
        model.setRowCount(0); 
        
        List<Room> rooms = roomManagerService.getAllRooms(); // Chú ý: Service cần có hàm getAllRooms() trả về roomDAO.findAll()
        if (rooms != null) {
            for (Room r : rooms) {
                model.addRow(new Object[]{r.getId(), r.getName(), r.getTotalSeats()});
            }
        }
    }
}