package controller;

import model.ShowTime;
import service.ShowTimeManagerService;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ShowTimeManagerController {
    private ShowTimeManagerService service = new ShowTimeManagerService();

    // Tiếp nhận dữ liệu thô từ View, xử lý logic và trả về thông báo
    public String handleAddShowTime(String id, java.util.Date chosenDate, java.util.Date sTime, java.util.Date eTime, String movieId, String roomId) {
        try {
            // 1. Kiểm tra rỗng
            if (id == null || id.trim().isEmpty()) {
                return "Vui lòng nhập Showtime ID!";
            }
            if (chosenDate == null) {
                return "Vui lòng chọn ngày chiếu phim!";
            }

            // 2. Chuyển đổi và làm tròn thời gian (Logic xử lý dữ liệu)
            java.time.LocalDate date = chosenDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
                    
            java.time.LocalTime startTime = sTime.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalTime()
                    .truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
                    
            java.time.LocalTime endTime = eTime.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalTime()
                    .truncatedTo(java.time.temporal.ChronoUnit.MINUTES);

            // 3. Kiểm tra tính hợp lệ của Thời gian
            if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                return "Giờ bắt đầu phải trước giờ kết thúc!";
            }

            // 4. Đóng gói Model và gọi Service
            ShowTime st = new ShowTime(id.trim(), date, startTime, endTime, movieId, roomId);
            boolean success = service.addShowTime(st);
            
            if (success) {
                return "SUCCESS";
            } else {
                return "Trùng lịch chiếu! Đã có phim khác chiếu tại phòng này trong khoảng thời gian trên.";
            }
            
        } catch (Exception e) {
            return "Lỗi xử lý dữ liệu! Vui lòng kiểm tra lại.";
        }
    }

    // Load dữ liệu lên bảng
    public void loadDataToTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); 
        List<ShowTime> list = service.getAllShowTimes();
        if (list != null) {
            for (ShowTime st : list) {
                tableModel.addRow(new Object[]{
                    st.getId(), 
                    st.getMovieId(), 
                    st.getRoomId(), 
                    st.getShowDate().toString(), 
                    st.getStartTime().toString(), 
                    st.getEndTime().toString()
                });
            }
        }
    }

    // Xử lý xóa
    public boolean handleDeleteShowTime(String id) {
        return service.deleteShowTime(id);
    }
}