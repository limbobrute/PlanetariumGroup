package com.revature.poms;

import com.revature.TestRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(tagName = "input")
    private WebElement loginButton;

    @FindBy(tagName = "a")
    private WebElement registrationLink;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUpLoggedInUser(){
        driver.get("http://localhost:8081/");
        //System.out.println(driver.getPageSource());
        usernameInput.sendKeys("Batman");
        //System.out.println("sending username: " + usernameInput);
        passwordInput.sendKeys("Iamthenight1939");
        //System.out.println("sending password: " + passwordInput);
        loginButton.submit();
        /*System.out.println("logging in");
        try {
            Alert alert = TestRunner.driver.switchTo().alert();
            System.out.println("Alert found: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert present after login.");
        }*/
    }

    public void clickLoginButton() {
        loginButton.submit();
    }

    public void openLoginPage(){
        driver.get("http://localhost:8081/");
    }

    public void clickRegistrationLink(){
        registrationLink.click();
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

}
