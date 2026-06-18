package model;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private int totalSeats;

    public Room() {
    }

    public Room(String id, String name, int totalSeats) {
        setId(id);
        setName(name);
        setTotalSeats(totalSeats);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên phòng không được để trống!"); 
        }
        this.name = name;
    }

    public void setTotalSeats(int totalSeats) throws IllegalArgumentException {
        if (totalSeats <= 0 || totalSeats > 50) {
            throw new IllegalArgumentException("Số ghế phải lớn hơn 0 và tối đa là 50!");
        }
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        
        
        
        
        return "Room{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", totalSeats=" + totalSeats + '}';
    }
}