@US2 @SR1 @SR3
Feature: Secure Access to the account
  As a user I want to securely access my account so I can interact with the Planetarium in a secure environment
    Background: The user is not logged in
      Given the user is not logged in

    @NeedsLogout
    Scenario: Could access the account
      When the user provides username "Batman"
      And the user provides password "Iamthenight1939"
      And the user submits the login credentials
      Then the user should get access to the Planetarium Homepage

    Scenario Outline: Could not access an account
      When the user provides username "<username>"
      And the user provides password "<password>"
      And the user submits the login credentials
      Then the user should get a browser alert saying "<alert>"
      And the user should stay on the login page

     Examples:
        | username       | password       | alert               |
        | Batman         | b0Ts           | Invalid credentials |
        | Robin          | Iamthenight1939| Invalid credentials |
        | Robin          | bOTs           | Invalid credentials |