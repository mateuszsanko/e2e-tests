package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.Helper;
import utils.PropertiesManager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SignupPage {
    private SelenideElement fullNameInput = $("input[placeholder='John Smith']");
    private SelenideElement companyNameInput = $("input[placeholder='SkyNet']");
    private SelenideElement jobTitleDropdown = $("div.Select-placeholder");
    private SelenideElement emailInput = $("input[placeholder='jsmith@example.com']");
    private SelenideElement passwordInput = $("input[type='password']");
    private ElementsCollection selectOptions = $$("div.Select-option");
    private SelenideElement signupButton = $("button[data-tid='sign-up-btn']");
    private Helper helper;

    private final String PREFIX = "e2e";

    public SignupPage() {
        helper = new Helper();
    }

    public void signupUser() {
        long timestamp = helper.generateTimestamp();
        fullNameInput.sendKeys(PREFIX + "name" + timestamp);
        companyNameInput.sendKeys(PREFIX + "com" + timestamp);
        clickValueFromDropdownByNumber(0);
        emailInput.sendKeys(PREFIX + "mail" + timestamp + "@userzoom.com");
        passwordInput.sendKeys("Passsentence9)9");
        signupButton.click();
    }

    private void clickValueFromDropdownByNumber(int optionNumber) {
        jobTitleDropdown.click();
        selectOptions.get(optionNumber).click();
    }

}
