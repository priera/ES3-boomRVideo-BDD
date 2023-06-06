package com.tecnocampus.ES3.BoomRVideo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Movie {

    private @Id
    @GeneratedValue Long id;

    private String name;
    private int releaseYear;
    private String genre;
    private double score;

    @OneToMany(mappedBy = "movie")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Review> reviews;

    Movie() {}

    public Movie(String name, int releaseYear, String genre) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + this.id +
                ", name='" + this.name + "'"
                + ", releaseYear='" + this.releaseYear + "'"
                + ", genre='" + this.genre + "'"
                + '}';
    }

}
