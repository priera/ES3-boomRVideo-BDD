package com.tecnocampus.ES3.BoomRVideo.functional;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class FunctionalTests extends SpringFunctionalTesting {
}
