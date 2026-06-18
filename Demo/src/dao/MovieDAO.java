package dao;

import model.Movie;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private List<Movie> movies = new ArrayList<>();
    private static final String FILE_PATH = "data/movies.dat";

    public List<Movie> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.movies = new ArrayList<>();
            return this.movies;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.movies = (List<Movie>) obj;
            } else {
                this.movies = new ArrayList<>();
            }
        } catch (Exception e) {
            this.movies = new ArrayList<>();
        }
        return this.movies;
    }

    public boolean writeToFile(List<Movie> list) {
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
            this.movies = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(Movie movie) {
        if (movie == null) {
            return false;
        }
        readFromFile();
        this.movies.add(movie);
        return writeToFile(this.movies);
    }

    public boolean update(Movie movie) {
        if (movie == null || movie.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getId().equals(movie.getId())) {
                this.movies.set(i, movie);
                return writeToFile(this.movies);
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
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getId().equals(id)) {
                this.movies.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.movies);
        }
        return false;
    }

    public Movie findById(String id) {
        if (id == null) {
            return null;
        }
        
        readFromFile();
        
        for (Movie movie : this.movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> findAll() {
        return readFromFile();
    }
     
}
