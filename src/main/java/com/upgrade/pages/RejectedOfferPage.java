package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.upgrade.utilities.Constants.*;

public class RejectedOfferPage extends FunnelBasePage{

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
