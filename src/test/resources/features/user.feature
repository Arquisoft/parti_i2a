Feature: Being able to participation system
Scenario: User can make proposals /
    When the user writes a proposal /
    And selects a category / 
    And press the add proposal button/
    Then the new proposal will be added to the database 

Scenario: User can view and vote proposals/
    When the user clicks on view proposals /
    Then he can vote each one of them /
    And see their details
    
Scenario: User can comment proposals and vote comments/
    When the user clicks on view details of a proposal /
    Then he can comment it/
    And see its comments/
    And vote the comments/
    And order the comments
    