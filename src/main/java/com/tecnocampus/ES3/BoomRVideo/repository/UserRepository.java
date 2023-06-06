package com.tecnocampus.ES3.BoomRVideo.repository;

import com.tecnocampus.ES3.BoomRVideo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
