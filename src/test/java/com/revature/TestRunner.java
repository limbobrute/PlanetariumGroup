package com.revature;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.revature.tests",
        plugin = {"pretty", "html:src/test/resources/reports/html-report.html", "json:src/test/resources/reports/json-report.json"}
)
public class TestRunner
{
    @BeforeClass
    public static void setup()
    {

    }
}
