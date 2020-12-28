package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.SpeakUtil;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class UnmoderatedSessionPage {
    private SelenideElement startTestButton = $x("//button[text()[contains(.,'Start Test')]]");
    private SelenideElement startTestWizardButton = $(byText("Start Test"));
    private SelenideElement skipTutorialButton = $(byText("Skip tutorial"));
    private SelenideElement nextButton = $(byText("Next"));
    private SelenideElement selectShareButton = $(byText("OK, I'll select share"));
    private SelenideElement startTaskButton = $(byText("Start Task"));
    private SelenideElement completeButton = $(byText("Complete"));
    private SelenideElement circleElement = $("div.vldt-circle");
    private SelenideElement uploadHeader = $(byText("Upload"));
    private SelenideElement thankYouHeader = $(byText("Thank you !"));
    private SpeakUtil synth;
    private DashboardPage dashboardPage;
    private final String TEXT_TO_SPEAK = "Full fathom five thy father lies, of his bones are coral made. Those are pearls that were his eyes. Nothing of him that doth fade, but doth suffer a sea-change into something rich and strange";
    private Condition clickable;

    public UnmoderatedSessionPage() {
        this.clickable = and("can be clicked", visible, enabled);
        this.synth = new SpeakUtil();
        this.dashboardPage = new DashboardPage();
    }

    public void switchFrameToUnmoderatedScreen() {
        switchTo().defaultContent();
        Selenide.Wait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
    }

    public void performUnmoderatedSessionForNthCard(int studyCardNumber) {
        open(dashboardPage.getTestLink(studyCardNumber));
        startTestButton.should(clickable).click();
        sleep(2000);
        skipTutorialButton.should(clickable).click();
        sleep(2000);
        synth.speak("Microphone test");
        switchFrameToUnmoderatedScreen();
        nextButton.should(clickable).click();
        selectShareButton.should(clickable).click();
        sleep(2000);
        startTaskButton.should(clickable).click();
        synth.speak(TEXT_TO_SPEAK);
        circleElement.should(clickable).click();
        completeButton.should(clickable).click();
        uploadHeader.waitUntil(disappear, 20000);
        thankYouHeader.waitUntil(visible, 20000);
    }

}
