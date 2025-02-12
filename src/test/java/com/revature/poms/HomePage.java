package com.revature.poms;

import com.revature.planetarium.utility.AppConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
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
        driver.get("http://54.196.136.154:8081/planetarium/");
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
        File file = new File("src/test/resources/Celestial-Images/planet-3.jpg");
        String filePath = file.getAbsolutePath();
        planetImageInput.sendKeys(filePath);
    }

    public void enterMoonImage() {
        File file = new File("src/test/resources/Celestial-Images/moons-3.jpg");
        String filePath = file.getAbsolutePath();
        moonImageInput.sendKeys(filePath);
    }

    public void enterInvalidPlanetImage() {
        File file = new File("src/test/resources/Celestial-Images/Testwebp.webp");
        String filePath = file.getAbsolutePath();
        planetImageInput.sendKeys(filePath);
    }

    public void enterInvalidMoonImage() {
        File file = new File("src/test/resources/Celestial-Images/Testwebp.webp");
        String filePath = file.getAbsolutePath();
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
