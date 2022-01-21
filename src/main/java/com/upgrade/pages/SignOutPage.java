package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.upgrade.utilities.Constants.*;

public class SignOutPage extends BasePage {

    @FindBy(css = "[data-auto='logoutMessage']")
    private WebElement signOutText;

    public SignOutPage(WebDriver driver) {
        super(driver);
        waitForWebElement(signOutText);
    }

    public void verifySignOutPage(){
        textToBePresentInElement(signOutText, LOG_OUT_MSG);
    }
}
