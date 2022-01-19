package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

public class SignOutPage extends BasePage {

    private static final String logOutMsg = "You've been successfully logged out.\nSee you later.";

    @FindBy(css = "[data-auto='logoutMessage']")
    private WebElement signOutText;

    public SignOutPage(WebDriver driver) {
        super(driver);
        waitForWebElements(Arrays.asList(signOutText));
    }

    public void verifySignOutPage(){
        textToBePresentInElement(signOutText, logOutMsg);
    }
}
