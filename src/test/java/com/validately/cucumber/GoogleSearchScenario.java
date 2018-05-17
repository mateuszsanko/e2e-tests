package com.validately.cucumber;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class GoogleSearchScenario {

//    private SharedDriver sharedDriver;
    private WebDriver driver;

//    public GoogleSearchScenario(SharedDriver sharedDriver) {
//        this.sharedDriver = sharedDriver;
//    }

//    @Before
//    public void setupTest() {
//        driver = sharedDriver.getDriver();
//    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


	@Given("^the page is open \"([^\"]*)\"$")
	public void the_page_is_open(String page) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("Platform", "Linux");
		driver = new RemoteWebDriver(new URL("http://10.3.240.13:4444/wd/hub"), options);
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
		driver.quit();
	}
}