package dao;

import model.Review;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private List<Review> reviews = new ArrayList<>();
    private static final String FILE_PATH = "data/reviews.dat";

    public List<Review> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            this.reviews = new ArrayList<>();
            return this.reviews;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.reviews = (List<Review>) obj;
            } else {
                this.reviews = new ArrayList<>();
            }
        } catch (Exception e) {
            this.reviews = new ArrayList<>();
        }
        return this.reviews;
    }

    public boolean writeToFile(List<Review> list) {
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
            this.reviews = list;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(Review review) {
        if (review == null) {
            return false;
        }
        readFromFile();
        this.reviews.add(review);
        return writeToFile(this.reviews);
    }

    public boolean update(Review review) {
        if (review == null || review.getId() == null) {
            return false;
        }
        readFromFile();
        for (int i = 0; i < this.reviews.size(); i++) {
            if (this.reviews.get(i).getId().equals(review.getId())) {
                this.reviews.set(i, review);
                return writeToFile(this.reviews);
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
        for (int i = 0; i < this.reviews.size(); i++) {
            if (this.reviews.get(i).getId().equals(id)) {
                this.reviews.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            return writeToFile(this.reviews);
        }
        return false;
    }

    public Review findById(String id) {
        if (id == null) {
            return null;
        }
        readFromFile();
        for (Review review : this.reviews) {
            if (review.getId().equals(id)) {
                return review;
            }
        }
        return null;
    }

    public List<Review> findByMovieId(String movieId) {
        if (movieId == null) {
            return new ArrayList<>();
        }
        readFromFile();
        List<Review> result = new ArrayList<>();
        for (Review review : this.reviews) {
            if (review.getMovieId() != null && review.getMovieId().equals(movieId)) {
                result.add(review);
            }
        }
        return result;
    }

    public List<Review> findAll() {
        return readFromFile();
    }
}
