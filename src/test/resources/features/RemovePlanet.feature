@SR2 @NeedsLogout
Feature: Planet Deletion
  As a user I want to remove a planet from the Planetarium so I can update my findings

  Background: Planet Removal Starting Action
    Given the user is in the home page
    And Planet is selected  

  
  Scenario: Sucessful removal of a planet
    When the user provides name "Earth"
    And Clicks on Delete
    Then the table refreshes
    And the planet "Earth" is off the table
    And no moon with owner "1" is present in the table
  
  @PR4
  Scenario Outline: Unsucesful removal of a planet
    When the user provides name "Jupiter"
    And Clicks on Delete
    Then the user should get a browser alert saying "Invalid planet name"
