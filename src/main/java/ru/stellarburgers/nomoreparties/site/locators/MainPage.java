package ru.stellarburgers.nomoreparties.site.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    // Адрес страницы Stellar Burgers
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    //Кнопка входа в личный кабинет
    private SelenideElement personalAreaButton = $(byLinkText("Личный Кабинет"));

    //Кнопка входа в аккаунт на главной странице
    private SelenideElement signInButton = $(byCssSelector("button[class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']"));

    //Вкладка ассортимента булок
    private SelenideElement bunTab = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]/span"));

    //Вкладка ассортимента соусов
    private SelenideElement sauceTab = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]/span"));

    //Вкладка ассортимента начинок
    private SelenideElement fillingTab = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]/span"));

    // Заголовок начинок
    private SelenideElement fillingsHeader = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]"));

    // Заголовок соусов
    private SelenideElement saucesHeader = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[2]"));

    // Заголовок булок
    private SelenideElement bunsHeader = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]"));

    //Метод клика по кнопке входа в личный кабинет
    public void personalAreaButtonClick() {
        personalAreaButton.click();
    }

    //Метод клика по кнопке входа в аккаунт
    public void signInButtonClick() {
        signInButton.click();
    }

    // Метод получения текста кнопки входа в аккаунт
    public String signInButtonText() {
        return signInButton.getText();
    }

    // Метод клика по вкладке ассортимента булок
    public void bunTabClick() {
        bunTab.click();
    }

    // Метод клика по вкладке ассортимента соусов
    public void sauceTabClick() {
        sauceTab.click();
    }

    // Метод клика по вкладке ассортимента начинок
    public void fillingTabClick() {
        fillingTab.click();
    }

    // Метод получения заголовка начинок
    public SelenideElement fillingsName() {
        return fillingsHeader;
    }

    // Метод получения заголовка соусов
    public SelenideElement saucesName() {
        return saucesHeader;
    }

    // Метод получения заголовка булок
    public SelenideElement bunsName() {
        return bunsHeader;
    }

}