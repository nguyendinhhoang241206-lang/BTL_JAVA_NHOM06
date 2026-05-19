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

    // No-args Constructor
    public ShowTime() {
    }

    // All-args Constructor
    public ShowTime(String id, LocalDate showDate, LocalTime startTime, LocalTime endTime, String movieId, String roomId) {
        this.id = id;
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieId = movieId;
        this.roomId = roomId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "id='" + id + '\'' +
                ", showDate=" + showDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", movieId='" + movieId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
