package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects.components.UpdateCardModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AccountPage {
    private final String STRIPE_CARD_NUMBER = "4242424242424242";
    private final String STRIPE_CARD_CVC = "111";
    private SelenideElement addCardButton = $("a[name='change_card']");
    private ElementsCollection testPlanButtons = $$("button[name='update_plan']");
    private UpdateCardModal cardModal;
    private SelenideElement upgradePlanModal = $("div.modal-content");
    private SelenideElement submitButtonUpgradeModal = upgradePlanModal.$("button[data-tid='confirm-btn']");

    public AccountPage() {
        cardModal = new UpdateCardModal();
    }

    public void addTestCard() {
        addCardButton.click();
        cardModal.getCardNumberInput().sendKeys(STRIPE_CARD_NUMBER);
        cardModal.getSecurityCodeInput().sendKeys(STRIPE_CARD_CVC);
        cardModal.getMonthOptionByText("01").click();
        cardModal.getYearOptionByText("2022").click();
        cardModal.getSubmitButton().waitUntil(visible, 3000).click();
    }

    public void upgradePlan(String planName) {
        switch (planName) {
            case "Basic":
                testPlanButtons.get(0).click();
                submitButtonUpgradeModal.click();
                getCurrentPlan().shouldHave(text("Basic"));
                break;
            case "Experience":
                testPlanButtons.get(1).click();
                submitButtonUpgradeModal.click();
                getCurrentPlan().shouldHave(text("Experience"));
                break;
            case "Team":
                testPlanButtons.get(2).click();
                submitButtonUpgradeModal.click();
                getCurrentPlan().shouldHave(text("Team"));
                break;

        }
    }

    public SelenideElement getCurrentPlan() {
        return $("table.billing-details tr").$$("td").get(1);
    }
}
