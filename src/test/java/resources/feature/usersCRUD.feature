Feature: Gorest Application

  As a user I want to test student Application

  Scenario Outline: CRUD Test
    Given Gorest application is running
    When I create a new student by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then I verify that user is created with "<name>"
    And I update user with usertId name "<name>" email "<email>" gender "<gender>" status "<status>"
    And U check User is update successfully
    And I delete user with userId
    Then I verify that user is deleted successfully

    Examples:
      | name  | email              | gender | status |
      | prime | prime123@gmail.com | male   | active |

