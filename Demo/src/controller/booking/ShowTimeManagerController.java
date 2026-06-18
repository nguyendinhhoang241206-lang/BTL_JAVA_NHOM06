package controller.booking;

import model.ShowTime;
import service.booking.ShowTimeManagerService;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ShowTimeManagerController {
    private ShowTimeManagerService service = new ShowTimeManagerService();

    public void initComboBoxes(javax.swing.JComboBox<String> cbMovie, javax.swing.JComboBox<String> cbRoom) {
        cbMovie.removeAllItems();
        cbRoom.removeAllItems();

        java.util.List<model.Movie> movies = new dao.MovieDAO().findAll();
        if (movies != null) {
            for (model.Movie m : movies) {
                cbMovie.addItem(m.getId() + " - " + m.getTitle());
            }
        }

        try {
            java.util.List<model.Room> rooms = new dao.RoomDAO().findAll();
            if (rooms != null) {
                for (model.Room r : rooms) {
                    cbRoom.addItem(r.getId() + " - " + r.getName());
                }
            }
        } catch (Exception e) {
            cbRoom.addItem("R01 - Phòng Standard 1");
            cbRoom.addItem("R02 - Phòng VIP 1");
        }
    }

    public String getNextShowTimeId() {
        java.util.Random random = new java.util.Random();
        String newId;
        boolean isDuplicate;

        do {
            newId = String.format("ST%03d", random.nextInt(1000));
            isDuplicate = service.checkIdExist(newId);
        } while (isDuplicate);

        return newId;
    }

    public String handleAddShowTime(String id, java.util.Date chosenDate, java.util.Date sTime, java.util.Date eTime, String movieId, String roomId) {
        try {
            if (service.checkIdExist(id)) {
                return "ERROR:Mã lịch chiếu này đã tồn tại từ trước!";
            }

            if (chosenDate == null || sTime == null || eTime == null) {
                return "ERROR:Vui lòng nhập đầy đủ ngày và giờ chiếu!";
            }

            java.time.LocalDate date = chosenDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.LocalTime startTime = sTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
            java.time.LocalTime endTime = eTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);

            String finalMovieId = movieId.contains(" - ") ? movieId.split(" - ")[0] : movieId;
            String finalRoomId = roomId.contains(" - ") ? roomId.split(" - ")[0] : roomId;

            ShowTime st = new ShowTime(id.trim(), date, startTime, endTime, finalMovieId, finalRoomId);

            boolean success = service.addShowTime(st);
            if (success) {
                return "SUCCESS";
            } else {
                return "ERROR:Trùng lịch chiếu! Phòng này đã có phim khác chiếu trong khung giờ trên.";
            }

        } catch (IllegalArgumentException e) {
            return "ERROR:" + e.getMessage();
        } catch (Exception e) {
            return "ERROR:Lỗi định dạng hệ thống! Vui lòng kiểm tra lại.";
        }
    }   

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