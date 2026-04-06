package com.musicplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    // ===== Get all songs =====
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // ===== Get song by ID =====
    public Optional<Song> getSongById(String id) {
        return songRepository.findById(id);
    }

    // ===== Get songs by artist =====
    public ArrayList<Song> getSongsByArtist(String artist) {
        return songRepository.findByArtist(artist);
    }

    // ===== Get songs by genre =====
    public ArrayList<Song> getSongsByGenre(String genre) {
        return songRepository.findByGenre(genre);
    }

    // ===== Get songs by mood =====
    public ArrayList<Song> getSongsByMood(String mood) {
        return songRepository.findByMood(mood);
    }

    // ===== Get songs by title =====
    public ArrayList<Song> getSongsByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    // ===== Add a new song =====
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    // ===== Update an existing song =====
    public Song updateSong(String id, Song updatedSong) {
        return songRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedSong.getTitle());
                    existing.setArtist(updatedSong.getArtist());
                    existing.setGenre(updatedSong.getGenre());
                    existing.setMood(updatedSong.getMood());
                    existing.setDateReleased(updatedSong.getDateReleased());
                    existing.setImageBase64(updatedSong.getImageBase64());
                    return songRepository.save(existing);
                })
                .orElseGet(() -> {
                    updatedSong.setId(id);
                    return songRepository.save(updatedSong);
                });
    }

    // ===== Delete a song =====
    public String deleteSong(String id) {
        songRepository.deleteById(id);
        return "Song " + id + " deleted.";
    }

    // ===== Get all favorited songs for a user =====
    public List<Song> getFavoritedSongs(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null || user.getFavoritedSongIds() == null) {
            return new ArrayList<>();
        }
        return user.getFavoritedSongIds().stream()
                .map(id -> songRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    // ===== Favorite a song (add to user's favoritedSongIds) =====
    public String favoriteSong(String username, String songId) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "User not found.";
        if (!songRepository.existsById(songId)) return "Song not found.";

        if (user.getFavoritedSongIds() == null) {
            user.setFavoritedSongIds(new ArrayList<>());
        }
        if (!user.getFavoritedSongIds().contains(songId)) {
            user.getFavoritedSongIds().add(songId);
            userRepository.save(user);
        }
        return "Song favorited.";
    }

    // ===== Unfavorite a song =====
    public String unfavoriteSong(String username, String songId) {
        User user = userRepository.findByUsername(username);
        if (user == null) return "User not found.";

        if (user.getFavoritedSongIds() != null) {
            user.getFavoritedSongIds().remove(songId);
            userRepository.save(user);
        }
        return "Song unfavorited.";
    }

    // ===== Update favorite =====
    public Song updateFavorite(String id, boolean isFavorite) {
        Song song = songRepository.findById(id).orElseThrow();
        song.setIsFavorite(isFavorite);
        return songRepository.save(song);
    }
}