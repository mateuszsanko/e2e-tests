package pageobjects;

import com.codeborne.selenide.SelenideElement;
import utils.PropertiesManager;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement userInput = $("input[type='email']");
    private SelenideElement passInput = $("input[type='password']");
    private SelenideElement submitButton = $("button[type='submit']");
    private SelenideElement signupButton = $("a[data-tid='signup-link']");

    public void login() {
        PropertiesManager prop = new PropertiesManager();
        userInput.sendKeys(prop.getProperty("user"));
        passInput.sendKeys(prop.getProperty("password"));
        submitButton.click();
    }

    public SelenideElement getSignupButton() {
        return signupButton;
    }
}
