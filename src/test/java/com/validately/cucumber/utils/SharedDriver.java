package com.validately.cucumber.utils;

import cucumber.api.java.Before;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created by validately on 30/04/2018.
 */
public class SharedDriver {
    private static boolean initialized = false;

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        if (!initialized) {
            // initialize the driver
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            ChromeOptions opt = new ChromeOptions();
//            capabilities.setBrowserName("chrome");
//            capabilities.setPlatform(Platform.MAC);
            driver = new RemoteWebDriver(new URL("http://zalenium:4444/wd/hub"), opt);

            initialized = true;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
