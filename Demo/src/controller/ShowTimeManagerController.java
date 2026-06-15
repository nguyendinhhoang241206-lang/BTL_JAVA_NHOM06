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
    // =========================================================
    // SỬA LẠI HÀM THÊM: ÁP DỤNG TRY-CATCH HỨNG LỖI TỪ ENTITY
    // =========================================================
    public String handleAddShowTime(String id, java.util.Date chosenDate, java.util.Date sTime, java.util.Date eTime, String movieId, String roomId) {
        try {
            // Kiểm tra trùng mã lịch chiếu (nghiệp vụ liên quan database/file nên để ở Service)
            if (service.checkIdExist(id)) {
                return "ERROR:Mã lịch chiếu này đã tồn tại từ trước!";
            }

            // Chặn NullPointerException khi ép kiểu Date sang LocalDate
            if (chosenDate == null || sTime == null || eTime == null) {
                return "ERROR:Vui lòng nhập đầy đủ ngày và giờ chiếu!";
            }

            // Xử lý định dạng thời gian
            java.time.LocalDate date = chosenDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.LocalTime startTime = sTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
            java.time.LocalTime endTime = eTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);

            String finalMovieId = movieId.contains(" - ") ? movieId.split(" - ")[0] : movieId;
            String finalRoomId = roomId.contains(" - ") ? roomId.split(" - ")[0] : roomId;

            // KÍCH HOẠT CƠ CHẾ TRY-CATCH: 
            // Nếu dữ liệu sai (VD: chọn ngày quá khứ), lệnh 'new ShowTime' sẽ thất bại
            // và ném ra IllegalArgumentException, nhảy thẳng xuống khối catch bên dưới.
            ShowTime st = new ShowTime(id.trim(), date, startTime, endTime, finalMovieId, finalRoomId);

            // Xuống được đến đây nghĩa là dữ liệu hoàn toàn hợp lệ, tiến hành lưu
            boolean success = service.addShowTime(st);
            if (success) {
                return "SUCCESS";
            } else {
                return "ERROR:Trùng lịch chiếu! Phòng này đã có phim khác chiếu trong khung giờ trên.";
            }

        } catch (IllegalArgumentException e) {
            // HỨNG LỖI LAN TRUYỀN TỪ LỚP SHOWTIME (Ví dụ: e.getMessage() = "Ngày chiếu không được là ngày trong quá khứ!")
            return "ERROR:" + e.getMessage();
        } catch (Exception e) {
            return "ERROR:Lỗi định dạng hệ thống! Vui lòng kiểm tra lại.";
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