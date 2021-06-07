package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AllureTest extends TestBase {

    public static final String githubUrl = "https://github.com",
            searchAllure = "allure",
            allureRepository = "allure-framework/allure2",
            issueNumber = "#1303";

    private WebSteps steps = new WebSteps();

    @Test
    public void testIssueSearch() {
        open(githubUrl);
        $("[name=\"q\"]").val(searchAllure).pressEnter();
        $(By.linkText(allureRepository)).click();
        $(byText("Issues")).click();
        $(withText(issueNumber)).shouldBe(Condition.visible);
    }

    @Test
    @Owner("Daria")
    @Feature("Issue")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "GitHub", url = githubUrl)
    @DisplayName("Validation by the issue number with Lambda Steps")
    public void testIssueSearchWithLambdaSteps() {
        step("Open website: " + githubUrl, (s) -> {
            s.parameter("url", githubUrl);
            open(githubUrl);
        });

        step("Search for repository: " + searchAllure, (s) -> {
            s.parameter("repository", searchAllure);
            $("[name=\"q\"]").val(searchAllure).pressEnter();
        });

        step("Select the repository: " + allureRepository, (s) -> {
            s.parameter("repository", allureRepository);
            $(By.linkText(allureRepository)).click();
        });

        step("Click on the issue", (s) -> {
            $(byText("Issues")).click();
        });

        step("Validate issue: " + issueNumber, (s) -> {
            s.parameter("Number of the issue", issueNumber);
            $(withText(issueNumber)).shouldBe(Condition.visible);
        });
    }

    @Test
    @Owner("Daria")
    @Feature("Issue")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "GitHub", url = githubUrl)
    @DisplayName("Validation by the issue number with Annotated Steps")
    public void issueSearchWithAnnotatedSteps() {
        steps.openMainPage(githubUrl);
        steps.makeScreenshot();
        steps.searchForRepository(searchAllure);
        steps.goToRepository(allureRepository);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(issueNumber);
    }

}
