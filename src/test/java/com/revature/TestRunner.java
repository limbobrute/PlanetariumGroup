package com.revature;

import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;


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
        //Add logic for setup (i.e setup-reset.sql)
    }

    @AfterClass
    public static void tearDown()
    {
        //Reset everything here as needed
    }

}
