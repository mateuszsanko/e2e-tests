package pageobjects.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.PropertiesManager;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WelcomeModal {
    private SelenideElement component = $("div.modal-content");

    public SelenideElement getComponent() {
        return component;
    }

    public SelenideElement getHeader() {
        return component.$("h1");
    }

    public ElementsCollection getCheckboxList() {
        return component.$$("label.radio i");
    }

    public SelenideElement getSubmitButton() {
        return component.$("button[data-tid='questionnair-done-btn']");
    }
}


