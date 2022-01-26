package com.upgrade.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.*;
import static com.upgrade.utilities.Constants.*;

public class DocumentsPage extends BasePage{
    private static final String DOWNLOAD_DOCUMENT_CSS = "[data-auto='downloadDocument']";

    @FindBy(css = "[data-auto='layoutTitle']")
    private WebElement title;

    @FindBy(css = DOWNLOAD_DOCUMENT_CSS)
    private WebElement downloadDocument;

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
        assertTrue(checkDownLoadDocumentLinkIsDisplayed(upgradePrivateNoticeAgreementTxt));
        assertEqual(creditProfileAuthAgreementTxt, CREDIT_PROFILE_AUTH_AGREEMENT);
        assertTrue(checkDownLoadDocumentLinkIsDisplayed(creditProfileAuthAgreementTxt));
        assertEqual(eSignActConsentAgreementTxt, ESIGN_ACT_CONSENT_AGREEMENT);
        assertTrue(checkDownLoadDocumentLinkIsDisplayed(eSignActConsentAgreementTxt));
        assertEqual(termsOfUseAgreementTxt, TERMS_OF_USE_AGREEMENT);
        assertTrue(checkDownLoadDocumentLinkIsDisplayed(termsOfUseAgreementTxt));
        assertEqual(adverseActionTxt, ADVERSE_ACTION_NOTICE);
        assertTrue(checkDownLoadDocumentLinkIsDisplayed(adverseActionTxt));
        return new FunnelBasePage(driver);
    }

    private void assertEqual(WebElement webElement, String expectedTxt){
        assertEquals(webElement.getText(), expectedTxt, expectedTxt.concat(MSG_SUFFIX));
    }

    private WebElement getParentTagUsingXpath(WebElement webElement){
        return webElement.findElement(By.xpath("./.."));
    }

    private boolean checkDownLoadDocumentLinkIsDisplayed(WebElement webElement){
        return getParentTagUsingXpath(webElement).findElement(By.cssSelector(DOWNLOAD_DOCUMENT_CSS)).isDisplayed();
    }

}
