package qa.guru.allure;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.Keys.ENTER;

@Owner("Arpigo")
@Severity(NORMAL)
@Feature("Вкладка Issues")
@Story("Просмотр Issues")
public class HomeWorkTest {

    Steps steps = new Steps();
    public static final String REPOSITORY = "Arpigo/7_homework_Allure_Reports";

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        addListener("allure", new AllureSelenide());
    }

    @Test
    @DisplayName("Проверка наличия вкладки Issues, использование Selenide Listener")
    public void issueTabSelenideLogger() {
        parameter("Тест с", "Selenide Listener");

        open("https://github.com");
        $("[name = q").sendKeys(REPOSITORY, ENTER);
        $(linkText(REPOSITORY)).click();
        $("[data-tab-item=i1issues-tab]").shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка наличия вкладки Issues, Lambda шаги")
    public void issueTabLambdaSteps() {
        parameter("Тест с", "Lambda шаги");

        step("Открываем главную страницу github", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $("[name = q").sendKeys(REPOSITORY, ENTER);
        });

        step("Открываем репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Проверяем наличие вкладки Issues", () -> {
            $("[data-tab-item=i1issues-tab]").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверка наличия вкладки Issues, аннотация Steps")
    public void issueTabAnnotationsSteps() {
        parameter("Тест с", "аннотация Steps");

        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.checkIssuesTab();
    }
}

