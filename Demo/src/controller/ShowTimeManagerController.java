package controller;

import model.ShowTime;
import service.ShowTimeManagerService;

public class ShowTimeManagerController {
    private ShowTimeManagerService showTimeManagerService = new ShowTimeManagerService();

    // TODO: Sinh viên tự code logic: Nhận thông tin xếp lịch chiếu từ View (chọn phim, chọn phòng, chọn ngày, chọn thời gian bắt đầu/kết thúc), validate dữ liệu hợp lệ (startTime < endTime), sau đó gọi showTimeManagerService.addShowTime(showTime) để xử lý lưu trữ và hiển thị kết quả thành công hoặc cảnh báo trùng lịch.
    public boolean handleAddShowTime(ShowTime showTime) {
        return false;
    }
}
