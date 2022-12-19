Feature: CandidateAddDeleteFeature
  This feature deals with the login functionality of the applications

  Scenario: I add a candidate, then delete the same candidate, checking the table is updated all along the way.
    Given I navigate to React
    Then Verify Count P Text is "0"
    Then Verify Toggle P Text is "false"
    Then Click the Count Button
    Then Click the Toggle Button
    Then Verify Count P Text is "1"
    Then Verify Toggle P Text is "true"
    Then Click the Count Button
    Then Click the Toggle Button
    Then Verify Count P Text is "2"
    Then Verify Toggle P Text is "false"
