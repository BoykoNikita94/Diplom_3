package ru.stellarburgers.nomoreparties.site.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {

    //Кнопка входа в аккаунт
    private SelenideElement signInButton = $(byLinkText("Войти"));

    //Метод клика по кнопке входа в аккаунт
    public void signInButtonClick() {
        signInButton.click();
    }

}