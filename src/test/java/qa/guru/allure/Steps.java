package qa.guru.allure;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.Keys.ENTER;

public class Steps{

    @Step("Открытие github")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Поиск репозитория {repository}")
    public void searchRepository(String repository) {
        $("[name = q").sendKeys(repository, ENTER);
    }

    @Step("Переход к репозиторию {repository}")
    public void openRepository(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Проверка отображения вкладки Issues")
    public void checkIssuesTab() {
        $("[data-tab-item=i1issues-tab]").shouldBe(visible);
    }
}
