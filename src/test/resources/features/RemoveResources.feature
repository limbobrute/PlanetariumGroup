Feature: Planet and Moon Removal
  As a user I want to remove planets and moons from the Planetarium so I can correct my findings

  Background: Removing resource starting action
    Given the user is logged in

  Scenario Outline: User removes a resource that exists in the database
    When the user removes a <entity type> with name "<name>"
    Then the <entity type> "<name>" should be removed from the database successfully
    And all associated moons should be removed if it is a planet "<entity type>"
    Examples:
      |entity type   | name |
      |planet   | Earth |
      |moon   | Titan |

  Scenario Outline: User attempts to remove a resource that is not in the database
    When the user removes a <entity type> with name "<name>"
    Then  the user should get a browser alert saying Invalid "<entity type>" name
    Examples:
       |entity type   | name |
       |planet   | NotRealPlanet |
       |moon   | NotRealMoon |

