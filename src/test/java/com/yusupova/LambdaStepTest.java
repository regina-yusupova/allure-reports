package com.yusupova;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.*;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepTest extends TestBase {

    private final static String REPOSITORY = "qa-guru/knowledge-base";
    private final static String ISSUE_NAME = "Need to change from 'JUnit' to 'Selenide'";

    @Test
    @DisplayName("Проверка соответствия названия Issue в репозитории")
    public void testGithub() {
        feature("Issues");
        story("Вкладка Issues");
        label("Owner", "regina yusupova");
        label("Severity", SeverityLevel.NORMAL.toString());
        step("Открыть главную страницу", () -> {
            open("https://github.com");
            takeScreenshot();
        });
        step("Выполнить поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Перейти в репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
            $("#repository-container-header").shouldBe(visible, Duration.ofSeconds(30));
        });
        step("Открыть разедел Issues", () -> {
            $(partialLinkText("Issues")).click();
            takeScreenshot();
        });
        step("Проверить, что issue_2 назвается " + ISSUE_NAME, () -> {
            $(("#issue_2")).$("a").shouldHave(Condition.text(ISSUE_NAME));
        });
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
