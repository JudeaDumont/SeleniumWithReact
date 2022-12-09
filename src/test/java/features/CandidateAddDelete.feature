Feature: CandidateAddDeleteFeature
  This feature deals with the login functionality of the applications

  Scenario: I add a candidate, then delete the same candidate, checking the table is updated all along the way.
    Given I navigate to Angular
    And I add candidate table to history
    And I enter a name to add
      | name |
      | chef |
    And I click the add button
    And I add candidate table to history
    Then diff tables to get the new candidates ID
    And I enter the new candidates id to delete
    And I click the delete button
    And I add candidate table to history
    Then the table does not contain the candidate

