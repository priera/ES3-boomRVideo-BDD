package com.tecnocampus.ES3.BoomRVideo.controller;

import com.tecnocampus.ES3.BoomRVideo.Application;
import com.tecnocampus.ES3.BoomRVideo.JsonUtil;
import com.tecnocampus.ES3.BoomRVideo.model.User;
import com.tecnocampus.ES3.BoomRVideo.model.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void canReadRepository() throws Exception {
        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getExistingUserIsOk() throws Exception {
        mvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
                .andDo((print()))
                .andExpect(status().isFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Bilbo Bolsón")));
    }

    @Test
    public void createNewUserIsOk() throws Exception {
        var testUser = new User("Legolas");

        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(testUser)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(testUser.getName())));
    }

    @Test
    public void getNonExistingUserIsNotOk() throws Exception {
        mvc.perform(get("/users/100").contentType(MediaType.APPLICATION_JSON))
                .andDo((print()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingUserIsOk() throws Exception {
        mvc.perform(delete("/users/3").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingUserIsNotOk() throws Exception {
        mvc.perform(delete("/users/300").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findExistingUserByNameIsOk() throws Exception {
        // Returns the first user with a matching name

        // in the controller: you can have different functions which reply to a GET
        // just put a different mapping in each one of them

        mvc.perform(get("/users/name/Bilbo Bolsón").contentType(MediaType.APPLICATION_JSON))
                .andDo((print()))
                .andExpect(status().isFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Bilbo Bolsón")));
    }

    @Test
    public void canAddANewReview() throws Exception {
        Review r = new Review(3, "Wonderful movvie!!");

        // Bilbo Bolsón (user 1) adding a review for Goodfellas (movie 5)

        // In the mapping function, add the following parameter: @RequestHeader("movieId") int movieId
        mvc.perform(post("/users/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(r))
                        .header("movieId", 5))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
