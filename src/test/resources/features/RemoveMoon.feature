@SR2 @MR4 @MR5 @NeedsLogout
Feature: Moon Deletion
  As a user I want to remove a moon from the Planetarium so I can update my findings

  Background: Moon Removal Starting Action
    Given the user is in the home page
    And Moon is selected
  
  Scenario: Successful removal of a moon
    When the user provides name "Luna"
    And Clicks on Delete
    Then the table refreshes
    And the moon "Luna" is off the table

    Scenario: Unsuccessful removal of a moon
      When the user provides name "Europa"
      And Clicks on Delete
      Then the user should get a browser alert saying "Invalid moon name"