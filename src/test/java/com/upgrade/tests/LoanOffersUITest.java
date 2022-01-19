package com.upgrade.tests;

import com.upgrade.pages.*;
import com.upgrade.pojos.loan.Borrower;
import com.upgrade.pojos.loan.Offer;
import com.upgrade.utilities.CreateTestData;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

@Log4j
public class LoanOffersUITest extends AbstractTest {
    private static final String url = "https://www.credify.tech";

    /*
        Please refer README.md for more details on
        Case # 1 : Validate offers after re-login
    */

    @Test
    public void validateOffersTest() {
        Borrower borrower = CreateTestData.getRandomTestBorrower("Home Improvement");
        LandingPage landingPage = new LandingPage(getDriver());
        Offer offerAfterAccountCreation = new Offer();

        //Capture offer details in the Offers page
        landingPage
                .gotoLandingPage(url)
                .enterLoanDetails(borrower)
                .enterContactDetails(borrower)
                .enterIncomeDetails(borrower)
                .enterLoginDetails(borrower, new SelectOfferPage(getDriver()))
                .verifyDefaultFirstOffer(offerAfterAccountCreation)
                .clickSignOut(new SignOutPage(getDriver()))
                .verifySignOutPage();

        //Validate the offer details after login
        SignInPage signInPage = new SignInPage(getDriver());
        Offer offerAfterReLogin = new Offer();
        signInPage
                .gotoSignInPage(url)
                .signIn(borrower)
                .verifyDefaultFirstOffer(offerAfterReLogin)
                .verifyOfferAfterReLogin(offerAfterAccountCreation, offerAfterReLogin)
                .clickSignOut(new SignOutPage(getDriver()));
    }

    /*
        Please refer README.md for more details on
        Case # 2  : Loan rejected for low annual income
    */

    @Test
    public void validateDeclineLoanTest() {
        Borrower borrower = CreateTestData.getRandomTestBorrower("Debt Consolidation");
        borrower.setYearlyIncome(CreateTestData.generateRandomNumberFromRange(100, 1000));
        borrower.setAdditionalIncome(CreateTestData.generateRandomNumberFromRange(100, 500));

        LandingPage landingPage = new LandingPage(getDriver());
        Offer offerAfterAccountCreation = new Offer();

        //Capture offer details in the Offers page
        landingPage
                .gotoLandingPage(url)
                .enterLoanDetails(borrower)
                .enterContactDetails(borrower)
                .enterIncomeDetails(borrower)
                .enterLoginDetails(borrower, new RejectedOfferPage(getDriver()))
                .verifyRejectedOffer()
                .verifyDocuments()
                .clickSignOut(new SignInPage(getDriver()))
                .verifySignInHeader();
    }

}
