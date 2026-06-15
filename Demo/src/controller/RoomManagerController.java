package controller;

import model.Room;
import service.RoomManagerService;
import view.show_time_room_infrForm;
import view.showtimeroom_schedule;

import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RoomManagerController {

    private RoomManagerService roomManagerService;
    private show_time_room_infrForm view;

    // Khởi tạo Controller nhận View vào để điều khiển
    public RoomManagerController(show_time_room_infrForm view) {
        this.view = view;
        this.roomManagerService = new RoomManagerService();

        // =========================================================
        // THIẾT LẬP GIAO DIỆN BAN ĐẦU
        // =========================================================
        // Khóa ô nhập Mã phòng (ID) và GỌI HÀM SINH MÃ HIỂN THỊ SẴN
        this.view.getTxtRoomId().setEditable(false);
        this.view.getTxtRoomId().setText(getNextRoomId());

        // Khóa ô Tổng số ghế và fix cứng hiển thị số 50
        this.view.getTxtTotalSeats().setEditable(false);
        this.view.getTxtTotalSeats().setText("50");

        // =========================================================
        // GẮN SỰ KIỆN NÚT BẤM
        // =========================================================
        this.view.getBtnAddRoom().addActionListener(e -> handleAddRoomEvent());

        if (this.view.getBtnBack() != null) {
            this.view.getBtnBack().addActionListener(e -> {
                // 1. TẠO VỎ JFRAME ẢO CHO TRANG CHỦ (DASHBOARD)
                javax.swing.JFrame mainFrame = new javax.swing.JFrame("Trang chủ Quản trị - Cinema System");
                mainFrame.setSize(1000, 600);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

                // 2. TẠO THANH MENU TỔNG HỢP (CHỈ DASHBOARD MỚI CÓ)
                javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();

                javax.swing.JMenu menuNav = new javax.swing.JMenu("Chức năng Hệ thống");

                // -- Nút Lịch chiếu
                javax.swing.JMenuItem itemSchedule = new javax.swing.JMenuItem("📅 Quản lý Lịch chiếu");
                itemSchedule.addActionListener(evt -> {
                    new view.showtimeroom_schedule().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemSchedule);

                // -- Nút Phòng chiếu
                javax.swing.JMenuItem itemRoom = new javax.swing.JMenuItem("🏢 Quản lý Phòng chiếu");
                itemRoom.addActionListener(evt -> {
                    new view.show_time_room_infrForm().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemRoom);

                // -- Nút Doanh thu
                javax.swing.JMenuItem itemRevenue = new javax.swing.JMenuItem("📈 Báo cáo Doanh thu");
                itemRevenue.addActionListener(evt -> {
                    new view.RevenueForm().setVisible(true);
                    mainFrame.dispose();
                });
                menuNav.add(itemRevenue);

                // -- Đăng xuất
                javax.swing.JMenu menuSystem = new javax.swing.JMenu("Tài khoản");
                javax.swing.JMenuItem itemLogout = new javax.swing.JMenuItem("🚪 Đăng xuất");
                itemLogout.addActionListener(evt -> {
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(mainFrame, "Đăng xuất tài khoản?", "Xác nhận", javax.swing.JOptionPane.YES_NO_OPTION);
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        view.LoginForm loginForm = new view.LoginForm();
                        new controller.LoginController(loginForm);
                        loginForm.setVisible(true);
                        mainFrame.dispose();
                    }
                });
                menuSystem.add(itemLogout);

                menuBar.add(menuNav);
                menuBar.add(menuSystem);
                mainFrame.setJMenuBar(menuBar); // Đính Menu lên Dashboard

                // 3. NHÚNG MẢNH GHÉP QUẢN LÝ PHIM VÀO DASHBOARD
                view.ShowlistmovieForm showListPanel = new view.ShowlistmovieForm();
                new controller.MovieController(showListPanel); // Kích hoạt nút bấm phim

                mainFrame.add(showListPanel);
                mainFrame.setVisible(true);
                this.view.dispose();
            });
        }

        refreshRoomTable();
    }

    // =========================================================
    // HÀM LẤY MÃ PHÒNG TIẾP THEO (R + 6 SỐ)
    // =========================================================
    public String getNextRoomId() {
        Random random = new Random();
        String newId;
        boolean isDuplicate;

        do {
            int randomNum = 100000 + random.nextInt(900000);
            newId = "R" + randomNum;

            isDuplicate = false;
            List<Room> rooms = roomManagerService.getAllRooms();
            if (rooms != null) {
                for (Room r : rooms) {
                    if (r.getId().equals(newId)) {
                        isDuplicate = true;
                        break;
                    }
                }
            }
        } while (isDuplicate);

        return newId;
    }

    // =========================================================
    // XỬ LÝ LOGIC KHI BẤM NÚT THÊM PHÒNG
    // =========================================================
    // =========================================================
    // XỬ LÝ LOGIC KHI BẤM NÚT THÊM PHÒNG
    // =========================================================
    private void handleAddRoomEvent() {
        String roomId = view.getTxtRoomId().getText().trim();
        String roomName = view.getTxtRoomName().getText().trim();
        int fixedTotalSeats = 50;

        try {
            // CƠ CHẾ TRY-CATCH: Nếu roomName rỗng, dòng 'new Room' sẽ kích hoạt hàm setName.
            // Hàm setName sẽ ném ra lỗi IllegalArgumentException và nhảy thẳng xuống khối catch.
            Room newRoom = new Room(roomId, roomName, fixedTotalSeats);
            
            // Nếu không có lỗi gì xảy ra ở trên thì mới tiến hành lưu
            boolean isSuccess = roomManagerService.addRoom(newRoom);

            if (isSuccess) {
                JOptionPane.showMessageDialog(view, "Thêm phòng thành công!");
                refreshRoomTable();
                view.clearInputFields(); 
                view.getTxtRoomId().setText(getNextRoomId());
            } else {
                JOptionPane.showMessageDialog(view, "Thêm thất bại (Mã phòng đã tồn tại)!");
            }

        } catch (IllegalArgumentException e) {
            // HỨNG LỖI LAN TRUYỀN: e.getMessage() chính là câu "Tên phòng không được để trống!" từ lớp Room
            JOptionPane.showMessageDialog(view, e.getMessage(), "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            // Hứng các lỗi hệ thống khác nếu có
            JOptionPane.showMessageDialog(view, "Có lỗi hệ thống xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // LOAD DỮ LIỆU LÊN BẢNG
    // =========================================================
    private void refreshRoomTable() {
        DefaultTableModel model = (DefaultTableModel) view.getTblRooms().getModel();
        model.setRowCount(0);

        List<Room> rooms = roomManagerService.getAllRooms();
        if (rooms != null) {
            for (Room r : rooms) {
                model.addRow(new Object[]{r.getId(), r.getName(), r.getTotalSeats()});
            }
        }
    }
}