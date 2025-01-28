package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RemoveSteps {
    @When("the user removes a planet with name {string}")
    public void the_user_removes_a_planet_with_name(String name) {
        TestRunner.homePage.selectPlanet();
        TestRunner.homePage.deleteResource(name);
    }

    @When("the user removes a moon with name {string}")
    public void the_user_removes_a_moon_with_name(String name) {
        TestRunner.homePage.selectMoon();
        TestRunner.homePage.deleteResource(name);
    }

    @Then("the planet {string} should be removed from the database successfully")
    public void the_planet_should_be_removed_from_the_database_successfully(String name) {
        Assert.assertFalse(TestRunner.homePage.tableContains(name));
    }

    @Then("the moon {string} should be removed from the database successfully")
    public void the_moon_should_be_removed_from_the_database_successfully(String name) {
        Assert.assertFalse(TestRunner.homePage.tableContains(name));
    }

    @Then("the user should get a browser alert saying Invalid {string} name")
    public void the_user_should_get_a_browser_alert_saying_Invalid_planet_or_moon(String entityType) {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        Assert.assertEquals("Invalid " + entityType + " name", alert.getText());
        alert.accept();
    }


    @And("all associated moons should be removed if it is a planet {string}")
    public void all_associated_moons_should_be_removed_if_it_is_a_planet(String entityType) {
        if (entityType.equals("planet")) {
            Assert.assertFalse(TestRunner.homePage.tableContains("Luna"));
        }
    }
}
