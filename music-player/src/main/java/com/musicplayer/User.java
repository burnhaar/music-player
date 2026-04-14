package com.musicplayer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

@Document(collection = "users")

public class User implements Comparable<User> {
    // ============ INSTANCE PROPERTIES ============
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private ArrayList<String> favoritedSongIds;
    private ArrayList<String> playlistIds;
    private ArrayList<String> friends;
    private ArrayList<String> followers;
    private ArrayList<String> following;
    private ArrayList<String> pendingRequests; // usernames who have requested to follow this user
    private boolean isPrivate;

    // ============ CONSTRUCTORS ============
    public User() {
    }

    public User(String id, String firstName, String lastname, String username, String email, String password,
    ArrayList<String> favoritedSongIds, ArrayList<String> playlistIds, ArrayList<String> friends) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastname);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setFavoritedSongIds(favoritedSongIds);
        this.setPlaylistIds(playlistIds);
        this.setFriends(friends);
    }

    // ============ METHODS ============
    @Override
    public String toString() {
        return String.format(
            "User{id='%s', username='%s', email='%s', favoritedSongs='%s', playlists='%s'}",
            id, username, email,
            favoritedSongIds != null ? favoritedSongIds.size() + " songs" : "none",
            playlistIds != null ? playlistIds.size() + " playlists" : "none"
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User u = (User) obj;
        return this.getId().equals(u.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(User other) {
        return this.getLastName().compareTo(other.lastName);
    }

    // ============ GETTERS AND SETTERS ============
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public ArrayList<String> getFavoritedSongIds() { return favoritedSongIds; }
    public void setFavoritedSongIds(ArrayList<String> favoritedSongIds) { this.favoritedSongIds = favoritedSongIds; }

    public ArrayList<String> getPlaylistIds() { return playlistIds; }
    public void setPlaylistIds(ArrayList<String> playlistIds) { this.playlistIds = playlistIds; }

    public ArrayList<String> getFriends() { return friends; }
    public void setFriends(ArrayList<String> friends) { this.friends = friends; }

    public ArrayList<String> getFollowers() { return followers; }
    public void setFollowers(ArrayList<String> followers) { this.followers = followers; }

    public ArrayList<String> getFollowing() { return following; }
    public void setFollowing(ArrayList<String> following) { this.following = following; }

    public ArrayList<String> getPendingRequests() { return pendingRequests; }
    public void setPendingRequests(ArrayList<String> pendingRequests) { this.pendingRequests = pendingRequests; }

    public boolean isPrivate() { return isPrivate; }
    public void setPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }
}
