package com.upgrade.pages;

import com.upgrade.pojos.loan.Borrower;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginInfoPage extends BasePage {

    @FindBy(name = "username")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(linkText = "Terms of Use")
    private WebElement termsOfUse;

    @FindBy(linkText = "ESIGN Act Consent")
    private WebElement eSIGNActConsent;

    @FindBy(linkText = "Credit Profile Authorization")
    private WebElement creditProfileAuthorization;

    @FindBy(linkText = "Privacy Policy")
    private WebElement privacyPolicy;

    @FindBy(css = "[data-auto='submitPersonalInfo']")
    private WebElement checkYourRateBtn;

    @FindBy(name = "agreements")
    private WebElement agreements;

    public LoginInfoPage(WebDriver driver) {
        super(driver);
        waitForWebElement(email);
    }

    public <T> T enterLoginDetails(Borrower randomPerson, T type) {
        type(email, randomPerson.getEmail());
        type(password, randomPerson.getPassword());
        selectTermsOfUse();
        click(checkYourRateBtn);
        waitForPage();
        return type;
    }

    public LoginInfoPage selectTermsOfUse() {
        javaScriptClick(agreements);
        return this;
    }
}
