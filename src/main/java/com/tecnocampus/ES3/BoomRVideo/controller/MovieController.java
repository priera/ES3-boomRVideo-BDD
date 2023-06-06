package com.tecnocampus.ES3.BoomRVideo.controller;

import com.tecnocampus.ES3.BoomRVideo.model.Movie;
import com.tecnocampus.ES3.BoomRVideo.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Movie>> all(@RequestParam(required = false) String name) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(repository.findAll(), status);
    }

    @GetMapping("/{id}")
    ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return null;
    }

    @PutMapping("/{id}")
    ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Movie> deleteById(@PathVariable Long id) {
        return null;
    }

}
