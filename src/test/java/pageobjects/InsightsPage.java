package pageobjects;

import com.codeborne.selenide.*;
import org.openqa.selenium.Keys;
import pageobjects.components.VideoPlayer;

import java.util.logging.Logger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

public class InsightsPage {
    private ElementsCollection recordingsList = $$("tr[data-tid='recording-tr']");
    private SelenideElement insightsTab = $("a[data-tid='tab-link-insights']");
    private SelenideElement reportTextArea = $("div.ql-editor");
    private SelenideElement addItemButton = $("button[data-tid='insights-sidemenu-toggle-btn']");
    private SelenideElement addHashtagButton = $("button[data-tid='insert-hashtag-btn']");
    private SelenideElement addClipButton = $("button[data-tid='insert-clip-btn']");
    private SelenideElement addImageButton = $("button[data-tid='insert-image-btn']");
    private SelenideElement selectModal = $("div.modal-body div.Select-control");
    private ElementsCollection modalClipLinks = $$("span.modal-clip-link");
    private ElementsCollection hashtagsListFromModal = $$("div.Select-menu-outer div.Select-option");
    private VideoPlayer videoPlayer;

    Logger logger = Logger.getLogger(InsightsPage.class.getName());
    private Condition clickable;

    public InsightsPage() {
        videoPlayer = new VideoPlayer();
        clickable = and("can be clicked", visible, enabled);
    }

    public SelenideElement getInsightsTab() {
        return insightsTab;
    }

    public void clearTextArea() {
        reportTextArea.should(clickable).click();
        reportTextArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        Selenide.actions().sendKeys(Keys.DELETE).perform();
        reportTextArea.should(exactText(""));
    }

    public void addHashtags() {
        int numberOfHashtagsInReport;
        reportTextArea.should(clickable).click();
        reportTextArea.sendKeys("a");
        addItemButton.should(clickable).click();
        addHashtagButton.should(clickable).click();
        selectModal.should(visible);
        Selenide.actions().sendKeys(Keys.ENTER).perform();
        numberOfHashtagsInReport = (int) reportTextArea.getText().chars().filter(ch -> ch == '#').count();
        assertTrue("Number of hashtags is different than expected ", numberOfHashtagsInReport > 0);
    }

    public void checkHashtagsVideos(int numberOfHashtags) {
        modalClipLinks.shouldHave(CollectionCondition.sizeGreaterThan(2));
        for (int i = 0; i < numberOfHashtags; i++) {
            modalClipLinks.get(i).should(clickable).click();
            videoPlayer.getPlayButton().should(visible);
            Selenide.actions().moveToElement(videoPlayer.getPlayButton()).moveByOffset(0, 200).click().perform();
            videoPlayer.getPlayButton().should(not(visible));
        }
    }
}