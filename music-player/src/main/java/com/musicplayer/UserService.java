package com.musicplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (newUser.getUsername() == null || newUser.getUsername().isBlank()) return "Username is required.";
        if (newUser.getEmail() == null || newUser.getEmail().isBlank()) return "Email is required.";
        if (newUser.getPassword() == null || newUser.getPassword().isBlank()) return "Password is required.";
        if (userRepository.findByUsername(newUser.getUsername()) != null) return "Username already taken.";
        if (userRepository.findByEmail(newUser.getEmail()) != null) return "Email already in use.";
        userRepository.save(newUser);
        return "Registration successful.";
    }

    // ===== Login =====
    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "User not found.";
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
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
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

    // ===== Update username =====
    public String updateUsername(String oldUsername, String newUsername) {
        if (userRepository.findByUsername(newUsername) != null) return "Username already taken.";
        User user = userRepository.findByUsername(oldUsername);
        if (user == null) return "User not found.";
        user.setUsername(newUsername);
        userRepository.save(user);
        return "Username updated.";
    }

    // ===== Get friends =====
    public List<String> getFriends(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return new ArrayList<>();
        return user.getFriends() != null ? user.getFriends() : new ArrayList<>();
    }

    // ===== Search users =====
    public List<User> searchUsers(String query) {
        return userRepository.findAll().stream()
            .filter(u -> u.getUsername() != null &&
                u.getUsername().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    // ===== Send follow request =====
    public String sendFollowRequest(String fromUsername, String toUsername) {
        if (fromUsername.equals(toUsername)) return "You cannot follow yourself.";
        User fromUser = userRepository.findByUsername(fromUsername);
        User toUser = userRepository.findByUsername(toUsername);
        if (fromUser == null || toUser == null) return "User not found.";

        // Initialize lists
        if (toUser.getFollowers() == null) toUser.setFollowers(new ArrayList<>());
        if (toUser.getPendingRequests() == null) toUser.setPendingRequests(new ArrayList<>());
        if (fromUser.getFollowing() == null) fromUser.setFollowing(new ArrayList<>());

        // Already following
        if (toUser.getFollowers().contains(fromUsername)) return "Already following.";
        // Already requested
        if (toUser.getPendingRequests().contains(fromUsername)) return "Request already sent.";

        if (!toUser.isPrivate()) {
            // Public account — auto approve
            toUser.getFollowers().add(fromUsername);
            fromUser.getFollowing().add(toUsername);
            userRepository.save(toUser);
            userRepository.save(fromUser);
            return "Now following.";
        } else {
            // Private account — send request
            toUser.getPendingRequests().add(fromUsername);
            userRepository.save(toUser);
            return "Follow request sent.";
        }
    }

    // ===== Approve follow request =====
    public String approveFollowRequest(String username, String requesterUsername) {
        User user = userRepository.findByUsername(username);
        User requester = userRepository.findByUsername(requesterUsername);
        if (user == null || requester == null) return "User not found.";

        if (user.getPendingRequests() == null || !user.getPendingRequests().contains(requesterUsername))
            return "No pending request from this user.";

        if (user.getFollowers() == null) user.setFollowers(new ArrayList<>());
        if (requester.getFollowing() == null) requester.setFollowing(new ArrayList<>());

        user.getPendingRequests().remove(requesterUsername);
        user.getFollowers().add(requesterUsername);
        requester.getFollowing().add(username);

        userRepository.save(user);
        userRepository.save(requester);
        return "Follow request approved.";
    }

    // ===== Reject follow request =====
    public String rejectFollowRequest(String username, String requesterUsername) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "User not found.";
        if (user.getPendingRequests() != null) {
            user.getPendingRequests().remove(requesterUsername);
            userRepository.save(user);
        }
        return "Follow request rejected.";
    }

    // ===== Unfollow =====
    public String unfollow(String fromUsername, String toUsername) {
        User fromUser = userRepository.findByUsername(fromUsername);
        User toUser = userRepository.findByUsername(toUsername);
        if (fromUser == null || toUser == null) return "User not found.";

        if (fromUser.getFollowing() != null) fromUser.getFollowing().remove(toUsername);
        if (toUser.getFollowers() != null) toUser.getFollowers().remove(fromUsername);
        // Also cancel pending request if exists
        if (toUser.getPendingRequests() != null) toUser.getPendingRequests().remove(fromUsername);

        userRepository.save(fromUser);
        userRepository.save(toUser);
        return "Unfollowed.";
    }

    // ===== Toggle privacy =====
    public String setPrivacy(String username, boolean isPrivate) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "User not found.";
        user.setPrivate(isPrivate);
        userRepository.save(user);
        return isPrivate ? "Account set to private." : "Account set to public.";
    }

    // ===== Get pending requests =====
    public List<String> getPendingRequests(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return new ArrayList<>();
        return user.getPendingRequests() != null ? user.getPendingRequests() : new ArrayList<>();
    }

    // ===== Old friend methods (kept for compatibility) =====
    public String addFriend(String username, String friendUsername) {
        return sendFollowRequest(username, friendUsername);
    }

    public String removeFriend(String username, String friendUsername) {
        return unfollow(username, friendUsername);
    }
}
