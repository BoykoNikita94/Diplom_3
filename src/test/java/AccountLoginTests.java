import api.client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import models.CreateUserRequest;
import models.UserLoginRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.nomoreparties.site.locators.*;

import java.util.Locale;

import static org.junit.Assert.assertTrue;

public class AccountLoginTests {

    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();
    private final RegisterPage registerPage = new RegisterPage();
    private final ProfilePage profilePage = new ProfilePage();
    private final ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
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

    // Вход через кнопку в форме регистрации
    @Test
    public void loginThroughRegisterPageButtonShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        mainPage.signInButtonClick();
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

        // Проверяем разлогин
        profilePage.signOutButtonClick();
        profilePage.logoButtonClick();
        String expectedSignInButtonText = "Войти в аккаунт";
        String actualSignInButtonText = mainPage.signInButtonText();
        assertTrue(expectedSignInButtonText.contains(actualSignInButtonText));
    }

    // Вход через кнопку в форме восстановления пароля
    @Test
    public void loginThroughForgotPasswordPageButtonShouldBeSuccess() {
        // Настройка запуска тестов в Яндекс.Браузере
//        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");

        mainPage.signInButtonClick();
        loginPage.forgotPasswordButtonClick();
        forgotPasswordPage.signInButtonClick();
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

        // Проверяем разлогин
        profilePage.signOutButtonClick();
        profilePage.logoButtonClick();
        String expectedSignInButtonText = "Войти в аккаунт";
        String actualSignInButtonText = mainPage.signInButtonText();
        assertTrue(expectedSignInButtonText.contains(actualSignInButtonText));
    }
}