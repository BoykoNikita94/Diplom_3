package ru.stellarburgers.nomoreparties.site.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // Поле ввода e-mail
    private SelenideElement emailField = $(byName("name"));

    // Поле ввода пароля
    private SelenideElement passwordField = $(byName("Пароль"));

    // Кнопка входа в аккаунт
    private SelenideElement signInButton = $(byCssSelector("button[class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']"));

    // Кнопка восстановления пароля
    private SelenideElement forgotPasswordButton = $(byLinkText("Восстановить пароль"));

    // Кнопка регистрации
    private SelenideElement registerButton = $(byLinkText("Зарегистрироваться"));

    // Метод заполнения поля e-mail
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    // Метод заполнения поля пароля
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    // Метод клика по кнопке входа в аккаунт
    public void signInButtonClick() {
        signInButton.click();
    }

    // Метод клика по кнопке восстановления пароля
    public void forgotPasswordButtonClick() {
        forgotPasswordButton.click();
    }

    // Метод клика по кнопке регистрации
    public void registerButtonClick() {
        registerButton.click();
    }

    // Метод заполнения данных пользователя
    public void fillUserInfo(String email, String password) {
        setEmail(email);
        setPassword(password);
    }
}