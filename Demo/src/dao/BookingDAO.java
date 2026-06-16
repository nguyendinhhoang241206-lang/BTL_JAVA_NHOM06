package dao;

import model.Booking;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingDAO {
    private List<Booking> bookings = new ArrayList<>();
    private static final String FILE_PATH = "data/bookings.dat";

    public List<Booking> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.bookings = new ArrayList<>();
            return this.bookings;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.bookings = (List<Booking>) obj;
            } else {
                this.bookings = new ArrayList<>();
            }
        } catch (Exception e) {
            this.bookings = new ArrayList<>();
        }
        return this.bookings;
    }

    public boolean writeToFile(List<Booking> list) {
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
            this.bookings = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(Booking booking) {
        if (booking == null) {
            return false;
        }
        readFromFile();
        this.bookings.add(booking);
        return writeToFile(this.bookings);
    }

    public boolean update(Booking booking) {
        if (booking == null || booking.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.bookings.size(); i++) {
            if (this.bookings.get(i).getId().equals(booking.getId())) {
                this.bookings.set(i, booking);
                return writeToFile(this.bookings);
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
        for (int i = 0; i < this.bookings.size(); i++) {
            if (this.bookings.get(i).getId().equals(id)) {
                this.bookings.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.bookings);
        }
        return false;
    }

    public Booking findById(String id) {
        if (id == null) {
            return null;
        }
        
        readFromFile();
        
        for (Booking booking : this.bookings) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }
        return null;
    }

    public List<Booking> findByUserId(String userId) {
        if (userId == null) {
            return new ArrayList<>();
        }
        readFromFile();
        
        List<Booking> result = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if (booking.getUserId() != null && booking.getUserId().equals(userId)) {
                result.add(booking);
            }
        }
        return result;
    }

    public List<Booking> findByShowTimeId(String showTimeId) {
        if (showTimeId == null) {
            return new ArrayList<>();
        }
        readFromFile();
        List<Booking> result = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if (booking.getShowTimeId() != null && booking.getShowTimeId().equals(showTimeId)) {
                result.add(booking);
            }
        }
        return result;
    }

    public List<Booking> findAll() {
        return readFromFile();
    }
    
}
