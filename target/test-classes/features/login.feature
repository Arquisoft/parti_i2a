Feature: Being able to login
Scenario: User logs in /
   	Given the user "calleja@email.com" /
   	And password "password" /
    When the users clicks on Login /
    Then the user sees the proposal list

Scenario: Admin logs in /
   	Given the user "admin" /
   	And password "password" /
    When the admin clicks on Login /
    Then the admin is able to choose parameters for the app