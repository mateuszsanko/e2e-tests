package pageobjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects.components.TestLinkModal;
import pageobjects.components.WelcomeModal;
import pageobjects.components.WorkspaceModal;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private WelcomeModal welcomeModal;
    private WorkspaceModal workspaceModal;
    private SelenideElement freeTrialMessage = $("div.free-trial-expiration-message");
    private SelenideElement menuDropdown = $("div.section-right li.dropdown a.dropdown-toggle");
    private SelenideElement accountButton = $("ul.dropdown-menu").$$("li").filter(matchesText("Account")).get(0);
    private SelenideElement createNewStudyButton = $("a[data-tid='create-new-study-btn']");
    private SelenideElement shareTestButton = $("button[data-tid='share-test-btn']");
    private SelenideElement workspaceSelect = $("div.Select-control");
    private ElementsCollection studyCards = $$("div[data-tid^='test-']");
    private SelenideElement confirmDelete = $("button[data-tid='confirm-btn']");
    private TestLinkModal testLinkModal;


    public DashboardPage() {
        this.welcomeModal = new WelcomeModal();
        this.testLinkModal = new TestLinkModal();
        this.workspaceModal = new WorkspaceModal();
    }

    public WelcomeModal getWelcomeModal() {
        return welcomeModal;
    }

    public WorkspaceModal getWorkspaceModal() {
        return workspaceModal;
    }

    public void clickCreateNewWorkspaceButton() {
        workspaceSelect.hover();
        $("button[data-tid='create-workspace-btn']").click();
    }

    public SelenideElement upgradeThisAccountButton() {
        return freeTrialMessage.$("a");
    }

    public void goToAccount() {
        menuDropdown.click();
        accountButton.click();
    }

    public SelenideElement getCreateNewStudyButton() {
        return createNewStudyButton;
    }

    public void clickViewResults(int studyNumber) {
        studyCards.get(studyNumber - 1).should(visible).hover().$("button[data-tid='view-results-btn']").click();
    }

    public void deleteStudy(int studyNumber) {
        String studyTitle = studyCards.get(studyNumber - 1).should(visible).$("h4.test-title").getText();
        int numberOfStudies = studyCards.size();
        studyCards.get(studyNumber - 1).should(visible).hover().$("button[data-tid='delete-test-btn']").click();
        confirmDelete.should(visible).click();
        studyCards.shouldHave(CollectionCondition.sizeLessThan(numberOfStudies));
        for (int i = 0; i < studyCards.size(); i++) {
            studyCards.get(i).should(visible).$("h4.test-title").should(not(text(studyTitle)));
        }
    }

    public void duplicateStudy(int studyNumber) {
        String studyTitle = studyCards.get(studyNumber - 1).should(visible).$("h4.test-title").getText();
        int numberOfStudies = studyCards.size();
        studyCards.get(studyNumber - 1).should(visible).hover().$("button[data-tid='duplicate-test-btn']").click();
        studyCards.shouldHave(CollectionCondition.sizeGreaterThan(numberOfStudies));
        if (studyTitle.contains("Copy of")) {
            studyCards.get(0).should(visible).$("h4.test-title").should(text(studyTitle));
        } else {
            studyCards.get(0).should(visible).$("h4.test-title").should(text("Copy of " + studyTitle));
        }
    }

    public String getTestLink(int studyNumber) {
        String url = null;
        studyCards.get(studyNumber - 1).should(visible).hover().$("button[data-tid='share-test-btn']").click();
        testLinkModal.getComponent().should(visible);
        testLinkModal.getCopyButton().click();
        try {
            url = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
