package com.upgrade.pages;

import com.upgrade.pojos.loan.Borrower;
import com.upgrade.utilities.CommonUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

public class ContactInfoPage extends BasePage {

    @FindBy(name = "borrowerFirstName")
    private WebElement firstName;

    @FindBy(name = "borrowerLastName")
    private WebElement lastName;

    @FindBy(id = "geosuggest__input--borrowerStreet")
    private WebElement street;

    @FindBy(name = "borrowerCity")
    private WebElement city;

    @FindBy(name = "borrowerState")
    private WebElement state;

    @FindBy(name = "borrowerZipCode")
    private WebElement zipCode;

    @FindBy(name = "borrowerDateOfBirth")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//h3[contains(.,'Need help?')]")
    private WebElement needHelp;

    @FindBy(css = "[data-auto='continuePersonalInfo']")
    private WebElement continueContactInfo;

    public ContactInfoPage(WebDriver driver) {
        super(driver);
        waitForWebElements(Arrays.asList(firstName, lastName));
    }

    public IncomeInfoPage enterContactDetails(Borrower borrower) {
        type(firstName, borrower.getFirstName());
        type(lastName, borrower.getLastName());
        waitForWebElement(street);
        type(street, borrower.getStreet());
        waitForElementToBeDisplayed(firstName, TIMEOUT, 1);
        click(firstName);
        type(city, borrower.getCity());
        type(state, borrower.getState());
        type(zipCode, borrower.getZipCode());
        type(dateOfBirth, CommonUtilities.convertDOBFormat(borrower.getDob()));
        click(continueContactInfo);
        return new IncomeInfoPage(driver);
    }

}
