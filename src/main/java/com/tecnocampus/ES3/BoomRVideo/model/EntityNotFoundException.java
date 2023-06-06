package com.tecnocampus.ES3.BoomRVideo.model;

public class EntityNotFoundException extends RuntimeException {
    Long id;

    public EntityNotFoundException(Long id) {
        super("Entity with Id: " + id + " not found");
        this.id = id;
    }
}
