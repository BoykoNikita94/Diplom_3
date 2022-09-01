import api.client.UserClient;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.CreateUserRequest;
import models.UserLoginRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.nomoreparties.site.locators.*;

import static org.junit.Assert.assertTrue;

public class ConstructorTests {

    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();
    private final RegisterPage registerPage = new RegisterPage();
    private final ProfilePage profilePage = new ProfilePage();
    private final UserClient userClient = new UserClient();
    private String email;
    private String password;
    private String name;

    @Before
    public void configuration() {
        Configuration.startMaximized = true;

        Faker faker = new Faker();
        name = faker.name().firstName();
        email = faker.name().firstName() + "@mail.ru";
        password = faker.internet().password();

        userClient.createUser(new CreateUserRequest(email, password, name));

        Selenide.open(MainPage.URL);
    }

    @After
    public void deleteUser() {
        String token = userClient.userGetToken(new UserLoginRequest(email, password));
        if (token != null && !token.equals("")) {
            userClient.deleteUser(token);
        }
        Selenide.closeWebDriver();
    }

    // Переход по клику на конструктор
    @Test
    public void transitionThroughConstructorButtonShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        mainPage.signInButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.fillUserInfo(
                email,
                password);
        loginPage.signInButtonClick();
        mainPage.personalAreaButtonClick();

        // Проверяем переход в конструктор из личного кабинета
        profilePage.constructorButtonClick();
        String expectedButtonText = "Оформить заказ";
        String actualButtonText = mainPage.signInButtonText();
        assertTrue(expectedButtonText.contains(actualButtonText));
    }

    // Переход по клику на логотип Stellar Burgers
    @Test
    public void transitionThroughLogoButtonShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        mainPage.signInButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.fillUserInfo(
                email,
                password);
        loginPage.signInButtonClick();
        mainPage.personalAreaButtonClick();

        // Проверяем переход при клике по лого Stellar Burgers
        profilePage.logoButtonClick();
        String expectedButtonText = "Оформить заказ";
        String actualButtonText = mainPage.signInButtonText();
        assertTrue(expectedButtonText.contains(actualButtonText));
    }

    // Переходы по вкладкам конструктора
    @Test
    public void transitionsThroughConstructorTabFillingsShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        mainPage.fillingTabClick();
        mainPage.getSelectedCategory().shouldBe(Condition.text("Начинки"));
    }

    @Test
    public void transitionsThroughConstructorTabSaucesShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        mainPage.sauceTabClick();
        mainPage.getSelectedCategory().shouldBe(Condition.text("Соусы"));
    }

    @Test
    public void transitionsThroughConstructorTabBunsShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        mainPage.fillingTabClick();
        Selenide.sleep(2000);
        mainPage.bunTabClick();
        mainPage.getSelectedCategory().shouldBe(Condition.text("Булки"));
    }

}