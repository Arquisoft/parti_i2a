Feature: Being able to configure different parameters for the app
Scenario: Admin can introduce new categories /
    When the admin writes a new category /
    And press the accept button /
    Then the new category will be added to the database /
    And there will be a new category to choose from in the proposals

Scenario: Admin can introduce new non-allowed words /
    When the admin writes non-allowed words /
    And press the accept button /
    Then the new words will be added to the database /
    And they won't be allowed to be used in the proposals
    
Scenario: Admin can delete non-appropiate proposals /
    When the admin writes the id of a proposal /
    And press the accept button /
    Then the proposal will be deleted    