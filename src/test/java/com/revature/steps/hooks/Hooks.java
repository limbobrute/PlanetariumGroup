package com.revature.steps.hooks;

import com.revature.TestRunner;
import com.revature.utils.Setup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Hooks {

    /*
        Instead of manually resetting the test database between
        runs we can use a hook and the setup class to automate
        the database reset process
     */

    @Before
    public void resetDatabase(){
        System.out.println("resetDatabase hook called");
        Setup.resetTestDatabase();
    }

    @After
    public void dismissLeftoverAlert() {
        try {
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            Alert leftoverAlert = TestRunner.driver.switchTo().alert();
            leftoverAlert.accept();
            System.out.println("Dismissed leftover alert after scenario.");
        } catch (TimeoutException e) {
            System.out.println("No leftover alert detected.");
        }
    }


}
