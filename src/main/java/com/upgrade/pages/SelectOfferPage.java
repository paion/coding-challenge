package com.upgrade.pages;

import com.upgrade.pojos.loan.Offer;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.*;

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
        assertNotNull(userLoanAmount.getText(), msg("approved loan amount", userLoanAmount));
        assertTrue(monthlyPayment.getText().contains("$"), msg("monthly payment", monthlyPayment));
        assertTrue(loanTerm.getText().contains(" Month"), msg("month term", loanTerm));
        assertTrue(loanInterestRate.getText().contains("%"), msg("interest rate", loanInterestRate));
        assertTrue(apr.getText().contains("%"), msg("APR", apr));

        offer.setLoanAmount(userLoanAmount.getText());
        offer.setMonthlyPayment(monthlyPayment.getText());
        offer.setLoanTerm(loanTerm.getText());
        offer.setInterestRate(loanInterestRate.getText());
        offer.setApr(apr.getText());

        return new SelectOfferPage(driver);
    }

    public SelectOfferPage verifyOfferAfterReLogin(Offer offerAfterAccountCreation, Offer offerAfterReLogin){
        assertEquals(offerAfterAccountCreation.getLoanAmount(), offerAfterReLogin.getLoanAmount(),  msg("Loan amount"));
        assertEquals(offerAfterAccountCreation.getMonthlyPayment(), offerAfterReLogin.getMonthlyPayment(),  msg("Monthly payment"));
        assertEquals(offerAfterAccountCreation.getLoanTerm(), offerAfterReLogin.getLoanTerm(),  msg("Loan term"));
        assertEquals(offerAfterAccountCreation.getInterestRate(), offerAfterReLogin.getInterestRate(),  msg("Interest rate"));
        assertEquals(offerAfterAccountCreation.getApr(), offerAfterReLogin.getApr(),  msg("APR"));
        return new SelectOfferPage(driver);
    }

    private String msg(String value, WebElement actualText){
        return String.format("There is no default %s found. Actual Text: '%s' | ", value, actualText.getText());
    }

    private String msg(String value){
        return String.format("%s is not matching after Re-login", value);
    }

}
