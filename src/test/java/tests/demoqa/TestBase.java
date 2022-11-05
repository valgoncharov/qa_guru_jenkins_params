package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.properties.Property;

public class TestBase {
    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = Property.browser();
        Configuration.browserSize = Property.browserSize();
        Configuration.browserVersion = Property.browserVersion();
        if (!Property.remoteUrl().equals("")){
            Configuration.remote = Property.remoteUrl();
        }

        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
    }


    @AfterEach
    void addAttachments(){
        Attach.attachScreenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
