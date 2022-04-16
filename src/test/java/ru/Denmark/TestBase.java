package ru.Denmark;

import Config.Credential;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        //String login = System.getProperty("login");
        //String password = System.getProperty("password");
        //String url = System.getProperty("url");
        //String remoteUrl = "https://" + login + ":" + password + "@" + url;
        //Configuration.remote = remoteUrl;*/
        //String remoteUrl = System.getProperty("remoteUrl"); //Получаем значение из параметров Jenkins
        String user = Credential.config.user();
        String password = Credential.config.password();
        String remoteURL = Credential.config.remoteURL();
        Configuration.remote = format("https://%s:%s@%s", user, password, remoteURL);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
