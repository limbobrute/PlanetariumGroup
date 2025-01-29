package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddSteps {
    @When("the user provides a planet with name {string}")
    public void the_user_provides_a_planet_with_name(String name) {
        TestRunner.homePage.selectPlanet();
        TestRunner.homePage.enterPlanetName(name);
    }

    @When("the user provides file type {string} for planet")
    public void the_user_provides_file_type_for_planet(String fileType) {
        if (!fileType.equals("null")) {
            TestRunner.homePage.enterPlanetImage();
        }
    }

    @Then("the planet {string} should be created successfully in the database")
    public void the_planet_should_be_created_successfully_in_the_database(String name) {
        TestRunner.homePage.submitResource();
        TestRunner.wait.until(driver -> TestRunner.homePage.tableContains(name));
        Assert.assertTrue(TestRunner.homePage.tableContains(name));
    }

    @When("the user provides a moon with name {string}")
    public void the_user_provides_a_moon_with_name(String name) {
        TestRunner.homePage.selectMoon();
        TestRunner.homePage.enterMoonName(name);
    }

    @When("the user provides file type {string} for moon")
    public void the_user_provides_file_type_for_moon(String fileType) {
        if (!fileType.equals("null")) {
            TestRunner.homePage.enterMoonImage();
        }
    }

    @When("the user provides a valid orbited planet id")
    public void the_user_provides_a_valid_orbited_planet_id() {
        TestRunner.homePage.enterOrbitedPlanetId("1");
    }

    @Then("the moon {string} should be created successfully in the database")
    public void the_moon_should_be_created_successfully_in_the_database(String name) {
        TestRunner.homePage.submitResource();
        TestRunner.wait.until(driver -> TestRunner.homePage.tableContains(name));
        Assert.assertTrue(TestRunner.homePage.tableContains(name));
    }

    @Then("the user should get a browser alert saying Invalid planet name")
    public void the_user_should_get_a_browser_alert_saying_Invalid_planet_name() {
        TestRunner.homePage.submitResource();
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        Assert.assertEquals("Invalid planet name", alert.getText());
        alert.accept();
    }

    @When("the user provides an orbited planet id {string}")
    public void the_user_provides_an_orbited_planet_id(String id) {
        TestRunner.homePage.enterOrbitedPlanetId(id);
    }

    @Then("the user should get a browser alert saying alert: {string}")
    public void the_user_should_get_a_browser_alert_saying_alert(String currAlert) {
        TestRunner.homePage.submitResource();
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        Assert.assertEquals(currAlert, alert.getText());
        alert.accept();
        TestRunner.wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

    @When("the user provides a valid name for planet")
    public void the_user_provides_a_valid_name_for_planet() {
        TestRunner.homePage.selectPlanet();
        TestRunner.homePage.enterPlanetName("eternal atake");
    }

    @When("the user provides an invalid file type for planet")
    public void the_user_provides_an_invalid_file_type_for_planet() {
        TestRunner.homePage.selectPlanet();
        TestRunner.homePage.enterInvalidPlanetImage();
    }

    @When("the user provides an invalid file type for moon")
    public void the_user_provides_an_invalid_file_type_for_moon() {
        TestRunner.homePage.selectMoon();
        TestRunner.homePage.enterInvalidMoonImage();
    }


    @Then("the user should get a browser alert saying Invalid file type")
    public void the_user_should_get_a_browser_alert_saying_Invalid_file_type() {
        TestRunner.homePage.submitResource();
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        Assert.assertEquals("Invalid file type", alert.getText());
        alert.accept();
    }

    @When("the user provides a valid name for moon")
    public void the_user_provides_a_valid_name_for_moon() {
        TestRunner.homePage.selectMoon();
        TestRunner.homePage.enterMoonName("majora moon");
        TestRunner.homePage.enterOrbitedPlanetId("1");
    }
}