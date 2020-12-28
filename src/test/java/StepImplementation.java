import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;
import com.thoughtworks.gauge.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.*;
import utils.Helper;
import utils.PropertiesManager;

import java.io.File;
import java.util.HashSet;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class StepImplementation {

    private HashSet<Character> vowels;
    private PropertiesManager prop;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private SignupPage signupPage;
    private AccountPage accountPage;
    private StudyDetailsPage studyDetailsPage;
    private TasksAndQuestionsPage tasksAndQuestionsPage;
    private OptionsPage optionsPage;
    private TeamPage teamPage;
    private SharePage sharePage;
    private UnmoderatedSessionPage unmoderatedSessionPage;
    private final static String HEADER_TEXT = "Welcome to UserZoom GO!";
    private Condition clickable;
    private Helper helper;
    private VideosAndClipsPage videosPage;
    private InsightsPage insightsPage;

    public StepImplementation() {
        this.insightsPage = new InsightsPage();
        this.teamPage = new TeamPage();
        this.optionsPage = new OptionsPage();
        this.prop = new PropertiesManager();
        this.loginPage = new LoginPage();
        this.dashboardPage = new DashboardPage();
        this.accountPage = new AccountPage();
        this.signupPage = new SignupPage();
        this.studyDetailsPage = new StudyDetailsPage();
        this.tasksAndQuestionsPage = new TasksAndQuestionsPage();
        this.sharePage = new SharePage();
        this.clickable = and("can be clicked", visible, enabled);
        this.unmoderatedSessionPage = new UnmoderatedSessionPage();
        this.helper = new Helper();
        this.videosPage = new VideosAndClipsPage();
    }

    @BeforeSpec
    public void setup() {
        ClassLoader classLoader = StepImplementation.class.getClassLoader();
        File extension = new File(classLoader.getResource("files/build.crx").getFile());
        if (prop.getProperty("browser").toLowerCase().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addExtensions(extension);
            options.addArguments("--use-fake-ui-for-media-stream");
            options.addArguments("--enable-usermedia-screen-capturing");
            options.addArguments("--auto-select-desktop-capture-source=Entire screen");
            WebDriver webDriver = new ChromeDriver(options);
            setWebDriver(webDriver);
        }
    }

    @AfterSpec
    public void teardown() {
        Selenide.closeWebDriver();
    }

    @Step("Vowels in English language are <vowelString>.")
    public void setLanguageVowels(String vowelString) {
        vowels = new HashSet<>();
        for (char ch : vowelString.toCharArray()) {
            vowels.add(ch);
        }
    }

    @Step("The word <word> has <expectedCount> vowels.")
    public void verifyVowelsCountInWord(String word, int expectedCount) {
        int actualCount = countVowels(word);
        assertThat(expectedCount).isEqualTo(actualCount);
    }

    @Step("Almost all words have vowels <wordsTable>")
    public void verifyVowelsCountInMultipleWords(Table wordsTable) {
        for (TableRow row : wordsTable.getTableRows()) {
            String word = row.getCell("Word");
            int expectedCount = Integer.parseInt(row.getCell("Vowel Count"));
            int actualCount = countVowels(word);

            assertThat(expectedCount).isEqualTo(actualCount);
        }
    }

    private int countVowels(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if (vowels.contains(ch)) {
                count++;
            }
        }
        return count;
    }

    @Step("Go to <http://www.google.pl> page")
    public void implementation1(String address) {
        Configuration.startMaximized = true;
        open(address);
        if (!address.equals(url())) {
            open(address);
        }
        ;
    }

    @Step("Log in to UZ Go")
    public void loginToUzGo() {
        loginPage.login();
        try {
            dashboardPage.getWelcomeModal().getComponent().shouldBe(visible);
            dashboardPage.getWelcomeModal().getHeader().shouldHave(text(HEADER_TEXT));
        } catch (ElementNotFound e) {
            Logger.info("Modal has not been visible");
        }
    }

    @Step("Choose <1> modal option")
    public void chooseNthModalOption(int optionNumber) {
        try {
            dashboardPage.getWelcomeModal().getComponent().should(visible);
            dashboardPage.getWelcomeModal().getCheckboxList().get(optionNumber - 1).click();
            dashboardPage.getWelcomeModal().getSubmitButton().click();
        } catch (NoSuchElementException | ElementNotFound e) {
            Logger.info("Modal has not been visible");
        }

    }

    @Step("Go to account section")
    public void goToAccount() {
        dashboardPage.goToAccount();
    }

    @Step("Register credit card")
    public void implementation2() {
        accountPage.addTestCard();
    }

    @Step("Upgrade plan to <Team>")
    public void implementation3(String planName) {
        accountPage.upgradePlan(planName);
    }

    @Step("Signup with new user")
    public void signupWithNewUser() {
        loginPage.getSignupButton().click();
        signupPage.signupUser();
        dashboardPage.getWelcomeModal().getHeader().should(visible).should(matchesText("Welcome to UserZoom GO!"));
    }

    @Step("Click Create New Study button on Dashboard page")
    public void clickCreateNewStudyButton() {
        dashboardPage.getCreateNewStudyButton().click();
    }


    @Step("Name your study on New Study Page")
    public void nameYourStudy() {
        studyDetailsPage.getTestNameInput().sendKeys(studyDetailsPage.generateStudyName());
    }

    @Step("Choose Unmoderated study type on New Study Page")
    public void chooseUnmoderated() {
        studyDetailsPage.getUnmoderatedCard().should(visible);
        studyDetailsPage.getUnmoderatedCard().click();
        sleep(500);
    }

    @Step("Choose Test on my own users on New Study Page")
    public void chooseByo() {
        studyDetailsPage.getTestOnMyOwnCard().should(visible);
        studyDetailsPage.getTestOnMyOwnCard().click();
        sleep(500);
    }

    @Step("Choose Desktop on New Study Page")
    public void chooseDesktop() {
        studyDetailsPage.getDesktopCard().should(visible);
        studyDetailsPage.getDesktopCard().click();
        sleep(500);
    }

    @Step("Click Next")
    public void clickNext() {
        studyDetailsPage.getNextButton().should(visible);
        studyDetailsPage.getNextButton().click();
    }

    @Step("Set <http://www.google.pl> in Url field on Tasks and Questions page")
    public void setUrlFieldOnTasksAndQuestionsPage(String url) {
        tasksAndQuestionsPage.getTestUrlInput().should(visible).sendKeys(url);
    }

    @Step("Set <e2e task description> in <1> Task description field on Tasks and Questions page")
    public void setTaskDescription(String description, int fieldNumber) {
        tasksAndQuestionsPage.getTaskDescriptionTextArea().get(fieldNumber - 1).should(visible).sendKeys(description);
    }

    @Step("Click Next on Task and Questions page")
    public void clickNextOnTaskAndQuestions() {
        tasksAndQuestionsPage.getNextButton().should(visible).click();
    }

    @Step("Choose default limited responses on Options page")
    public void defaultLimitedResponses() {
        optionsPage.getLimitedResponsesRadioButton().click();
    }

    @Step("Choose to not prompt for Name and Email on Options page")
    public void notPromptForNameAndEmail() {
        optionsPage.getDoNotPromptForEmailRadioButton().click();
    }

    @Step("Click Next on Options page")
    public void nextOnOptionPage() {
        optionsPage.getNextButton().should(enabled).click();
    }

    @Step("Click Launch button on Team page")
    public void launchButtonOnTeamPage() {
        teamPage.getLaunchButton().click();
        teamPage.getLaunchStudyModal().getComponent().should(visible);
        teamPage.getLaunchStudyModal().getConfirmButoon().click();
    }

    @Step("Click Back to dashboard on Share page")
    public void clickBackToDashboard() {
        sharePage.getBackToDashboardButton().should(visible).click();
    }

    @Step("Create workspace and select <1> user as an owner")
    public void createWorkspaceAndSelectNthUser(int userNumber) {
        dashboardPage.clickCreateNewWorkspaceButton();
        dashboardPage.getWorkspaceModal().create(String.valueOf(helper.generateTimestamp()), userNumber - 1);
    }

    @Step("Check recordings for <1> study")
    public void checkRecordings(int cardNumber) throws InterruptedException {
        dashboardPage.clickViewResults(cardNumber);
        assertTrue("Recordings are not visible ", videosPage.checkRecordingsOrClipsListSize(videosPage.getRecordingsList(), 5));
    }

    @Step("Perform unmoderated test for <1> study")
    public void performUnmoderatedStudyForNStudy(int cardNumber) {
        unmoderatedSessionPage.performUnmoderatedSessionForNthCard(cardNumber);
    }

    @Step("Add notes flags and hashtags to <1> study")
    public void addNotesFlagsAndHashtagsToStudy(int studyNumber) {
        dashboardPage.clickViewResults(studyNumber);
        videosPage.getRecordingNumber(0).click();
        videosPage.addNote(6, "Note");
        videosPage.addFlag(3);
        videosPage.addHashtag(3, "#hash");
    }

    @Step("Edit notes flags and hashtags to <1> study")
    public void editNotesFlagsAndHashtagsToStudy(int studyNumber) {
        dashboardPage.clickViewResults(studyNumber);
        videosPage.getRecordingNumber(0).click();
        videosPage.editNote(3);
    }

    @Step("Delete notes flags and hashtags to <1> study")
    public void deleteNotesFlagsAndHashtagsToStudy(int studyNumber) {
        dashboardPage.clickViewResults(studyNumber);
        videosPage.getRecordingNumber(0).click();
        videosPage.deleteItemFromList(3);
    }

    @Step("Add hashtags to report from <1> study")
    public void addHashtagsToReportFrom(int studyNumber) {
        dashboardPage.clickViewResults(studyNumber);
        insightsPage.getInsightsTab().waitUntil(clickable, 8000).click();
        insightsPage.clearTextArea();
        insightsPage.addHashtags();
        insightsPage.checkHashtagsVideos(3);
    }

    @Step("Create clip for recording from <1> study")
    public void clipForRecording(int studyNumber) throws InterruptedException {
        dashboardPage.clickViewResults(studyNumber);
        videosPage.getRecordingNumber(0).click();
        videosPage.getVideoPlayer().createClip();
        assertTrue("Clips is not added to list ", videosPage.checkRecordingsOrClipsListSize(videosPage.getClipsItems(), 3));
        assertTrue("Clips is not generated ", videosPage.checkIfClipIsGenerated(5));
        videosPage.playClip();
    }

    @Step("Duplicate <1> study from Dashboard")
    public void duplicateStudyFromDashboard(int studyNumber) {
        dashboardPage.duplicateStudy(studyNumber);
    }

    @Step("Delete <1> study from Dashboard")
    public void deleteStudyFromDashboard(int studyNumber) {
        dashboardPage.deleteStudy(studyNumber);
    }
}
