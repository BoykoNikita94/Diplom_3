import api.client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import models.UserLoginRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.nomoreparties.site.locators.LoginPage;
import ru.stellarburgers.nomoreparties.site.locators.MainPage;
import ru.stellarburgers.nomoreparties.site.locators.ProfilePage;
import ru.stellarburgers.nomoreparties.site.locators.RegisterPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertTrue;

public class RegisterTests {

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

    // Успешная регистрация
    @Test
    public void newUserRegisterShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        // Создаем нового пользователя
        mainPage.signInButtonClick();
        loginPage.registerButtonClick();
        registerPage.fillNewUserInfo(
                name,
                email,
                password);
        registerPage.waitForLoadRegisterPage();
        registerPage.registerButtonClick();

        // Логинимся под новым пользователем
        loginPage.waitForLoadLoginPage();
        loginPage.fillUserInfo(
                email,
                password);
        loginPage.signInButtonClick();
        mainPage.personalAreaButtonClick();

        //Проверяем что данные в личном кабинете соответствуют тем, с которыми создавали пользователя
        String actualName = name;
        String expectedName = profilePage.getUserNameFieldText();
        String actualLogin = email.toLowerCase(Locale.ROOT);
        String expectedLogin = profilePage.getUserLoginFieldText();
        assertTrue(expectedName.contains(actualName));
        assertTrue(expectedLogin.contains(actualLogin));
    }

    // Проверка текста ошибки при вводе некорректного пароля при регистрации
    @Test
    public void checkErrorMessageForInvalidPassword() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        // Создаем тестового пользователя с невалидным паролем
        Faker faker = new Faker();
        password = String.valueOf(faker.random().nextInt(5));

        // Создаем нового пользователя
        mainPage.signInButtonClick();
        loginPage.registerButtonClick();
        registerPage.fillNewUserInfo(
                name,
                email,
                password);
        registerPage.registerButtonClick();

        // Проверяем ошибку при вводе невалидного пароля
        String actualErrorMessage = "Некорректный пароль";
        String expectedErrorMessage = registerPage.getErrorMessageForInvalidPasswordText();
        assertTrue(expectedErrorMessage.contains(actualErrorMessage));
    }
}