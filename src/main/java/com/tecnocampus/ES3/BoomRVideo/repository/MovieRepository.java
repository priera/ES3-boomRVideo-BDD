package com.tecnocampus.ES3.BoomRVideo.repository;

import com.tecnocampus.ES3.BoomRVideo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);
}
