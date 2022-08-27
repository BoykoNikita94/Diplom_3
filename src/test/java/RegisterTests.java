import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.Test;
import ru.stellarburgers.nomoreparties.site.locators.LoginPage;
import ru.stellarburgers.nomoreparties.site.locators.MainPage;
import ru.stellarburgers.nomoreparties.site.locators.ProfilePage;
import ru.stellarburgers.nomoreparties.site.locators.RegisterPage;

import java.util.Locale;

import static org.junit.Assert.assertTrue;

public class RegisterTests {

    // Успешная регистрация
    @Test
    public void newUserRegisterShouldBeSuccess() {
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

        //Проверяем что данные в личном кабинете соответствуют тем, с которыми создавали пользователя
        String actualName = name;
        String expectedName = profilePage.getUserNameFieldText();
        String actualLogin = email.toLowerCase(Locale.ROOT);
        String expectedLogin = profilePage.getUserLoginFieldText();
        assertTrue(expectedName.contains(actualName));
        assertTrue(expectedLogin.contains(actualLogin));
        Selenide.closeWebDriver();
    }

    // Проверка текста ошибки при вводе некорректного пароля при регистрации
    @Test
    public void checkErrorMessageForInvalidPassword() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
        Configuration.startMaximized = true;
        Selenide.open(MainPage.URL);

        // Создаем объекты классов страниц
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // Создаем тестового пользователя с невалидным паролем
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.name().firstName() + "@mail.ru";
        String invalidPassword = String.valueOf(faker.random().nextInt(5));

        // Создаем нового пользователя
        mainPage.signInButtonClick();
        loginPage.registerButtonClick();
        registerPage.fillNewUserInfo(
                name,
                email,
                invalidPassword);
        Selenide.sleep(200);
        registerPage.registerButtonClick();
        Selenide.sleep(200);

        // Проверяем ошибку при вводе невалидного пароля
        String actualErrorMessage = "Некорректный пароль";
        String expectedErrorMessage = registerPage.getErrorMessageForInvalidPasswordText();
        assertTrue(expectedErrorMessage.contains(actualErrorMessage));
        Selenide.closeWebDriver();
    }
}