package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.*;
import static com.upgrade.utilities.Constants.*;

public class DocumentsPage extends BasePage{

    @FindBy(css = "[data-auto='layoutTitle']")
    private WebElement title;

    @FindBy(xpath = XPATH_PREFIX + UPGRADE_PRIVACY_NOTICE_AGREEMENT + XPATH_SUFFIX)
    private WebElement upgradePrivateNoticeAgreementTxt;

    @FindBy(xpath = XPATH_PREFIX + CREDIT_PROFILE_AUTH_AGREEMENT + XPATH_SUFFIX)
    private WebElement creditProfileAuthAgreementTxt;

    @FindBy(xpath = XPATH_PREFIX + ESIGN_ACT_CONSENT_AGREEMENT + XPATH_SUFFIX)
    private WebElement eSignActConsentAgreementTxt;

    @FindBy(xpath = XPATH_PREFIX + TERMS_OF_USE_AGREEMENT + XPATH_SUFFIX)
    private WebElement termsOfUseAgreementTxt;

    @FindBy(xpath = XPATH_PREFIX + ADVERSE_ACTION_NOTICE + XPATH_SUFFIX)
    private WebElement adverseActionTxt;

    public DocumentsPage(WebDriver driver, String url) {
        super(driver);
        waitForWebElement(title);
        assertEquals(driver.getCurrentUrl(), url, "Url is not matching with link clicked");
    }

    public FunnelBasePage verifyDocuments(){
        waitForWebElement(upgradePrivateNoticeAgreementTxt);
        assertEqual(upgradePrivateNoticeAgreementTxt, UPGRADE_PRIVACY_NOTICE_AGREEMENT);
        assertEqual(creditProfileAuthAgreementTxt, CREDIT_PROFILE_AUTH_AGREEMENT);
        assertEqual(eSignActConsentAgreementTxt, ESIGN_ACT_CONSENT_AGREEMENT);
        assertEqual(termsOfUseAgreementTxt, TERMS_OF_USE_AGREEMENT);
        assertEqual(adverseActionTxt, ADVERSE_ACTION_NOTICE);
        return new FunnelBasePage(driver);
    }

    private void assertEqual(WebElement webElement, String expectedTxt){
        assertEquals(webElement.getText(), expectedTxt, expectedTxt.concat(MSG_SUFFIX));
    }

}
