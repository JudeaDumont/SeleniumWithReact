Feature: LoginFeature
  This feature deals with the login functionality of the applications

  Scenario: Login with correct username and password
    Given I navigate to Angular
    And I enter a name
      | name |
      | chef |
    And I click the add button
    #Then I should see an additional candidate
#
#  Scenario: Login with correct username and password to fail
#    Given I navigate to the login page
#    And I enter the following for Login
#      | username | password      |
#      | admin    | adminpassword |
#    And I click login button
#    Then I should see the userform page wrongly
