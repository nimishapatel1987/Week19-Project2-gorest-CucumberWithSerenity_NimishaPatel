Feature: Testing different request on the student application

  Scenario: Check if the gorest application can be accessed by User
    When User sends a GET request to userID endpoint
    Then User must get back a valid status code 200

  Scenario Outline: Create a new user & verify if the student is added
    When I create a new student by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then I verify that user is created with "<name>"

    Examples:
      | name | gender | email          | status |
      | pari | female | pari@gmail.com | active |


