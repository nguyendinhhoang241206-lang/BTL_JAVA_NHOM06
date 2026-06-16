package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Status {
        PENDING,
        SUCCESS,
        CANCELLED
    }

    private String id;
    private LocalDateTime bookingDate;
    private String comboName;
    private double discountAmount;
    private double totalPrice;
    private Status status;
    private String userId;
    private String showTimeId;
    private List<String> bookedSeatIds;

    public Booking() {
    }

    public Booking(String id, LocalDateTime bookingDate, String comboName, double discountAmount,
                   double totalPrice, Status status, String userId, String showTimeId, List<String> bookedSeatIds) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.comboName = comboName;
        this.discountAmount = discountAmount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
        this.showTimeId = showTimeId;
        this.bookedSeatIds = bookedSeatIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(String showTimeId) {
        this.showTimeId = showTimeId;
    }

    public List<String> getBookedSeatIds() {
        return bookedSeatIds;
    }

    public void setBookedSeatIds(List<String> bookedSeatIds) {
        this.bookedSeatIds = bookedSeatIds;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", bookingDate=" + bookingDate +
                ", comboName='" + comboName + '\'' +
                ", discountAmount=" + discountAmount +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                ", showTimeId='" + showTimeId + '\'' +
                ", bookedSeatIds=" + bookedSeatIds +
                '}';
    }
}
