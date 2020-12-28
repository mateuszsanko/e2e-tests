package pageobjects;

import com.codeborne.selenide.SelenideElement;
import pageobjects.components.LaunchStudyModal;

import static com.codeborne.selenide.Selenide.$;

public class SharePage {
    private SelenideElement backToDashboardButton = $("a[data-tid='to-dashboard-btn']");
    private SelenideElement studyUrlInput = $("input#shareable");
    private SelenideElement copyButton = $("button[data-tid='copy-content-btn']");

    public SelenideElement getBackToDashboardButton() {
        return backToDashboardButton;
    }

    public SelenideElement getStudyUrlInput() {
        return studyUrlInput;
    }

    public SelenideElement getCopyButton() {
        return copyButton;
    }
}
