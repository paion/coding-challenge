package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RejectedOfferPage extends FunnelBasePage{

    //TODO: Need to confirm if this is the correct message or not, bcs Readme.md file has different msg for rejection
    private static final String REJECTED_OFFER_TXT = "We can't find you a loan offer yet, but you still have great options";
    private static final String ADVERSE_LEARN_MORE_TXT = "If you would like to learn more about why you were not approved, please click here.";

    @FindBy(css = ".col-md-6")
    private WebElement rejectedOfferMsg;

    @FindBy(xpath = "//a[@data-auto='adverse-learn-more-link']/..")
    private WebElement adverseLearnMoreTxt;

    @FindBy(css = "[data-auto='adverse-learn-more-link']")
    private WebElement adverseLearnMoreLink;

    public RejectedOfferPage(WebDriver driver) {
        super(driver);
    }

    public DocumentsPage verifyRejectedOffer(){
        Assert.assertEquals(rejectedOfferMsg.getText(), REJECTED_OFFER_TXT,  "Rejected offer msg not found");
        Assert.assertEquals(adverseLearnMoreTxt.getText(), ADVERSE_LEARN_MORE_TXT,  "Adverse learn more text not found");

        String url = adverseLearnMoreLink.getAttribute("href");
        adverseLearnMoreLink.click();

        return new DocumentsPage(driver, url);
    }

}
