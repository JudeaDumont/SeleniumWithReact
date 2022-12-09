Feature: LoginFeature
  This feature deals with the login functionality of the applications

  Scenario: Login with correct username and password
    Given I navigate to Angular
    And I get existing candidates
    And I enter a name to add
      | name |
      | chef |
    And I click the add button
#    And I enter an id to delete
#      | id |
#      | chef |
#    #this is going to be pretty elaborate to get the id,
#    #diff the table data before and after add i guess.
#    And I click the delete button
