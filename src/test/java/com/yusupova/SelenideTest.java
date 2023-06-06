package com.yusupova;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.partialLinkText;

public class SelenideTest extends TestBase {

    @Test
    public void testGithub() {
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").setValue("qa-guru/knowledge-base");
        $(".header-search-input").submit();
        $((".repo-list")).$("a").click();
        $("#repository-container-header").shouldBe(visible, Duration.ofSeconds(30));
        $(partialLinkText("Issues")).click();
        $(("#issue_2")).$("a").shouldHave(Condition.text("Need to change from 'JUnit' to 'Selenide'"));
    }
}
