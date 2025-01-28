package com.revature.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    @FindBy(id = "greeting")
    private WebElement greetingHeader;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(tagName = "tr")
    private List<WebElement> tableRows;

    @FindBy(id = "locationSelect")
    private WebElement planetMoonSelect;

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;

    @FindBy(className = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "celestialTable")
    private WebElement celestialTable;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHomePageGreeting(){
        return greetingHeader.getText();
    }

    public int getNumberOfCelestialRows(){
        return tableRows.size()-1;
    }

    public void tryToAccessHomePageDirectly(){
        driver.get("http://localhost:8080/planetarium");
    }

    public void selectPlanet() {
        List<WebElement> options = planetMoonSelect.findElements(By.tagName("option"));
        options.get(1).click();
    }

    public void selectMoon() {
        List<WebElement> options = planetMoonSelect.findElements(By.tagName("option"));
        options.get(0).click();
    }

    public void enterPlanetName(String planetName) {
        planetNameInput.sendKeys(planetName);
    }

    public void enterMoonName(String moonName) {
        moonNameInput.sendKeys(moonName);
    }

    public void enterPlanetImage() {
        String filePath = "C:\\Users\\micke\\OneDrive\\Desktop\\Revature Paid Training\\Project1\\Project1ME\\db-setup\\src\\test\\resources\\Celestial-Images\\planet-3.jpg";
        planetImageInput.sendKeys(filePath);
    }

    public void enterMoonImage() {
        String filePath = "C:\\Users\\micke\\OneDrive\\Desktop\\Revature Paid Training\\Project1\\Project1ME\\db-setup\\src\\test\\resources\\Celestial-Images\\moon-3.jpg";
        moonImageInput.sendKeys(filePath);
    }

    public void enterInvalidPlanetImage() {
        String filePath = "C:\\Users\\micke\\OneDrive\\Desktop\\Revature Paid Training\\Project1\\Project1ME\\db-setup\\src\\test\\resources\\Celestial-Images\\video.wmv";
        planetImageInput.sendKeys(filePath);
    }

    public void enterInvalidMoonImage() {
        String filePath = "C:\\Users\\micke\\OneDrive\\Desktop\\Revature Paid Training\\Project1\\Project1ME\\db-setup\\src\\test\\resources\\Celestial-Images\\video.wmv";
        moonImageInput.sendKeys(filePath);
    }

    public void enterOrbitedPlanetId(String id) {
        orbitedPlanetInput.sendKeys(id);
    }

    public void submitResource() {
        submitButton.click();
    }


    public void deleteResource(String name) {
        deleteInput.sendKeys(name);
        deleteButton.click();
    }

    public void logout(){
        logoutButton.click();
    }

    public boolean tableContains(String name) {
        String tableText = celestialTable.getText();

        return tableText.contains(name);
    }


}
