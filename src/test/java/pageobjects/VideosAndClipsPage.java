package pageobjects;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import pageobjects.components.VideoPlayer;
import utils.Helper;

import java.util.logging.Logger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VideosAndClipsPage {
    private ElementsCollection recordingsList = $$("tr[data-tid='recording-tr']");
    private SelenideElement clipsListComponent = $("table[data-tid='clips-list']");
    private ElementsCollection clipsItems = $$("table[data-tid='clips-list'] tr");
    private SelenideElement videosHeader = $(byText("Videos"));
    private SelenideElement notesTextarea = $("textarea");
    private SelenideElement notesComponent = $("ul.list-unstyled");
    private ElementsCollection notesList = $$("li.note-item");
    private SelenideElement tagsList = $("div[data-tid='tags-list']");
    private SelenideElement playButton = $("button[data-tid='toggle-playback-btn']");
    private SelenideElement flagButton = $("button[data-tid='flag-btn']");
    private ElementsCollection editNoteButton = $$("button[data-tid='edit-note-btn'] i.fa-pencil");
    private ElementsCollection deleteNoteButton = $$("button[data-tid='delete-note-btn'] i.fa-trash");
    private SelenideElement noteEditTextarea = $("li.note-item textarea");
    private SelenideElement saveEditedNoteButton = $("button[data-tid='save-note-edit-btn']");
    private VideoPlayer videoPlayer;
    private Helper helper;

    Logger logger = Logger.getLogger(VideosAndClipsPage.class.getName());
    private Condition clickable;

    public VideosAndClipsPage() {
        this.videoPlayer = new VideoPlayer();
        this.helper = new Helper();
        clickable = and("can be clicked", visible, enabled);
    }

    public VideoPlayer getVideoPlayer() {
        return videoPlayer;
    }

    public SelenideElement getClipsListComponent() {
        return clipsListComponent;
    }

    public ElementsCollection getClipsItems() {
        return clipsItems;
    }

    public SelenideElement getRecordingNumber(int recNumber) {
        return recordingsList.get(recNumber).should(visible).$("th");
    }

    public ElementsCollection getRecordingsList() {
        return recordingsList;
    }

    public void editNote(int numberOfNotes) {
        String primaryNoteText;
        String editText = "edited ";
        for (int i = 0; i < numberOfNotes; i++) {
            editNoteButton.get(i).should(clickable).click();
            primaryNoteText = notesTextarea.should(visible).getText();
            notesTextarea.sendKeys(editText);
            notesTextarea.should(text(editText.concat(primaryNoteText)));
            saveEditedNoteButton.should(clickable).click();
            notesComponent.should(text(editText.concat(primaryNoteText)));
        }
    }

    public void deleteItemFromList(int numberOfItemsToDelete) {
        int numberOfItemsOnList = notesList.shouldHave(CollectionCondition.sizeGreaterThan(0)).size();
        for (int i = 0; i < numberOfItemsToDelete; i++) {
            deleteNoteButton.get(i).should(clickable).click();
            notesList.shouldHaveSize(numberOfItemsOnList - 1);
            numberOfItemsOnList = notesList.size();
        }
    }

    public void addNote(int numberOfNotes, String pattern) {
        pattern = pattern.concat(" ");
        videoPlayer.getPlayButton().should(clickable).click();
        for (int i = 0; i < numberOfNotes; i++) {
            pattern = pattern + i;
            notesTextarea.should(visible).sendKeys(pattern);
            Selenide.actions().sendKeys(Keys.ENTER).perform();
            notesComponent.should(text(pattern));
            Selenide.sleep(1000);
        }
    }

    public void addHashtag(int numberOfNotes, String pattern) {
        videoPlayer.getPlayButton().should(clickable).click();
        pattern = pattern.concat(String.valueOf(helper.generateTimestamp()));
        for (int i = 0; i < numberOfNotes; i++) {
            notesTextarea.should(visible).sendKeys(pattern.concat(" "));
            notesTextarea.sendKeys(Keys.ENTER);
//            Selenide.actions().sendKeys(Keys.ENTER).perform();
//            notesComponent.should(text(pattern));
            tagsList.should(text(pattern));
            Selenide.sleep(1000);
        }
    }


    public void addFlag(int numberOfFlags) {
        int actualNumberOfFlagsInNotes = $$("i.fa-flag").size();
        videoPlayer.getPlayButton().should(clickable).click();
        for (int i = 0; i < numberOfFlags; i++) {
            flagButton.click();
            $$("i.fa-flag").shouldHave(CollectionCondition.sizeGreaterThan(actualNumberOfFlagsInNotes));
            actualNumberOfFlagsInNotes = $$("i.fa-flag").size();
            Selenide.sleep(1000);
        }
    }

    public boolean checkRecordingsOrClipsListSize(ElementsCollection listCollection, int minutes) throws InterruptedException {
        int seconds = minutes * 6;
        while (seconds != 0) {
            try {
                Selenide.refresh();
                videosHeader.should(visible);
                Selenide.sleep(10000);
                if (listCollection.size() > 0) {
                    return true;
                }
            } catch (NoSuchElementException | ElementNotFound e) {
                logger.info("Element not found in second " + seconds);
            }
            seconds--;
        }
        return false;
    }

    public boolean checkIfClipIsGenerated(int minutes) throws InterruptedException {
        int seconds = minutes * 6;
        while (seconds != 0) {
            try {
                Selenide.refresh();
                videosHeader.should(visible);
                Selenide.sleep(10000);
                if (!clipsListComponent.getText().contains("Generating clip")) {
                    return true;
                }
            } catch (NoSuchElementException | ElementNotFound e) {
                logger.info("Clips still not generated in second " + seconds);
            }
            seconds--;
        }
        return false;
    }

    public void playClip() {
        clipsItems.shouldHave(CollectionCondition.sizeGreaterThan(0)).get(clipsItems.size() - 1).$("th").click();
        videoPlayer.getPlayButton().click();
    }
}
