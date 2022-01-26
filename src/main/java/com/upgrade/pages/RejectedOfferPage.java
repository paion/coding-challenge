package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.upgrade.utilities.Constants.*;
import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.*;
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
        assertThat(rejectedOfferMsg.getText()).isIn(REJECTED_OFFER_TXT, REJECTED_OFFER_TXT2);
        assertEquals(adverseLearnMoreTxt.getText(), ADVERSE_LEARN_MORE_TXT,  "Adverse learn more text not found");

        String url = adverseLearnMoreLink.getAttribute("href");
        adverseLearnMoreLink.click();

        return new DocumentsPage(driver, url);
    }

}
