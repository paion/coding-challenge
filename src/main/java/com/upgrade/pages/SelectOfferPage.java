package com.upgrade.pages;

import com.upgrade.pojos.loan.Offer;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.*;
import static com.upgrade.utilities.Constants.*;

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
        waitForWebElement(continueBtn);
    }

    public SelectOfferPage verifyDefaultFirstOffer(Offer offer){
        waitForWebElement(userLoanAmount);
        assertNotNull(userLoanAmount.getText(), msg(APPROVED_LOAN_AMOUNT, userLoanAmount));
        assertTrue(monthlyPayment.getText().contains("$"), msg(MONTHLY_PAYMENT, monthlyPayment));
        assertTrue(loanTerm.getText().contains(" Month"), msg(LOAN_TERM, loanTerm));
        assertTrue(loanInterestRate.getText().contains("%"), msg(INTEREST_RATE, loanInterestRate));
        assertTrue(apr.getText().contains("%"), msg(APR, apr));

        offer.setLoanAmount(userLoanAmount.getText());
        offer.setMonthlyPayment(monthlyPayment.getText());
        offer.setLoanTerm(loanTerm.getText());
        offer.setInterestRate(loanInterestRate.getText());
        offer.setApr(apr.getText());

        return new SelectOfferPage(driver);
    }

    public SelectOfferPage verifyOfferAfterReLogin(Offer offerAfterAccountCreation, Offer offerAfterReLogin){
        assertEquals(offerAfterAccountCreation.getLoanAmount(), offerAfterReLogin.getLoanAmount(),  msg(APPROVED_LOAN_AMOUNT));
        assertEquals(offerAfterAccountCreation.getMonthlyPayment(), offerAfterReLogin.getMonthlyPayment(),  msg(MONTHLY_PAYMENT));
        assertEquals(offerAfterAccountCreation.getLoanTerm(), offerAfterReLogin.getLoanTerm(),  msg(LOAN_TERM));
        assertEquals(offerAfterAccountCreation.getInterestRate(), offerAfterReLogin.getInterestRate(),  msg(INTEREST_RATE));
        assertEquals(offerAfterAccountCreation.getApr(), offerAfterReLogin.getApr(),  msg(APR));
        return new SelectOfferPage(driver);
    }

    private String msg(String value, WebElement actualText){
        return String.format("%s is not found. Actual Text: '%s' | ", value, actualText.getText());
    }

    private String msg(String value){
        return String.format("%s is not matching after Re-login", value);
    }

}
