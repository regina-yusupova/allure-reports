package com.yusupova;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

@Owner("regina yusupova")
@Feature("Issues")
public class AnnotatedStepTest extends TestBase {

    private final static String REPOSITORY = "qa-guru/knowledge-base";
    private final static String ISSUE_NAME = "Need to change from 'JUnit' to 'Selenide'";

    @Test
    @Story("Вкладка Issues")
    @Link(name = "Главная страница", url = "https://github.com")
    @DisplayName("Проверка соответствия названия Issue в репозитории")
    @Severity(SeverityLevel.NORMAL)
    public void testGithub() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        takeScreenshot();
        steps.openIssueTab();
        steps.issueShouldHaveName(ISSUE_NAME);
        takeScreenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
