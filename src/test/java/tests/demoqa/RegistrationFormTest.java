package tests.demoqa;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTest extends TestBase{

    @Test
    void practiceFormTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открытие страницы регистрации студента", () -> {
                    open("/automation-practice-form");
                    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
                    attachment("Source", webdriver().driver().source());
                });
        step("Скрытие баннеров с рекламой", () -> {
                    executeJavaScript("$('footer').remove()");
                    executeJavaScript("$('#fixedban').remove()");
                });

        step("Заполнение формы", () -> {
                    $("#firstName").setValue("Valentine"); //First Name
                    $("[id=lastName]").setValue("Goncharov"); //Last Name
                    $("#userEmail").setValue("vall@mail.ru"); //eMail
                    $("#genterWrapper").$(byText("Male")).click(); //Gender
                    $("#userNumber").setValue("7896543210"); //Mobile(10 Digits)
                    $("#dateOfBirthInput").click();
                    $(".react-datepicker__month-select").selectOption("January");
                    $(".react-datepicker__year-select").selectOption("1991");
                    $(".react-datepicker__day--027:not(.react-datepicker__year-select").click();
                    $("#hobbiesWrapper").$(byText("Sports")).click();
                    $("#subjectsInput").setValue("Economics").pressEnter(); //Subjects
                    $("#hobbies-checkbox-1").doubleClick(); //Hobbies (?)
                    $("#uploadPicture").uploadFromClasspath("1.png");
                    $("#currentAddress").setValue("Avenue, str"); //Current Address
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
                });
        step("Заполняем последний пункт и делаем скриншот", () -> {
                    $("#state").click();
                    $("#stateCity-wrapper").$(byText("NCR")).click();//xpath its better (?)
                    $("#city").click();
                    $("#stateCity-wrapper").$(byText("Delhi")).click();
                });
        step("Подтверждение данных путем нажатия кнопки", () -> {
                    $("#submit").click(); //Submit
                });

        step("Проверка данных студента", () -> {
            $(".table-responsive").shouldHave(text("Valentine Goncharov")); //Check out Student Name
            $(".table-responsive").shouldHave(text("vall@mail.ru")); //Check out Student Email
            $(".table-responsive").shouldHave(text("Male")); //Check out Gender
            $(".table-responsive").shouldHave(text("7896543210")); //Check out Mobile
            $(".table-responsive").shouldHave(text("27 January,1991")); //Check out Date of Birth
            $(".table-responsive").shouldHave(text("Economics")); //Check out Subjects
            $(".table-responsive").shouldHave(text("Sports")); //Check out Hobbies
            $(".table-responsive").shouldHave(text("1.png")); //Check out Picture
            $(".table-responsive").shouldHave(text("Avenue, str")); //Check out Address
            $(".table-responsive").shouldHave(text("NCR Delhi")); //Check out State and City
            $("#closeLargeModal").click();
        });
    }
}
