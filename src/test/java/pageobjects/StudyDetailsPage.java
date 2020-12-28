package pageobjects;

import com.codeborne.selenide.SelenideElement;
import utils.Helper;

import static com.codeborne.selenide.Selenide.$;

public class StudyDetailsPage {
    private SelenideElement freeTrialMessage = $("div.free-trial-expiration-message");
    private SelenideElement testNameInput = $("input#test-name");
    private SelenideElement unmoderatedCard = $("div[data-tid='unmoderated-card']");
    private SelenideElement moderatedCard = $("div[data-tid='moderated-card']");
    private SelenideElement testOnMyOwnCard = $("div[data-tid='byo-card']");
    private SelenideElement userPanelCard = $("div[data-tid='panel-card']");
    private SelenideElement desktopCard = $("div[data-tid='desktop-card']");
    private SelenideElement mobileCard = $("div[data-tid='mobile-card']");
    private SelenideElement nextButton = $("button[data-tid='next-btn']");
    public static String generatedStudyName;

    public StudyDetailsPage() {

    }

    public SelenideElement getTestNameInput() {
        return testNameInput;
    }

    public SelenideElement getUnmoderatedCard() {
        return unmoderatedCard;
    }

    public SelenideElement getModeratedCard() {
        return moderatedCard;
    }

    public SelenideElement getTestOnMyOwnCard() {
        return testOnMyOwnCard;
    }

    public SelenideElement getUserPanelCard() {
        return userPanelCard;
    }

    public SelenideElement getDesktopCard() {
        return desktopCard;
    }

    public SelenideElement getMobileCard() {
        return mobileCard;
    }

    public SelenideElement getNextButton() {
        return nextButton;
    }

    public static void setGeneratedStudyName(String generatedStudyName) {
        StudyDetailsPage.generatedStudyName = generatedStudyName;
    }

    public String generateStudyName() {
        Helper helper = new Helper();
        String prefix = "e2e-";
        String timestamp = Long.toString(helper.generateTimestamp());
        setGeneratedStudyName(prefix.concat(timestamp));
        return generatedStudyName;
    }
}
