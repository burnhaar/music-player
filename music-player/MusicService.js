const express = require('express');
const cors = require('cors');
const app = express();

app.use(cors()); 

app.use(express.json());

class MusicService {
    constructor() {
        // This array now matches your MongoDB collection exactly
        this.songs = [
            { title: 'Billie Jean', artist: 'Michael Jackson', genre: 'Pop', mood: 'Energetic', dateReleased: '1983-01-02', imageBase64: '' },
            { title: 'Bohemian Rhapsody', artist: 'Queen', genre: 'Pop', mood: 'Energetic', dateReleased: '1975-10-31', imageBase64: '' },
            { title: 'Take Me Home, Country Roads', artist: 'John Denver', genre: 'Country', mood: 'Calm', dateReleased: '1971-04-12', imageBase64: '' },
            { title: 'Not Like Us', artist: 'Kendrick Lamar', genre: 'Rap', mood: 'Energetic', dateReleased: '2024-05-04', imageBase64: '' }
            // ... you can add the rest of your MongoDB list here
        ];
        this.currentSongIndex = 0;
    }

    getPlaylist() {
        return this.songs;
    }

    // New "Pro" features since you have genres and moods:
    getSongsByGenre(genre) {
        return this.songs.filter(song => song.genre === genre);
    }

    getSongsByMood(mood) {
        return this.songs.filter(song => song.mood === mood);
    }

    getNextSong() {
        this.currentSongIndex = (this.currentSongIndex + 1) % this.songs.length;
        return this.songs[this.currentSongIndex];
    }
}

const musicService = new MusicService();