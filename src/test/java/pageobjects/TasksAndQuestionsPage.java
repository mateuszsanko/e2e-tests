package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.Helper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TasksAndQuestionsPage {
    private SelenideElement testUrlInput = $("input#test-url");
    private ElementsCollection taskDescriptionTextArea = $$("textarea[data-tid='task-description']");
    private SelenideElement nextButton = $("a[data-tid='next-btn']");

    public TasksAndQuestionsPage() {

    }

    public SelenideElement getTestUrlInput() {
        return testUrlInput;
    }

    public ElementsCollection getTaskDescriptionTextArea() {
        return taskDescriptionTextArea;
    }

    public SelenideElement getNextButton() {
        return nextButton;
    }
}
