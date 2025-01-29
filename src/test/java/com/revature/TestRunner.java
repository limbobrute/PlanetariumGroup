package com.revature;

import com.revature.poms.HomePage;
import com.revature.poms.LoginPage;
import com.revature.poms.RegistrationPage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.revature.steps",
        plugin = {
                "pretty",
                "html:src/test/resources/reports/html-report.html",
                "json:src/test/resources/reports/json-report.json"
        }
)
public class TestRunner {

    public static WebDriver driver = null;
    public static WebDriverWait wait;
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static RegistrationPage registrationPage;

    @BeforeClass
    public static void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        //String tempDir = "chrome-profile-" + System.getenv("BUILD_ID");
        //options.addArguments("user-data-dir=" + tempDir);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public static void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
