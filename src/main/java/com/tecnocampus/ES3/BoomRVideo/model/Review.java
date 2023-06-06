package com.tecnocampus.ES3.BoomRVideo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class Review {

    private @Id
    @GeneratedValue Long id;

    private int score;
    private String text;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private User author;

    Review() { }

    public Review(int score, String text) {
        this.score = score;
        this.text = text;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
