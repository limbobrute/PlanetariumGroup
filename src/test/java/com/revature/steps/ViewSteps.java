package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ViewSteps {

    @Given("the user is logged in on the home page")
    public void the_user_is_logged_in_on_the_home_page() {
        TestRunner.loginPage.setUpLoggedInUser();
    }

    @Given("the user is not logged in")
    public void the_user_is_not_logged_in() {
        TestRunner.loginPage.openLoginPage();
    }

    @When("the user tries to directly access the home page")
    public void the_user_tries_to_directly_access_the_home_page() {
        TestRunner.homePage.tryToAccessHomePageDirectly();
    }

    @Then("they should see their planets and moons")
    public void they_should_see_their_planets_and_moons() {
        // TODO: switch from try/finally to using a Cucumber hook to log out the user
        try{
            TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
            Assert.assertEquals(
                    String.format(
                            "Expected 'Welcome to the Home Page Batman, but got %s",
                            TestRunner.homePage.getHomePageGreeting()
                    ),
                    "Welcome to the Home Page Batman",
                    TestRunner.homePage.getHomePageGreeting());
            Assert.assertEquals(4, TestRunner.homePage.getNumberOfCelestialRows());
        } finally {
            TestRunner.homePage.logout();
        }
    }

    @Then("the user should be denied access")
    public void the_user_should_be_denied_access() {
        Assert.assertNotEquals("Home", TestRunner.driver.getTitle());
    }

    @Given("a user has created a planet")
    public void a_user_has_created_a_planet() {
        TestRunner.loginPage.setUpLoggedInUser();
        TestRunner.homePage.selectPlanet();
        TestRunner.homePage.enterPlanetName("eternal atake");
        TestRunner.homePage.submitResource();
    }

    @Given("a user has created a moon")
    public void a_user_has_created_a_moon() {
        TestRunner.loginPage.setUpLoggedInUser();
        TestRunner.homePage.selectPlanet();
        TestRunner.homePage.enterPlanetName("eternal atake");
        TestRunner.homePage.submitResource();
        TestRunner.homePage.selectMoon();
        TestRunner.homePage.enterMoonName("majora moon");
        TestRunner.homePage.enterOrbitedPlanetId("3");
        TestRunner.homePage.submitResource();
    }

    @When("another user views the home page")
    public void another_user_views_the_home_page() {
        TestRunner.homePage.logout();
        TestRunner.loginPage.clickRegistrationLink();
        TestRunner.registrationPage.enterUsername("Super_man-2001");
        TestRunner.registrationPage.enterPassword("Krypton-was_2000");
        TestRunner.registrationPage.clickCreateButton();
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        alert.accept();
        TestRunner.loginPage.enterUsername("Super_man-2001");
        TestRunner.loginPage.enterPassword("Krypton-was_2000");
        TestRunner.loginPage.clickLoginButton();
        //quick fix for celestialtable
        /*TestRunner.homePage.selectPlanet();
        TestRunner.homePage.enterPlanetName("Miyabi");
        TestRunner.homePage.submitResource();*/
    }

    @Then("they should not be able to see the created planet")
    public void they_should_not_be_able_to_see_the_created_planet() {
        Assert.assertFalse(TestRunner.homePage.tableContains("eternal atake"));
    }

    @Then("they should not be able to see the created moon")
    public void they_should_not_be_able_to_see_the_created_moon() {
        Assert.assertFalse(TestRunner.homePage.tableContains("majora moon"));
    }


}
