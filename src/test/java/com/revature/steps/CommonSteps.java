package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;

public class CommonSteps {

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        TestRunner.loginPage.setUpLoggedInUser();
    }
}
