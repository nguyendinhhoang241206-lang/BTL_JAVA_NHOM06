package dao;

import model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users = new ArrayList<>();
    private static final String FILE_PATH = "data/users.dat";

    public List<User> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.users = new ArrayList<>();
            return this.users;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.users = (List<User>) obj;
            } else {
                this.users = new ArrayList<>();
            }
        } catch (Exception e) {
            this.users = new ArrayList<>();
        }
        return this.users;
    }

    public boolean writeToFile(List<User> list) {
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
            this.users = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(User user) {
        if (user == null) {
            return false;
        }
        readFromFile();
        this.users.add(user);
        return writeToFile(this.users);
    }

    public boolean update(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId().equals(user.getId())) {
                this.users.set(i, user);
                return writeToFile(this.users);
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
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId().equals(id)) {
                this.users.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.users);
        }
        return false;
    }

    public User findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (User user : this.users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        if (username == null) {
            return null;
        }
        readFromFile();
        for (User user : this.users) {
            if (user.getUsername() != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
