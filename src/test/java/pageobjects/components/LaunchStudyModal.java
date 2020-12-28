package pageobjects.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LaunchStudyModal {
    private SelenideElement component = $("div.modal-content");
    private SelenideElement confirmButoon = component.$("button[data-tid='confirm-launch-btn']");

    public SelenideElement getComponent() {
        return component;
    }

    public SelenideElement getConfirmButoon() {
        return confirmButoon;
    }
}


