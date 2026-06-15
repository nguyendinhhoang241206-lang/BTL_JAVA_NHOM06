package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ShowTime implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String movieId;
    private String roomId;

    public ShowTime() {
    }

    public ShowTime(String id, LocalDate showDate, LocalTime startTime, LocalTime endTime, String movieId, String roomId) {
        // Sử dụng hàm Set để Constructor cũng được bảo vệ bởi bẫy lỗi
        setId(id);
        setShowDate(showDate);
        setStartTime(startTime);
        setEndTime(endTime);
        setMovieId(movieId);
        setRoomId(roomId);
        
        // Bẫy lỗi logic: Giờ bắt đầu phải trước giờ kết thúc
        if (this.startTime != null && this.endTime != null && 
           (this.startTime.isAfter(this.endTime) || this.startTime.equals(this.endTime))) {
            throw new IllegalArgumentException("Giờ bắt đầu phải trước giờ kết thúc!");
        }
    }

    public String getId() { return id; }
    public void setId(String id) throws IllegalArgumentException {
        if (id == null || id.trim().isEmpty() || id.equals("Hệ thống tự tạo")) {
            throw new IllegalArgumentException("Mã suất chiếu không hợp lệ!");
        }
        this.id = id;
    }

    public LocalDate getShowDate() { return showDate; }
    public void setShowDate(LocalDate showDate) throws IllegalArgumentException {
        if (showDate == null) {
            throw new IllegalArgumentException("Vui lòng chọn ngày chiếu phim!");
        }
        // Nâng cao: Không cho phép xếp lịch chiếu vào ngày trong quá khứ
        if (showDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày chiếu không được là ngày trong quá khứ!");
        }
        this.showDate = showDate;
    }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) throws IllegalArgumentException {
        if (movieId == null || movieId.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng chọn phim hợp lệ!");
        }
        this.movieId = movieId;
    }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) throws IllegalArgumentException {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng chọn phòng chiếu hợp lệ!");
        }
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "ShowTime{" + "id='" + id + '\'' + ", showDate=" + showDate + ", startTime=" + startTime + ", endTime=" + endTime + ", movieId='" + movieId + '\'' + ", roomId='" + roomId + '\'' + '}';
    }
}