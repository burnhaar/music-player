package com.musicplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    // ================= GET ALL USERS =================
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    

    // ================= GET USER BY USERNAME =================
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // ================= SEARCH USERS =================
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String query) {
        return userService.searchUsers(query);
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public String registerUser(@RequestBody User newUser) {
        return userService.registerUser(newUser);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String loginUser(@RequestBody User loginUser) {
        return userService.loginUser(loginUser.getUsername(), loginUser.getPassword());
    }

    // ================= UPDATE USERNAME =================
    @PutMapping("/{username}/username")
    public String updateUsername(@PathVariable String username, @RequestBody Map<String, String> body) {
        return userService.updateUsername(username, body.get("newUsername"));
    }

    // ================= GET FRIENDS =================
    @GetMapping("/{username}/friends")
    public List<String> getFriends(@PathVariable String username) {
        return userService.getFriends(username);
    }

    // ================= FOLLOW / SEND REQUEST =================
    @PostMapping("/{username}/follow/{targetUsername}")
    public String followUser(@PathVariable String username, @PathVariable String targetUsername) {
        return userService.sendFollowRequest(username, targetUsername);
    }

    // ================= UNFOLLOW =================
    @DeleteMapping("/{username}/follow/{targetUsername}")
    public String unfollowUser(@PathVariable String username, @PathVariable String targetUsername) {
        return userService.unfollow(username, targetUsername);
    }

    // ================= APPROVE FOLLOW REQUEST =================
    @PostMapping("/{username}/requests/approve/{requester}")
    public String approveRequest(@PathVariable String username, @PathVariable String requester) {
        return userService.approveFollowRequest(username, requester);
    }

    // ================= REJECT FOLLOW REQUEST =================
    @DeleteMapping("/{username}/requests/reject/{requester}")
    public String rejectRequest(@PathVariable String username, @PathVariable String requester) {
        return userService.rejectFollowRequest(username, requester);
    }

    // ================= GET PENDING REQUESTS =================
    @GetMapping("/{username}/requests")
    public List<String> getPendingRequests(@PathVariable String username) {
        return userService.getPendingRequests(username);
    }

    // ================= SET PRIVACY =================
    @PatchMapping("/{username}/privacy")
    public String setPrivacy(@PathVariable String username, @RequestParam boolean isPrivate) {
        return userService.setPrivacy(username, isPrivate);
    }

    // ================= FAVORITE A SONG =================
    @PatchMapping("/{username}/favorites/{songId}")
    public String favoriteSong(@PathVariable String username, @PathVariable String songId,
                                @RequestParam boolean isFavorite) {
        if (isFavorite) return songService.favoriteSong(username, songId);
        else return songService.unfavoriteSong(username, songId);
    }
}
