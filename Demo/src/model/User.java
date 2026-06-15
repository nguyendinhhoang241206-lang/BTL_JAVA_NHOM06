package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import model.enums.Gender;
import model.enums.Role;
import model.enums.UserStatus;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private Role role;
    private UserStatus status;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate birthday;
    private List<String> favoriteMovieIds;

    public User() {
    }

    public User(String id, String username, String password, Role role, UserStatus status, 
                String email, String phone, Gender gender, LocalDate birthday, List<String> favoriteMovieIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.favoriteMovieIds = favoriteMovieIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<String> getFavoriteMovieIds() {
        return favoriteMovieIds;
    }

    public void setFavoriteMovieIds(List<String> favoriteMovieIds) {
        this.favoriteMovieIds = favoriteMovieIds;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", favoriteMovieIds=" + favoriteMovieIds +
                '}';
    }
}
