package service;

import dao.ShowTimeDAO;
import model.ShowTime;

public class ShowTimeManagerService {
    private ShowTimeDAO showTimeDAO = new ShowTimeDAO();

    // TODO: Sinh viên tự code logic: Gọi checkTimeConflict(showTime) để kiểm tra xem lịch chiếu mới này có bị trùng giờ chiếu của bất kỳ phim nào khác trong cùng một phòng chiếu hay không. Nếu không trùng, tiến hành gọi showTimeDAO.add(showTime) để lưu lịch chiếu mới.
    public boolean addShowTime(ShowTime showTime) {
        return false;
    }

    // TODO: Sinh viên tự code logic: Lấy toàn bộ lịch chiếu từ showTimeDAO.findAll(). Lọc ra các lịch chiếu có cùng roomId và cùng showDate. Sau đó so khớp khoảng thời gian [startTime, endTime] của lịch chiếu mới có giao nhau với bất kỳ lịch chiếu nào sẵn có trong phòng chiếu đó hay không. Trả về true nếu bị trùng/conflict.
    public boolean checkTimeConflict(ShowTime showTime) {
        return false;
    }
}
