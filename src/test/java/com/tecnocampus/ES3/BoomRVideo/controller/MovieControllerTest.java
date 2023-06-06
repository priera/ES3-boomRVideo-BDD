package com.tecnocampus.ES3.BoomRVideo.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tecnocampus.ES3.BoomRVideo.Application;
import com.tecnocampus.ES3.BoomRVideo.JsonUtil;
import com.tecnocampus.ES3.BoomRVideo.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void canReadRepository() throws Exception {
        mvc.perform(get("/movies").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(9))));
    }

    @Test
    public void getExistingMovieIsOk() throws Exception {
        mvc.perform(get("/movies/1").contentType(MediaType.APPLICATION_JSON))
                .andDo((print()))
                .andExpect(status().isFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("The Shawshank Redemption")));
    }

    @Test
    public void createNewMovieIsOk() throws Exception {
        var testMovie = new Movie("jaws", 1975, "action");

        mvc.perform(post("/movies").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(testMovie)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(testMovie.getName())));
    }

    @Test
    public void getNonExistingMovieIsNotOk() throws Exception {
        mvc.perform(get("/movies/100").contentType(MediaType.APPLICATION_JSON))
                .andDo((print()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingMovieIsOk() throws Exception {
        var testMovie = new Movie("Pulp Fiction", 1995, "Crime");

        mvc.perform(put("/movies/2").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(testMovie)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(testMovie.getName())))
                .andExpect(jsonPath("releaseYear", is(testMovie.getReleaseYear())));

    }

    @Test
    public void updateNonExistingMovieIsNotOk() throws Exception {
        var testMovie = new Movie("Pulp Fiction", 1995, "Crime");

        mvc.perform(put("/movies/200").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(testMovie)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingMovieIsOk() throws Exception {
        mvc.perform(delete("/movies/3").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingMovieIsNotOk() throws Exception {
        mvc.perform(delete("/movies/300").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
