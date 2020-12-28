package pageobjects.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.Helper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class VideoPlayer {
    private SelenideElement playButton = $("button[data-tid='toggle-playback-btn']");
    private SelenideElement clip = $("div.clip");
    private SelenideElement createClipButton = $("button[data-tid='toggle-clip-btn']");
    private SelenideElement clipNameInput = $("input.hashtag-input__input");
    private SelenideElement saveClipButton = $("button[data-tid='save-clip-btn']");
    private Condition clickable;
    private Helper helper;

    public VideoPlayer() {
        this.clickable = and("can be clicked", visible, enabled);
        this.helper = new Helper();
    }

    public SelenideElement getPlayButton() {
        return playButton;
    }

    public void createClip() {
        String clipName = String.valueOf(helper.generateTimestamp());
        createClipButton.should(clickable).click();
        Selenide.actions().clickAndHold(clip).moveByOffset(200, 0).release().perform();
        clipNameInput.should(visible).sendKeys(clipName);
        saveClipButton.should(clickable).click();
        System.out.println();
    }
}


