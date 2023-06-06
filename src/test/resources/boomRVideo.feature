Feature: Users and movies can be managed

  Scenario: a movie can be queried by id
    When we query for the movie with id 4
    Then we obtain a 302 status code

  Scenario: a movie can be queried by name
    Given the movie "The Godfather"
    When we query for it
    Then we obtain a 302 status code

  Scenario: A new user can be created
    Given User "Fr0d0" does not exists
    When is created through REST call
    Then user appears as existing

  Scenario: An user can set favorite movies
    Given the user "Fr0d0"
    When adding the favorite movie "Pulp Fiction"
    And adding the favorite movie "Back to the Future"
    Then both movies appear as favorite

  Scenario: An user can be deleted
    When REST delete is done on user "Fr0d0"
    Then it cannot be retrieved anymore

  Scenario: An user can publish a review
    Given an existing user
    When publishes a review for the movie "Pulp Fiction"
    Then it appears in the movie
    And in the user

  Scenario: Publishing reviews increases the score
    Given the user "DaReviewer"
    When publishes a review for the movie "Pulp Fiction"
    Then his score increases
    And publishes a review for the movie "The Godfather"
    Then his score increases

  Scenario: The score of a movie is the average of its reviews
    Given the movie "Back to the Future"
    When an user publishes a review on it
    And another user publishes another
    Then the score of the movie is the average of the reviews
