package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OptionsPage {
    private SelenideElement limitedResponsesRadioButton = $("input[data-tid='limited-responses-radio']");
    private SelenideElement doNotPromptForEmailRadioButton = $("label[for='details-not-required']");
    private SelenideElement nextButton = $("a[data-tid='next-btn']");

    public OptionsPage() {

    }

    public SelenideElement getLimitedResponsesRadioButton() {
        return limitedResponsesRadioButton;
    }

    public SelenideElement getDoNotPromptForEmailRadioButton() {
        return doNotPromptForEmailRadioButton;
    }

    public SelenideElement getNextButton() {
        return nextButton;
    }
}
