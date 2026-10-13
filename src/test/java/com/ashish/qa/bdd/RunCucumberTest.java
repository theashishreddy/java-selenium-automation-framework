package com.ashish.qa.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.ashish.qa.bdd",
    plugin = {"pretty", "html:target/cucumber-report.html"},
    monochrome = true
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
