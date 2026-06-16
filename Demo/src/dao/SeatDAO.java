package dao;

import model.Seat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Seat.Type;

public class SeatDAO {
    private List<Seat> seats = new ArrayList<>();
    private static final String FILE_PATH = "data/seats.dat";

    public List<Seat> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.seats = new ArrayList<>();
            return this.seats;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.seats = (List<Seat>) obj;
            } else {
                this.seats = new ArrayList<>();
            }
        } catch (Exception e) {
            this.seats = new ArrayList<>();
        }
        return this.seats;
    }

    public boolean writeToFile(List<Seat> list) {
        if (list == null) {
            return false;
        }
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
            this.seats = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(Seat seat) {
        if (seat == null) {
            return false;
        }
        readFromFile();
        this.seats.add(seat);
        return writeToFile(this.seats);
    }

    public boolean update(Seat seat) {
        if (seat == null || seat.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.seats.size(); i++) {
            if (this.seats.get(i).getId().equals(seat.getId())) {
                this.seats.set(i, seat);
                return writeToFile(this.seats);
            }
        }
        return false;
    }

    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        readFromFile();
        boolean removed = false;
        for (int i = 0; i < this.seats.size(); i++) {
            if (this.seats.get(i).getId().equals(id)) {
                this.seats.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.seats);
        }
        return false;
    }

    public Seat findById(String id) {
        if (id == null) {
            return null;
        }
        
        readFromFile();
        
        for (Seat seat : this.seats) {
            if (seat.getId().equals(id)) {
                return seat;
            }
        }
        return null;
    }

    public List<Seat> findByRoomId(String roomId) {
        if (roomId == null) {
            return new ArrayList<>();
        }
        readFromFile();
        List<Seat> result = new ArrayList<>();
        for (Seat seat : this.seats) {
            if (seat.getRoomId() != null && seat.getRoomId().equals(roomId)) {
                result.add(seat);
            }
        }
        return result;
    }

    public List<Seat> findAll() {
        return readFromFile();
    }
    
}
