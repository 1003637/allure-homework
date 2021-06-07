package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    @Step("Open url: {url}")
    public void openMainPage(String url) {
        open(url);
    }

    @Step("Search for repository: {repository}")
    public void searchForRepository(String repository) {
        $("[name=\"q\"]").val(repository).pressEnter();
    }

    @Step("Select the repository: {repository}")
    public void goToRepository(String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Click on the issue")
    public void openIssueTab() {
        $(byText("Issues")).click();
    }

    @Step("Validate issue: {issueNumber}")
    public void shouldSeeIssueWithNumber(String issueNumber) {
        $(withText(issueNumber)).shouldBe(Condition.visible);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return screenshot(OutputType.BYTES);
    }
}
