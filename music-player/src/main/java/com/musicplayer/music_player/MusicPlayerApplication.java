package com.musicplayer.music_player; 

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.musicplayer.Song;           // Import your Song class
import com.musicplayer.SongRepository; // Import your Repository

@SpringBootApplication(scanBasePackages = {"com.musicplayer"})
@EnableMongoRepositories(basePackages = "com.musicplayer")
public class MusicPlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(SongRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Song("Billie Jean", "Michael Jackson", "Pop", "Energetic"));
                repository.save(new Song("Bohemian Rhapsody", "Queen", "Pop", "Energetic"));
                repository.save(new Song("Not Like Us", "Kendrick Lamar", "Rap", "Energetic"));
                System.out.println("--- Songs Seeded! ---");
            }
        };
    }
}