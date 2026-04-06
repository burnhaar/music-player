package com.musicplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ===== Get all users =====
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ===== Get user by username =====
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ===== Get user by ID =====
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // ===== Register a new user =====
    public String registerUser(User newUser) {
        if (newUser.getUsername() == null || newUser.getUsername().isBlank()) {
            return "Username is required.";
        }
        if (newUser.getEmail() == null || newUser.getEmail().isBlank()) {
            return "Email is required.";
        }
        if (newUser.getPassword() == null || newUser.getPassword().isBlank()) {
            return "Password is required.";
        }
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return "Username already taken.";
        }
        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            return "Email already in use.";
        }

        // NOTE: In production, hash the password here (e.g. BCrypt) before saving
        userRepository.save(newUser);
        return "Registration successful.";
    }

    // ===== Login =====
    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "User not found.";

        // NOTE: In production, use BCrypt.matches() here instead of plain equals
        if (!user.getPassword().equals(password)) return "Incorrect password.";

        return "Login successful";

    }

    // ===== Update user profile =====
    public String updateUser(String username, User updatedUser) {
        User existing = userRepository.findByUsername(username);
        if (existing == null) return "User not found.";

        existing.setFirstName(updatedUser.getFirstName());
        existing.setLastName(updatedUser.getLastName());
        existing.setEmail(updatedUser.getEmail());

        // Only update password if a new one is provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            // NOTE: Hash before saving in production
            existing.setPassword(updatedUser.getPassword());
        }

        userRepository.save(existing);
        return "Profile updated.";
    }

    // ===== Delete a user =====
    public String deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "User not found.";
        userRepository.deleteById(user.getId());
        return "User " + username + " deleted.";
    }

    public String updateUsername(String oldUsername, String newUsername) {
    if (userRepository.findByUsername(newUsername) != null) return "Username already taken.";
    User user = userRepository.findByUsername(oldUsername);
    if (user == null) return "User not found.";
    user.setUsername(newUsername);
    userRepository.save(user);
    return "Username updated.";
}

public List<String> getFriends(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) return new ArrayList<>();
    return user.getFriends() != null ? user.getFriends() : new ArrayList<>();
}
}