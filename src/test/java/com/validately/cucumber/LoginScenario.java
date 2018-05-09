//package com.validately.cucumber;
//
//import cucumber.api.java.en.Given;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import static org.junit.Assert.assertEquals;
//
//public class LoginScenario {
//
//    private WebDriver driver;
//    private static final String LOGIN_BUTTON_TEXT = "Login";
//
//
//    @BeforeClass
//    public static void setupClass() {
//
//    }
//
//    @Before
//    public void setupTest() {
//
//    }
//
//    @After
//    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @Given("^I click Login button$")
//    public void I_click_Login_button() {
//        WebElement loginNavButton = driver.findElement(By.cssSelector("li.login a"));
//        loginNavButton.click();
//    }
//
//    @Given("^I am on login page$")
//    public void I_am_on_login_page() {
//        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
//        assertEquals("Login page has not been loaded", LOGIN_BUTTON_TEXT, loginButton);
//        driver.quit();
//    }
//
//
//}
