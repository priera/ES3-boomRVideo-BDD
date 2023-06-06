package com.tecnocampus.ES3.BoomRVideo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "UserTable")
public class User {

    private @Id
    @GeneratedValue Long id;

    private String name;

    double score = 1.0;

    @ManyToMany
    @JoinTable(joinColumns = { @JoinColumn(name = "user_id")})
    private List<Movie> favoriteMovies;

    @OneToMany(mappedBy = "author")
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Review> reviews;

    User() {}

    public User(String name) {
        this.name = name;
        this.favoriteMovies = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(List<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        var favoriteMovies = this.favoriteMovies.stream().map(Movie::getName).toList();
        var reviewCount = this.reviews.size();

        return "User{" + "id=" + this.id +
                ", name='" + this.name + "'"
                + ", score='" + this.score + "'"
                + ", favoriteMovies=" + favoriteMovies
                + ", reviews=" + reviewCount
                + '}';
    }
}
