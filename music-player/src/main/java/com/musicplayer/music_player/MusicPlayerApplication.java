package com.musicplayer.music_player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.musicplayer", "com.musicplayer.music_player", "com.musicapp.musicapp"})
@EnableMongoRepositories(basePackages = "com.musicplayer")
public class MusicPlayerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerApplication.class, args);
    }
}