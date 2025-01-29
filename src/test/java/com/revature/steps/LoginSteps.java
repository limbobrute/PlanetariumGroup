package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginSteps {

    @Given("the user tries to login")
    public void the_user_tries_to_login() {
        TestRunner.loginPage.openLoginPage();
    }

    @When("the user provides a valid username for login")
    public void the_user_provides_a_valid_username_for_login() {
        TestRunner.loginPage.enterUsername("Batman");
    }

    @When("the user provides a valid password for login")
    public void the_user_provides_a_valid_password_for_login() {
        TestRunner.loginPage.enterPassword("Iamthenight1939");
    }

    @Then("the user should be able to securely interact with the Planetarium")
    public void the_user_should_be_able_to_securely_interact_with_the_Planetarium() {
        TestRunner.loginPage.clickLoginButton();
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

    @When("the user provides login username {string}")
    public void the_user_provides_login_username(String username) {
        TestRunner.loginPage.enterUsername(username);
    }

    @And("the user provides login password {string}")
    public void the_user_provides_login_password(String password) {
        TestRunner.loginPage.enterPassword(password);
    }

    @Then("the user should get a browser alert saying Invalid credentials")
    public void the_user_should_get_a_browser_alert_saying_Invalid_credentials() {
        TestRunner.loginPage.clickLoginButton();
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        Assert.assertEquals("Invalid credentials", alert.getText());
        alert.accept();
    }
}
