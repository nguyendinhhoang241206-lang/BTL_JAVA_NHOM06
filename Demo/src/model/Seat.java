package model;

import java.io.Serializable;

public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Type {
        NORMAL,
        VIP,
        COUPLE
    }

    private String id;
    private String seatName;
    private Type type;
    private double price;
    private String roomId;

    // No-args Constructor
    public Seat() {
    }

    // All-args Constructor
    public Seat(String id, String seatName, Type type, double price, String roomId) {
        this.id = id;
        this.seatName = seatName;
        this.type = type;
        this.price = price;
        this.roomId = roomId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id='" + id + '\'' +
                ", seatName='" + seatName + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
