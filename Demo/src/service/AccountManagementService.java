package service;

import dao.UserDAO;
import model.User;
import model.enums.Role;
import model.enums.UserStatus;
import java.util.ArrayList;
import java.util.List;

public class AccountManagementService {
    private UserDAO userDAO;

    public AccountManagementService() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers() {
        return userDAO.readFromFile();
    }

    public List<User> getAdminRequests() {
        List<User> requests = new ArrayList<>();
        for (User u : userDAO.readFromFile()) {
            if (u.isRequestedAdmin()) {
                requests.add(u);
            }
        }
        return requests;
    }

    public boolean grantAdmin(String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) return false;
        user.setRole(Role.ADMIN);
        user.setRequestedAdmin(false);
        return userDAO.update(user);
    }

    public boolean toggleUserStatus(String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) return false;
        
        if (user.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException("Không thể khóa tài khoản Admin!");
        }
        
        if (user.getStatus() == UserStatus.ACTIVE) {
            user.setStatus(UserStatus.LOCKED);
        } else {
            user.setStatus(UserStatus.ACTIVE);
        }
        return userDAO.update(user);
    }
}
