@US4 @SR3 @MR7 @NeedsLogout
Feature: Moon Creation
  As a user I want to add new moons to the Planetarium so I can update my findings

  Background: Moon Creation Starting Action
    Given the user is in the home page
    And Moon is selected

  @MR6
  Scenario Outline: Sucessful moon Creation 
    When the user provides moon name "<moon name>"
    And the user provides planet Id 1
    And the user provides moon image "<file path>" file
    And Submits the moon name
    Then the table refreshes
    And the new moon "<moon name>" is on the table

    Examples:
      | moon name   | file path                                                                                                                              |
      | Y3r_df -45  | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg  |
      | Y3r_df -46  | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png  |
      | Y3r_df -47  | null                                                                                                                                   |
  
  @MR1 @MR2 @MR3 @MR4
  Scenario Outline: Unsucessful moon creation
    When the user provides moon name "<moon name>"
    And the user provides planet Id <planet Id>
    And the user provides moon image "<file path>" file
    And Submits the moon name
    Then the user should get a browser alert saying "<alert>"
    Examples:
      | moon name                        |  planet Id | file path                                                                                                                                     | alert                |
      | Y3r_df -48                       |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/uploadMeIfYouCan.gif | Invalid image format |
      | Y3r_df -48                       |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/uploadMeIfYouCan.gif | Invalid image format |
      | Y3r_df -48                       |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/uploadMeIfYouCan.gif | Invalid image format |
      | Y3r_df -48                       |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/uploadMeIfYouCan.gif | Invalid image format |
      | Y3r_df -48                       |  4	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg         | Invalid planet id    |
      | Y3r_df -48                       |  4	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png         | Invalid planet id    |
      | Y3r_df -48                       |  4	        | null	                                                                                                                                        | Invalid planet id    |
      |                                  |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg         | Invalid moon name    |
      | andigkfsau_-123dadjing infkihek  |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png         | Invalid moon name    |
      | hellu?                           |  1	        | null	                                                                                                                                        | Invalid moon name    |
      | Luna                             |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg         | Invalid moon name    |
      |                                  |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png         | Invalid moon name    |
      | andigkfsau_-123dadjing infkihek  |  1	        | null	                                                                                                                                        | Invalid moon name    |
      | hellu?                           |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg         | Invalid moon name    |
      | Luna                             |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png         | Invalid moon name    |
      |                                  |  1	        | null	                                                                                                                                        | Invalid moon name    |
      | andigkfsau_-123dadjing infkihek  |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/jpg_test.jpg         | Invalid moon name    |
      | hellu?                           |  1	        | /Users/gokarnabhandari/Documents/Revature Training/Assignments/Project 1/Project/cucumber/src/test/resources/test_images/png_test.png         | Invalid moon name    |
      | Luna	                           |  1         | null	                                                                                                                                        | invalid moon name    |