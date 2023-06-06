package com.tecnocampus.ES3.BoomRVideo.functional;

import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

public class FeatureStepDefs extends SpringFunctionalTesting {


    @When("we query for the movie with id {int}")
    public void weQueryForTheMovieWithId(int id) {

    }
}
