# TODO add tags
Feature: User Login
  As a user I want to securely access my account so I can interact with the Planetarium in a secure environment

  Background: Login Starting Actions
    Given    the user tries to login

  Scenario: Users can register login with valid credentials
    When    the user provides a valid username for login
    And     the user provides a valid password for login
    Then    the user should be able to securely interact with the Planetarium

  Scenario Outline: Users can not login with invalid credentials
    When    the user provides login username "<username>"
    And     the user provides login password "<password>"
    Then    the user should get a browser alert saying Invalid credentials

    Examples:
      |username                         |password         |
      |Batman	                        |Krypton-was_2000 |
      |Robin                            |Iamthenight1939   |
      |Robin	                        |Krypton-was_2000  |