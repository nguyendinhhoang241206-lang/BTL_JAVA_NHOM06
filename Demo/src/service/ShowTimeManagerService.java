package service;

import dao.ShowTimeDAO;
import model.ShowTime;
import java.util.List;

public class ShowTimeManagerService {
    private ShowTimeDAO showTimeDAO = new ShowTimeDAO();

    // 1. Xử lý nghiệp vụ Thêm suất chiếu
    public boolean addShowTime(ShowTime showTime) {
        // Quy tắc nghiệp vụ: Bắt buộc phải chạy thuật toán check trùng lịch trước khi lưu
        if (checkTimeConflict(showTime)) {
            return false; // Phát hiện trùng lịch -> Từ chối lưu
        }
        // Nếu an toàn, đẩy dữ liệu xuống tầng DAO để ghi vào file showtimes.dat
        return showTimeDAO.add(showTime);
    }


    // Kiểm tra xem Showtime ID đã bị trùng trong file chưa
    public boolean checkIdExist(String id) {
        List<ShowTime> allShowTimes = showTimeDAO.findAll();
        if (allShowTimes != null) {
            for (ShowTime st : allShowTimes) {
                if (st.getId().equalsIgnoreCase(id.trim())) {
                    return true; // Bị trùng ID
                }
            }
        }
        return false; // ID an toàn
    }

    // 2. Thuật toán kiểm tra trùng lịch (Business Logic cốt lõi)
    public boolean checkTimeConflict(ShowTime showTime) {
        List<ShowTime> allShowTimes = showTimeDAO.findAll();
        if (allShowTimes == null || allShowTimes.isEmpty()) {
            return false; // File trống, chắc chắn không trùng
        }

        for (ShowTime existing : allShowTimes) {
            // Chỉ xét trùng lịch nếu hai suất chiếu nằm Cùng Phòng và Cùng Ngày
            if (existing.getRoomId().equals(showTime.getRoomId()) &&
                existing.getShowDate().equals(showTime.getShowDate())) {
                
                // Công thức Overlap (Giao nhau về thời gian): 
                // Thời gian Bắt đầu mới < Thời gian Kết thúc cũ VÀ Thời gian Kết thúc mới > Thời gian Bắt đầu cũ
                if (showTime.getStartTime().isBefore(existing.getEndTime()) &&
                    showTime.getEndTime().isAfter(existing.getStartTime())) {
                    return true; // Bị trùng lịch
                }
            }
        }
        return false; // Kiểm tra hết danh sách không thấy trùng -> An toàn
    }

    // 3. Lấy toàn bộ danh sách suất chiếu để Controller gọi
    public List<ShowTime> getAllShowTimes() {
        return showTimeDAO.findAll();
    }
     /*        */
    // 4. Xóa suất chiếu dựa trên ID
    public boolean deleteShowTime(String id) {
        return showTimeDAO.delete(id);
    }
}