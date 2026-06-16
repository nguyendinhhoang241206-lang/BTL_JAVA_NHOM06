package model;

import java.io.Serializable;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private int rating;
    private String comment;
    private String userId;
    private String movieId;

    public Review() {
    }

    public Review(String id, int rating, String comment, String userId, String movieId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.movieId = movieId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", userId='" + userId + '\'' +
                ", movieId='" + movieId + '\'' +
                '}';
    }
}
