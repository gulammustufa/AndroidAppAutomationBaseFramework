Feature: Text test cases

  Scenario: API Demos > Text > LogTextBox: Verify that text is visible after clicking on Add button
    When API Demos: User clicks on "Text" field
    And API Demos > Text: User clicks on "LogTextBox" field
    And API Demos > Text > LogTextBox: User clicks on Add button 1 times
    Then API Demos > Text > LogTextBox: Text should be visible 1 times

  Scenario: API Demos > Text > LogTextBox: Verify that text is visible after clicking on Add button second
    When API Demos: User clicks on "Text" field
    And API Demos > Text: User clicks on "LogTextBox" field
    And API Demos > Text > LogTextBox: User clicks on Add button 2 times
    Then API Demos > Text > LogTextBox: Text should be visible 3 times

  Scenario: API Demos > Text > LogTextBox: Verify that text is visible after clicking on Add button third
    When API Demos: User clicks on "Text" field
    And API Demos > Text: User clicks on "LogTextBox" field
    And API Demos > Text > LogTextBox: User clicks on Add button 3 times
    Then API Demos > Text > LogTextBox: Text should be visible 3 times
