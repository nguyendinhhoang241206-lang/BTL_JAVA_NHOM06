package dao;

import model.Room;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private List<Room> rooms = new ArrayList<>();
    private static final String FILE_PATH = "data/rooms.dat";

    public List<Room> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.rooms = new ArrayList<>();
            return this.rooms;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.rooms = (List<Room>) obj;
            } else {
                this.rooms = new ArrayList<>();
            }
        } catch (Exception e) {
            this.rooms = new ArrayList<>();
        }
        return this.rooms;
    }

    public boolean writeToFile(List<Room> list) {
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
            this.rooms = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(Room room) {
        if (room == null) {
            return false;
        }
        readFromFile();
        this.rooms.add(room);
        return writeToFile(this.rooms);
    }

    public boolean update(Room room) {
        if (room == null || room.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).getId().equals(room.getId())) {
                this.rooms.set(i, room);
                return writeToFile(this.rooms);
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
        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).getId().equals(id)) {
                this.rooms.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.rooms);
        }
        return false;
    }

    public Room findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (Room room : this.rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public List<Room> findAll() {
        return readFromFile();
    }
}
