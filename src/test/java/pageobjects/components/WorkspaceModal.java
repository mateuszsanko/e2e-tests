package pageobjects.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WorkspaceModal {
    private SelenideElement component = $("div.modal-content");

    public SelenideElement getComponent() {
        return component;
    }

    public SelenideElement getHeader() {
        return component.$("h2");
    }

    public SelenideElement workspaceNameInput = $("input[data-tid='workspace-name-input']");
    public SelenideElement selectOwner = component.$("div.Select-control");
    public ElementsCollection selectItems = $$("div.Select-option");
    public SelenideElement submitButton = $("button[data-tid='create-btn']");
    public Condition clickable;

    public WorkspaceModal() {
        clickable = and("can be clicked", visible, enabled);
    }

    public void selectNthOwner(int elementNumber) {
        selectOwner.should(clickable).click();
        selectItems.get(elementNumber).click();
    }

    public void submit() {
        submitButton.should(clickable).click();
    }

    public void create(String name, int ownerNumber) {
        component.should(visible);
        workspaceNameInput.should(visible).sendKeys(name);
        selectNthOwner(ownerNumber);
        submit();
    }
}


