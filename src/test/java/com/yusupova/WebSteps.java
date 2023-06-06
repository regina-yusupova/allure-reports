package com.yusupova;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открыть главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }
    @Step("Выполнить поиск репозитория {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }
    @Step("Перейти в репозиторий {repository}")
    public void openRepository(String repository) {
        $(linkText(repository)).click();
        $("#repository-container-header").shouldBe(visible, Duration.ofSeconds(30));
    }
    @Step("Открыть разедел Issues")
    public void openIssueTab() {
        $(partialLinkText("Issues")).click();
    }
    @Step("Проверить, что issue_2 назвается {issueName}")
    public void issueShouldHaveName(String issueName) {
        $(("#issue_2")).$("a").shouldHave(Condition.text(issueName));
    }
}
