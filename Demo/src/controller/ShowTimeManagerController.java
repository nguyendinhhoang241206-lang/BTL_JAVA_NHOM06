package controller;

import model.ShowTime;
import service.ShowTimeManagerService;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ShowTimeManagerController {
    private ShowTimeManagerService service = new ShowTimeManagerService();

    // ==========================================
    // 1. HÀM LOAD DỮ LIỆU TỪ DAO LÊN COMBOBOX
    // ==========================================
    public void initComboBoxes(javax.swing.JComboBox<String> cbMovie, javax.swing.JComboBox<String> cbRoom) {
        cbMovie.removeAllItems();
        cbRoom.removeAllItems();

        // Gọi DAO lấy danh sách Phim
        java.util.List<model.Movie> movies = new dao.MovieDAO().findAll();
        if (movies != null) {
            for (model.Movie m : movies) {
                // Hiển thị đẹp: "ID - Tên phim"
                cbMovie.addItem(m.getId() + " - " + m.getTitle());
            }
        }

        // Gọi DAO lấy danh sách Phòng
        try {
            // Lưu ý: Đảm bảo bạn đã tạo file RoomDAO.java và hàm findAll()
            java.util.List<model.Room> rooms = new dao.RoomDAO().findAll();
            if (rooms != null) {
                for (model.Room r : rooms) {
                    cbRoom.addItem(r.getId() + " - " + r.getName());
                }
            }
        } catch (Exception e) {
            // Nếu bạn chưa code RoomDAO thì nó sẽ add tạm 2 phòng này để không bị lỗi ứng dụng
            cbRoom.addItem("R01 - Phòng Standard 1");
            cbRoom.addItem("R02 - Phòng VIP 1");
        }
    }

    // =========================================================
    // ĐỔI THÀNH PUBLIC: Để View có thể gọi lấy mã hiển thị trước
    // =========================================================
    public String getNextShowTimeId() {
        java.util.Random random = new java.util.Random();
        String newId;
        boolean isDuplicate;

        do {
            newId = String.format("ST%03d", random.nextInt(1000));
            isDuplicate = service.checkIdExist(newId); // Check trùng
        } while (isDuplicate);

        return newId;
    }

    // =========================================================
    // SỬA LẠI HÀM THÊM: Nhận ID từ giao diện truyền vào
    // =========================================================
    public String handleAddShowTime(String id, java.util.Date chosenDate, java.util.Date sTime, java.util.Date eTime, String movieId, String roomId) {
        try {
            if (id == null || id.trim().isEmpty() || id.equals("Hệ thống tự tạo")) {
                return "ERROR:Mã suất chiếu không hợp lệ!";
            }
            if (service.checkIdExist(id)) {
                return "ERROR:Mã lịch chiếu này đã tồn tại từ trước!";
            }
            if (chosenDate == null) {
                return "ERROR:Vui lòng chọn ngày chiếu phim!";
            }

            // Xử lý thời gian
            java.time.LocalDate date = chosenDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.LocalTime startTime = sTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
            java.time.LocalTime endTime = eTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);

            if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                return "ERROR:Giờ bắt đầu phải trước giờ kết thúc!";
            }

            String finalMovieId = movieId.contains(" - ") ? movieId.split(" - ")[0] : movieId;
            String finalRoomId = roomId.contains(" - ") ? roomId.split(" - ")[0] : roomId;

            // Sử dụng luôn cái ID hiển thị trên giao diện
            ShowTime st = new ShowTime(id.trim(), date, startTime, endTime, finalMovieId, finalRoomId);

            boolean success = service.addShowTime(st);
            if (success) {
                return "SUCCESS";
            } else {
                return "ERROR:Trùng lịch chiếu! Phòng này đã có phim khác chiếu trong khung giờ trên.";
            }

        } catch (Exception e) {
            return "ERROR:Lỗi xử lý dữ liệu! Vui lòng kiểm tra lại.";
        }
    }

    // ==========================================
    // 3. LOAD JTABLE VÀ XÓA (Giữ nguyên)
    // ==========================================
    public void loadDataToTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        List<ShowTime> list = service.getAllShowTimes();
        if (list != null) {
            for (ShowTime st : list) {
                tableModel.addRow(new Object[]{
                        st.getId(), st.getMovieId(), st.getRoomId(), st.getShowDate().toString(), st.getStartTime().toString(), st.getEndTime().toString()
                });
            }
        }
    }

    public boolean handleDeleteShowTime(String id) {
        return service.deleteShowTime(id);
    }
}