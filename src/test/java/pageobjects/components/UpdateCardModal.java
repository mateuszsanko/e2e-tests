package pageobjects.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UpdateCardModal {
    private SelenideElement component = $("div.modal-content");
    private SelenideElement cardNumberInput = component.$("input.card-number");
    private SelenideElement securityCodeInput = component.$("input.cvc");
    private SelenideElement submitButton = component.$("button[type='submit']");

    public SelenideElement getMonthDropdown() {
        return $$("div.Select").get(1);
    }

    public SelenideElement getYearDropdown() {
        return $$("div.Select").get(2);
    }

    public SelenideElement getMonthOptionByText(String optionText) {
        getMonthDropdown().click();
        return getMonthDropdown().$("div[aria-label='" + optionText + "']");
    }

    public SelenideElement getYearOptionByText(String optionText) {
        getYearDropdown().click();
        return getYearDropdown().$("div[aria-label='" + optionText + "']");
    }

    public SelenideElement getComponent() {
        return component;
    }

    public SelenideElement getCardNumberInput() {
        return cardNumberInput;
    }

    public SelenideElement getSecurityCodeInput() {
        return securityCodeInput;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }
}
