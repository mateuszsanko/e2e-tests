package com.validately.cucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/resources", format = { "pretty",
        "json:target/cucumber.json", "html:target/cucumber" }, tags = { "~@ignore" })
public class GoogleSearchTest {

    //"pretty", "html:target/cucumber", "json:target/cucumber.json"
	
}
