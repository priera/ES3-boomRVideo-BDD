package com.tecnocampus.ES3.BoomRVideo.configuration;

import com.tecnocampus.ES3.BoomRVideo.model.Movie;
import com.tecnocampus.ES3.BoomRVideo.model.User;
import com.tecnocampus.ES3.BoomRVideo.repository.MovieRepository;
import com.tecnocampus.ES3.BoomRVideo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initMoviesDatabase(MovieRepository movieRepository, UserRepository userRepository) {

        return args -> {
            final var users = new ArrayList<>(Arrays.asList(
                    new User("Bilbo Bolsón"),
                    new User("DaReviewer"),
                    new User("Viu el cine"),
                    new User("JuanGabriel_1975"),
                    new User("Sin Cinema No Hay Paradiso")
            ));
            users.forEach(user -> log.info("Preloading " + userRepository.save(user)));

            final var movies = new ArrayList<>(Arrays.asList(
                    new Movie("The Shawshank Redemption", 1994, "Drama"),
                    new Movie("Pulp Fiction", 1994, "Crime"),
                    new Movie("The Godfather", 1972, "Crime"),
                    new Movie("The Empire Strikes Back", 1980, "Science Fiction"),
                    new Movie("Goodfellas", 1990, "Crime"),
                    new Movie("Raiders of the Lost Ark", 1981, "Action"),
                    new Movie("Back to the Future", 1985, "Science Fiction"),
                    new Movie("The Terminator", 1984, "Science Fiction"),
                    new Movie("Jurassic Park", 1993, "Science Fiction"),
                    new Movie("Fight Club", 1999, "Drama")));

            movies.forEach(movie -> log.info("Preloading " + movieRepository.save(movie)));
        };
    }
}
