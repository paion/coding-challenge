package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.InvocationTargetException;

public class FunnelBasePage extends BasePage {

    @FindBy(linkText = "Sign Out")
    private WebElement signOut;

    @FindBy(css = "label.header-nav__toggle")
    private WebElement menu;

    public FunnelBasePage(WebDriver driver) {
        super(driver);
    }

    public <T> T clickSignOut(Class<T> type) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        click(menu);
        waitForWebElement(signOut);
        click(signOut);
        return type.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

}