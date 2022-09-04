package ru.stellarburgers.nomoreparties.site.locators;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    // Адрес страницы Stellar Burgers
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    //Кнопка входа в личный кабинет
    private SelenideElement personalAreaButton = $(byXpath(".//a[@href='/account']"));

    //Кнопка входа в аккаунт на главной странице
    private SelenideElement signInButton = $(byCssSelector("button[class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']"));

    //Вкладка ассортимента булок
    private SelenideElement bunTab = $(byCssSelector("section.BurgerIngredients_ingredients__1N8v2 div:nth-child(2) div:nth-child(1)"));

    //Вкладка ассортимента соусов
    private SelenideElement sauceTab = $(byCssSelector("section.BurgerIngredients_ingredients__1N8v2 div:nth-child(2) div:nth-child(2)"));

    //Вкладка ассортимента начинок
    private SelenideElement fillingTab = $(byCssSelector("section.BurgerIngredients_ingredients__1N8v2 div:nth-child(2) div:nth-child(3)"));

    private SelenideElement selectedCategory = $(byCssSelector(".tab_tab_type_current__2BEPc"));

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

    public SelenideElement getSelectedCategory() {
        return selectedCategory;
    }

}