package pageobjects;

import com.codeborne.selenide.SelenideElement;
import pageobjects.components.LaunchStudyModal;

import static com.codeborne.selenide.Selenide.$;

public class TeamPage {
    private SelenideElement launchButton = $("button[data-tid='test-launch-btn']");
    private LaunchStudyModal launchStudyModal;

    public TeamPage() {
        this.launchStudyModal = new LaunchStudyModal();
    }

    public SelenideElement getLaunchButton() {
        return launchButton;
    }

    public LaunchStudyModal getLaunchStudyModal() {
        return launchStudyModal;
    }
}
