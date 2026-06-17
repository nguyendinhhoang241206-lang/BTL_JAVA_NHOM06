package service;

import dao.ShowTimeDAO;
import model.ShowTime;
import java.util.List;

public class ShowTimeManagerService {
    private ShowTimeDAO showTimeDAO = new ShowTimeDAO();

    public boolean addShowTime(ShowTime showTime) {
        if (checkTimeConflict(showTime)) {
            return false;
        }
        return showTimeDAO.add(showTime);
    }

    public boolean checkIdExist(String id) {
        List<ShowTime> allShowTimes = showTimeDAO.findAll();
        if (allShowTimes != null) {
            for (ShowTime st : allShowTimes) {
                if (st.getId().equalsIgnoreCase(id.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkTimeConflict(ShowTime showTime) {
        List<ShowTime> allShowTimes = showTimeDAO.findAll();
        if (allShowTimes == null || allShowTimes.isEmpty()) {
            return false;
        }

        for (ShowTime existing : allShowTimes) {
            if (existing.getRoomId().equals(showTime.getRoomId()) &&
                existing.getShowDate().equals(showTime.getShowDate())) {

                if (showTime.getStartTime().isBefore(existing.getEndTime()) &&
                    showTime.getEndTime().isAfter(existing.getStartTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<ShowTime> getAllShowTimes() {
        return showTimeDAO.findAll();
    }
    public boolean deleteShowTime(String id) {
        return showTimeDAO.delete(id);
    }
}