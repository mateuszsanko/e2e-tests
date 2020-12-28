package pageobjects.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NavbarComponent {
    private SelenideElement component = $("nav[data-tid='navbar-component']");
    private SelenideElement logo = component.$("img.logo-name");
    private SelenideElement workspaceName = component.$("div.workspace-name");
    private SelenideElement titleText = component.$("span.title-text");

    }


