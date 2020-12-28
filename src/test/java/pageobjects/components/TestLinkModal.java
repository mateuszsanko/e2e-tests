package pageobjects.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TestLinkModal {
    private SelenideElement component = $("div.modal-content");
    private SelenideElement copyButton = component.$("button[data-tid='copy-content-btn']");

    public SelenideElement getComponent() {
        return component;
    }

    public SelenideElement getCopyButton() {
        return copyButton;
    }
}


