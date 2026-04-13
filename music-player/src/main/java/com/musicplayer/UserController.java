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

    // ================= REGISTER NEW USER =================
    @PostMapping("/register")
    public String registerUser(@RequestBody User newUser) {
        return userService.registerUser(newUser);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String loginUser(@RequestBody User loginUser) {
        return userService.loginUser(loginUser.getUsername(), loginUser.getPassword());
    }

    // Change username
    @PutMapping("/{username}/username")
    public String updateUsername(@PathVariable String username, @RequestBody Map<String, String> body) {
        return userService.updateUsername(username, body.get("newUsername"));
    }

    // Get friends list
    @GetMapping("/{username}/friends")
    public List<String> getFriends(@PathVariable String username) {
        return userService.getFriends(username);
    }

    // Favorite/unfavorite a song for a user
    @PatchMapping("/{username}/favorites/{songId}")
    public String favoriteSong(@PathVariable String username, @PathVariable String songId,
            @RequestParam boolean isFavorite) {
        if (isFavorite) {
            return songService.favoriteSong(username, songId);
        } else {
            return songService.unfavoriteSong(username, songId);
        }
    }
}