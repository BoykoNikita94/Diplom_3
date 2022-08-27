import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.Test;
import ru.stellarburgers.nomoreparties.site.locators.*;

import java.util.Locale;

import static org.junit.Assert.assertTrue;

public class AccountLoginTests {

    // Вход через кнопку в форме регистрации
    @Test
    public void loginThroughRegisterPageButtonShouldBeSuccess() {
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
        loginPage.registerButtonClick();
        registerPage.signInButtonClick();

        // Логинимся под новым пользователем
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
        Configuration.startMaximized = true;
        Selenide.open(MainPage.URL);

        // Создаем объекты классов страниц
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        ProfilePage profilePage = new ProfilePage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

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
        loginPage.forgotPasswordButtonClick();
        forgotPasswordPage.signInButtonClick();

        // Логинимся под новым пользователем
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
        Selenide.closeWebDriver();
    }

}