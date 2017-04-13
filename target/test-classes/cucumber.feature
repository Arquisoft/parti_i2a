Feature: application running, recieves the initial data and connects with kafka
  The data should be load when the application starts
  Scenario: Start application database connection
    When the database gets a connection
    Then the application gets the proposal
    And the list is not empty