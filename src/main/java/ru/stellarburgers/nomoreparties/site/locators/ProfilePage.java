package ru.stellarburgers.nomoreparties.site.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    // Кнопка перехода в конструктор
    private SelenideElement constructorButton = $(byXpath(".//a[@href='/']"));

    // Кнопка логотипа Stellar Burgers
    private SelenideElement logoButton = $(byClassName("AppHeader_header__logo__2D0X2"));

    // Кнопка выхода из аккаунта
    private SelenideElement signOutButton = $(byCssSelector("button[class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']"));

    // Поле с именем пользователя
    private SelenideElement userNameField = $(byXpath("//*[@id=\"root\"]/div/main/div/div/div/ul/li[1]/div/div/input"));

    // Поле с логином пользователя
    private SelenideElement userLoginField = $(byXpath("//*[@id=\"root\"]/div/main/div/div/div/ul/li[2]/div/div/input"));

    // Метод клика по кнопке перехода в конструктор
    public void constructorButtonClick() {
        constructorButton.click();
    }

    // Метод клика по логотипу Stellar Burgers
    public void logoButtonClick() {
        logoButton.click();
    }

    // Метод клика по кнопке выхода из аккаунта
    public void signOutButtonClick() {
        signOutButton.click();
    }

    //Метод получения имени пользователя
    public String getUserNameFieldText() {
        return userNameField.getAttribute("value");
    }

    //Метод получения логина пользователя
    public String getUserLoginFieldText() {
        return userLoginField.getAttribute("value");
    }

}