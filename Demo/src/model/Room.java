package model;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private int totalSeats;

    // No-args Constructor
    public Room() {
    }

    // All-args Constructor
    public Room(String id, String name, int totalSeats) {
        this.id = id;
        this.name = name;
        this.totalSeats = totalSeats;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalSeats=" + totalSeats +
                '}';
    }
}
