Feature: Text test cases

  Scenario: API Demos > Text > LogTextBox: Verify that text is visible after clicking on Add button
    When API Demos: User clicks on "Text" field
    And API Demos > Text: User clicks on "LogTextBox" field
    And API Demos > Text > LogTextBox: User clicks on Add button
    Then API Demos > Text > LogTextBox: Text should be visible as "This is a test"

  Scenario: API Demos > Text > LogTextBox: Verify that text is visible after clicking on Add button second
    When API Demos: User clicks on "Text" field
    And API Demos > Text: User clicks on "LogTextBox" field
    And API Demos > Text > LogTextBox: User clicks on Add button
    Then API Demos > Text > LogTextBox: Text should be visible as "This is a test"

  Scenario: API Demos > Text > LogTextBox: Verify that text is visible after clicking on Add button third
    When API Demos: User clicks on "Text" field
    And API Demos > Text: User clicks on "LogTextBox" field
    And API Demos > Text > LogTextBox: User clicks on Add button
    Then API Demos > Text > LogTextBox: Text should be visible as "This is a test"
