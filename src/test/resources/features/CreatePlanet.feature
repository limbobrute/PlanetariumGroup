@US4 @SR3 @PR6 @NeedsLogout
Feature: Planet Creation
  As a user I want to add new planets to the Planetarium so I can update my findings
  Background: Planet Creation Starting Action
    Given the user is in the home page
    And Planet is selected
  
  @PR4 @PR5
  Scenario Outline: Sucessful planet Creation
    When the user provides planet name "<planet name>"
    And the user provides planet image "<file path>" file
    And Submits the planet name
    Then the table refreshes
    And the new planet "<planet name>" is on the table

    Examples:
      | planet name | file path                                                                                                                             |
      | X3r_df -45  | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg |
      | X3r_df -46  | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png |
      | X3r_df -47  | null                                                                                                                                  |

  @PR1 @PR2 @PR3
  Scenario Outline: Unsucessful planet creation
    When the user provides planet name "<planet name>"
    And the user provides planet image "<file path>" file
    And Submits the planet name
    Then the user should get a browser alert saying "<alert>"

    Examples:
      | planet name                    |  file path                                                                                                                                             | alert               |
      |                                |  /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg                 |	Invalid planet name |
      | andigkfsau_-123dadjing infkihek|	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg                 |	Invalid planet name |
      | hellu?                         |	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg                 |	Invalid planet name |
      | Earth                          |	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg                 |	Invalid planet name |
      |                                |	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png                 |	Invalid planet name |
      | andigkfsau_-123dadjing infkihek|	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png                 |	Invalid planet name |
      | hellu?                         |	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png                 |	Invalid planet name |
      | Earth                          |	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png                 |	Invalid planet name |
      |                                |	null                                                                                                                                                  |	Invalid planet name |
      | andigkfsau_-123dadjing infkihek|	null                                                                                                                                                  |	Invalid planet name |
      | hellu?                         |	null                                                                                                                                                  |	Invalid planet name |
      | Earth                          |	null                                                                                                                                                  |	Invalid planet name |
      | X3r_df -48                     |	/Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/uploadMeIfYouCan.gif         |	Invalid file type   |