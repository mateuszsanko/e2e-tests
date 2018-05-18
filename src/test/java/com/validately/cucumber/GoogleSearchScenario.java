package com.validately.cucumber;


import com.validately.cucumber.utils.SharedDriver;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class GoogleSearchScenario {

    private WebDriver driver;
    private SharedDriver sharedDriver;

    public GoogleSearchScenario() {
        sharedDriver = new SharedDriver();
        driver = sharedDriver.driver();
    }

    @After
    public void afterScenario() {
        driver.quit();
    }

    @Given("^the page is open \"([^\"]*)\"$")
    public void the_page_is_open(String page) throws MalformedURLException {
        driver.get(page);
    }

    @When("^I search for \"([^\"]*)\"$")
    public void I_search_for(String search) throws Throwable {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(search);
        element.submit();
    }

    @Then("^a browser title should contains \"([^\"]*)\"$")
    public void a_browser_title_should_contains(String text) throws Throwable {
        assertTrue(driver.getTitle().contains(text));
    }
}