package com.upgrade.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;

@Log4j
public class SelectOfferPage extends FunnelBasePage {

    @FindBy(css = "[data-auto='getDefaultLoan']")
    private WebElement continueBtn;

    @FindBy(css = "[data-auto='userLoanAmount']")
    private WebElement userLoanAmount;

    @FindBy(css = "[data-auto='offer-card-content-submit'] [data-auto='defaultMonthlyPayment']")
    private WebElement monthlyPayment;

    @FindBy(css = "[data-auto='offer-card-content-submit'] [data-auto='defaultLoanTerm'] div")
    private WebElement loanTerm;

    @FindBy(css = "[data-auto='offer-card-content-submit'] [data-auto='defaultLoanInterestRate'] div")
    private WebElement loanInterestRate;

    @FindBy(css = "[data-auto='offer-card-content-submit'] [data-auto='defaultAPR']")
    private WebElement apr;

    public SelectOfferPage(WebDriver driver) {
        super(driver);
        waitForWebElements(Arrays.asList(continueBtn));
    }

    public SelectOfferPage verifyDefaultFirstOffer(){
        waitForWebElement(userLoanAmount);
        Assert.assertNotNull(userLoanAmount.getText(), msg("approved loan amount", userLoanAmount));
        Assert.assertTrue(monthlyPayment.getText().contains("$"), msg("monthly payment", monthlyPayment));
        Assert.assertTrue(loanTerm.getText().contains(" Month"), msg("month term", loanTerm));
        Assert.assertTrue(loanInterestRate.getText().contains("%"), msg("interest rate", loanInterestRate));
        Assert.assertTrue(apr.getText().contains("%"), msg("APR", apr));
        return new SelectOfferPage(driver);
    }

    private String msg(String value, WebElement actualText){
        return String.format("There is no default %s found. Actual Text: '%s' | ", value, actualText.getText());
    }

}
