package com.upgrade.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.*;

public class DocumentsPage extends BasePage{

    private static final String xpathPrefix = "//td[text()='";
    private static final String UPGRADE_PRIVACY_NOTICE_AGREEMENT = "Upgrade Privacy Notice Agreement";
    private static final String CREDIT_PROFILE_AUTH_AGREEMENT = "Credit Profile Authorization Agreement";
    private static final String ESIGN_ACT_CONSENT_AGREEMENT = "ESIGN Act Consent Agreement";
    private static final String TERMS_OF_USE_AGREEMENT = "Terms Of Use Agreement";
    private static final String ADVERSE_ACTION_NOTICE = "Adverse Action Notice";
    private static final String msg_suffix = "txt not found";

    @FindBy(css = "[data-auto='layoutTitle']")
    private WebElement title;

    @FindBy(xpath = xpathPrefix + UPGRADE_PRIVACY_NOTICE_AGREEMENT + "']")
    private WebElement upgradePrivateNoticeAgreementTxt;

    @FindBy(xpath = xpathPrefix + CREDIT_PROFILE_AUTH_AGREEMENT + "']")
    private WebElement creditProfileAuthAgreementTxt;

    @FindBy(xpath = xpathPrefix + ESIGN_ACT_CONSENT_AGREEMENT + "']")
    private WebElement eSignActConsentAgreementTxt;

    @FindBy(xpath = xpathPrefix + TERMS_OF_USE_AGREEMENT + "']")
    private WebElement termsOfUseAgreementTxt;

    @FindBy(xpath = xpathPrefix + ADVERSE_ACTION_NOTICE + "']")
    private WebElement adverseActionTxt;

    public DocumentsPage(WebDriver driver, String url) {
        super(driver);
        waitForWebElement(title);
        assertEquals(driver.getCurrentUrl(), url, "Url is not matching with link clicked");
    }

    public FunnelBasePage verifyDocuments(){
        waitForWebElement(upgradePrivateNoticeAgreementTxt);
        assertEquals(upgradePrivateNoticeAgreementTxt.getText(), UPGRADE_PRIVACY_NOTICE_AGREEMENT, UPGRADE_PRIVACY_NOTICE_AGREEMENT.concat(msg_suffix));
        assertEquals(creditProfileAuthAgreementTxt.getText(), CREDIT_PROFILE_AUTH_AGREEMENT, CREDIT_PROFILE_AUTH_AGREEMENT.concat(msg_suffix));
        assertEquals(eSignActConsentAgreementTxt.getText(), ESIGN_ACT_CONSENT_AGREEMENT, ESIGN_ACT_CONSENT_AGREEMENT.concat(msg_suffix));
        assertEquals(termsOfUseAgreementTxt.getText(), TERMS_OF_USE_AGREEMENT, TERMS_OF_USE_AGREEMENT.concat(msg_suffix));
        assertEquals(adverseActionTxt.getText(), ADVERSE_ACTION_NOTICE, ADVERSE_ACTION_NOTICE.concat(msg_suffix));

        return new FunnelBasePage(driver);
    }

}
