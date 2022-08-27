import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.Test;
import ru.stellarburgers.nomoreparties.site.locators.LoginPage;
import ru.stellarburgers.nomoreparties.site.locators.MainPage;
import ru.stellarburgers.nomoreparties.site.locators.ProfilePage;
import ru.stellarburgers.nomoreparties.site.locators.RegisterPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTests {

    // Переход по клику на конструктор
    @Test
    public void transitionThroughConstructorButtonShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        Configuration.startMaximized = true;
        Selenide.open(MainPage.URL);

        // Создаем объекты классов страниц
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        ProfilePage profilePage = new ProfilePage();

        // Создаем тестового пользователя
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.name().firstName() + "@mail.ru";
        String password = faker.internet().password();

        // Создаем нового пользователя
        mainPage.signInButtonClick();
        loginPage.registerButtonClick();
        registerPage.fillNewUserInfo(
                name,
                email,
                password);
        registerPage.registerButtonClick();
        // Ожидания добавлены т.к. иначе тесты падают (особенности быстродействия ресурса Stellar Burgers)
        Selenide.sleep(200);

        // Логинимся под новым пользователем
        loginPage.fillUserInfo(
                email,
                password);
        loginPage.signInButtonClick();
        mainPage.personalAreaButtonClick();
        Selenide.sleep(2000);

        // Проверяем переход в конструктор из личного кабинета
        profilePage.constructorButtonClick();
        String expectedButtonText = "Оформить заказ";
        String actualButtonText = mainPage.signInButtonText();
        assertTrue(expectedButtonText.contains(actualButtonText));
        Selenide.closeWebDriver();
    }

    // Переход по клику на логотип Stellar Burgers
    @Test
    public void transitionThroughLogoButtonShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        Configuration.startMaximized = true;
        Selenide.open(MainPage.URL);

        // Создаем объекты классов страниц
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        ProfilePage profilePage = new ProfilePage();

        // Создаем тестового пользователя
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.name().firstName() + "@mail.ru";
        String password = faker.internet().password();

        // Создаем нового пользователя
        mainPage.signInButtonClick();
        loginPage.registerButtonClick();
        registerPage.fillNewUserInfo(
                name,
                email,
                password);
        registerPage.registerButtonClick();
        Selenide.sleep(200);

        // Логинимся под новым пользователем
        loginPage.fillUserInfo(
                email,
                password);
        loginPage.signInButtonClick();
        mainPage.personalAreaButtonClick();
        Selenide.sleep(2000);

        // Проверяем переход при клике по лого Stellar Burgers
        profilePage.logoButtonClick();
        String expectedButtonText = "Оформить заказ";
        String actualButtonText = mainPage.signInButtonText();
        assertTrue(expectedButtonText.contains(actualButtonText));
        Selenide.closeWebDriver();
    }

    // Переходы по вкладкам конструктора
    @Test
    public void transitionsThroughConstructorTabsShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        Configuration.startMaximized = true;
        Selenide.open(MainPage.URL);

        // Создаем объекты классов страниц
        MainPage mainPage = new MainPage();

        // Проверка перехода по вкладке "Начинки"
        mainPage.fillingTabClick();
        SelenideElement fillingsHeader = mainPage.fillingsName();
        String actualFillingsHeaderText = fillingsHeader.getText();
        String expectedFillingsHeaderText = "Начинки";
        assertTrue(actualFillingsHeaderText.contains(expectedFillingsHeaderText));
        assertTrue(fillingsHeader.isDisplayed());
        Selenide.sleep(2000);

        // Проверка перехода по вкладке "Соусы"
        mainPage.sauceTabClick();
        SelenideElement saucesHeader = mainPage.saucesName();
        String actualSaucesHeaderText = saucesHeader.getText();
        String expectedSaucesHeaderText = "Соусы";
        assertTrue(actualSaucesHeaderText.contains(expectedSaucesHeaderText));
        assertTrue(saucesHeader.isDisplayed());
        Selenide.sleep(2000);

        // Проверка перехода по вкладке "Булки"
        mainPage.bunTabClick();
        SelenideElement bunsHeader = mainPage.bunsName();
        String actualBunsHeaderText = bunsHeader.getText();
        String expectedBunsHeaderText = "Булки";
        assertTrue(actualBunsHeaderText.contains(expectedBunsHeaderText));
        assertTrue(bunsHeader.isDisplayed());
        Selenide.closeWebDriver();
    }

}