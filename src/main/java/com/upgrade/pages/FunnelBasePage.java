package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FunnelBasePage extends BasePage {

    @FindBy(linkText = "Sign Out")
    private WebElement signOut;

    @FindBy(css = "label.header-nav__toggle")
    private WebElement menu;

    public FunnelBasePage(WebDriver driver) {
        super(driver);
    }

    public SignOutPage clickSignOut() {
        click(menu);
        waitForWebElement(signOut);
        click(signOut);
        return new SignOutPage(driver);
    }

}