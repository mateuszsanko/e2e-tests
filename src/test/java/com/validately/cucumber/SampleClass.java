package com.validately.cucumber;

import com.validately.cucumber.utils.PropertiesManager;
import com.validately.cucumber.utils.SharedDriver;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class SampleClass {

    private WebDriver driver;
    private SharedDriver sharedDriver;
    private PropertiesManager propertiesManager;

    public SampleClass() {
        sharedDriver = new SharedDriver();
        driver = sharedDriver.driver();
        propertiesManager = new PropertiesManager();
    }

    @After
    public void afterScenario() {
        driver.quit();
    }

    @Given("^the page is opened \"([^\"]*)\"$")
    public void the_page_is_opened(String arg1) {
        driver.get("http://www.onet.pl");
        System.out.println(propertiesManager.getProperty("name1") + " ###");
    }

    @When("^I searched for \"([^\"]*)\"$")
    public void i_searched_for(String arg1) throws Throwable {

    }
}
