package com.validately.cucumber.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.selenium.factory.WebDriverPool;

import java.net.MalformedURLException;
import java.net.URL;

public class SharedDriver {

    public static WebDriver driver() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("Platform", "Linux");
        URL hub = null;
        try {
            hub = new URL("");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(hub, options);
    }

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver().quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }
}