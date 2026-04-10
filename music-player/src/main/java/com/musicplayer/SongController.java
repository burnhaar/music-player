package com.musicplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    private SongService songService;

    // ===== Get all songs =====
    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    // ===== Get song by ID =====
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable String id) {
        return songService.getSongById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }  // ← this closing brace was missing

    // ===== Get songs by artist =====
    @GetMapping("/artist/{artist}")
    public ArrayList<Song> getByArtist(@PathVariable String artist) {
        return songService.getSongsByArtist(artist);  // ← fixed
    }

    // ===== Get songs by genre =====
    @GetMapping("/genre/{genre}")
    public ArrayList<Song> getByGenre(@PathVariable String genre) {
        return songService.getSongsByGenre(genre);  // ← fixed
    }

    // ===== Get songs by mood =====
    @GetMapping("/mood/{mood}")
    public ArrayList<Song> getByMood(@PathVariable String mood) {
        return songService.getSongsByMood(mood);  // ← fixed
    }

    // ===== Get songs by title =====
    @GetMapping("/title/{title}")
    public ArrayList<Song> getByTitle(@PathVariable String title) {
        return songService.getSongsByTitle(title);
    }

    // ===== Add a new song =====
    @PostMapping
    public Song addSong(@RequestBody Song newSong) {
        return songService.addSong(newSong);  // ← fixed
    }

    // ===== Update a song =====
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable String id, @RequestBody Song updatedSong) {
        return ResponseEntity.ok(songService.updateSong(id, updatedSong));
    }

    // ===== Delete a song =====
    @DeleteMapping("/{id}")
    public String deleteSong(@PathVariable String id) {
        return songService.deleteSong(id);
    }

    // ===== Favorite a Song =====
    @PatchMapping("/{id}/favorite")
    public ResponseEntity<Song> updateFavorite(@PathVariable String id, @RequestParam boolean isFavorite) {
    return ResponseEntity.ok(songService.updateFavorite(id, isFavorite));
    }

    // Favorite a song for a user
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
