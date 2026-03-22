package com.musicplayer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")

public class Song implements Comparable<Song> {
    // ============ INSTANCE PROPERTIES ============
    @Id
    private String id;
    private String title;
    private String artist;
    private String genre;
    private String mood;
    private String dateReleased;
    private String imageBase64;

    // ============ CONSTRUCTORS ============

    /**
     * Empty Constructor
     */
    public Song() {
    }

    /**
     * Workhorse Constructor
     * 
     * @param title
     * @param artist
     * @param genre
     * @param mood
     * @param dateReleased
     * @param imageBased64
     */
    public Song(String title, String artist, String genre, String mood, String dateReleased, String imageBase64) {
        this.setTitle(title);
        this.setArtist(artist);
        this.setGenre(genre);
        this.setMood(mood);
        this.setDateReleased(dateReleased);
        this.setImageBase64(imageBase64);
    }

    public Song(String title, String artist, String genre, String mood) {
    this.title = title;
    this.artist = artist;
    this.genre = genre;
    this.mood = mood;
}

    // ============ METHODS ============
    /**
     * This method formats the song into a neat String
     * 
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format(
                "Songs{id='%s', title='%s', artist='%s', genre='%s', mood='%s', dateReleased='%s', imageBase64='%s'}",
                id,
                title,
                artist,
                genre,
                mood,
                dateReleased,
                imageBase64 != null ? "image present" : "no image");
    }

    /**
     * This method checks if two songs are equal to each other
     *
     * @param Song you are checking
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Song)) {
            return false;
        }

        Song s = (Song) obj;
        return this.getId().equals(s.id);
    }

    /**
     * This method converts the object to a number
     * 
     * @return the hash value
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * This method checks the relationship between two songs
     * This is done in order to put them in alphabetical order
     * 
     * @param Song you are checking
     * @return location of the Song being searched
     */
    @Override
    public int compareTo(Song other) {
        return this.getTitle().compareTo(other.title);
    }

    // ============ GETTERS & SETTERS ============
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(String dateReleased) {
        this.dateReleased = dateReleased;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

}
