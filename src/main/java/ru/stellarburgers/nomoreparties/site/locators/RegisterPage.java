package ru.stellarburgers.nomoreparties.site.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    // Поле ввода имени
    private SelenideElement nameField = $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input"));

    // Поле ввода e-mail
    private SelenideElement emailField = $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input"));

    // Поле ввода пароля
    private SelenideElement passwordField = $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input"));

    // Кнопка регистрации нового пользователя
    private SelenideElement registerButton = $(byCssSelector("button[class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']"));

    // Кнопка входа в аккаунт
    private SelenideElement signInButton = $(byLinkText("Войти"));

    // Текст ошибки при вводе некорректного пароля
    private SelenideElement errorMessageForInvalidPassword = $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p"));

    // Метод заполнения поля имени
    public void setName(String name) {
        nameField.setValue(name);
    }

    // Метод заполнения поля e-mail
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    // Метод заполнения поля пароля
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    // Метод клика по кнопке регистрации нового пользователя
    public void registerButtonClick() {
        registerButton.click();
    }

    // Метод клика по кнопке входа в аккаунт
    public void signInButtonClick() {
        signInButton.click();
    }

    // Метод получения текста ошибки при вводе некорректного пароля
    public String getErrorMessageForInvalidPasswordText() {
        return errorMessageForInvalidPassword.getText();
    }

    // Метод заполнения данных для регистрации нового пользователя
    public void fillNewUserInfo(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

}