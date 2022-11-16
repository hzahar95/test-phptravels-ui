package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import com.phptravels.models.User;
import com.phptravels.utils.RandomWebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class ContactsPage extends LoadableComponent<ContactsPage> {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final PageActions pageActions;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/account/contacts";

    @FindBy(xpath = "//button[normalize-space()='Go']")
    private WebElement goButton;

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputCompanyName")
    private WebElement companyNameField;

    @FindBy(id = "inputEmail")
    private WebElement emailAddressField;

    @FindBy(id = "inputPhone")
    private WebElement phoneNumberField;

    @FindBy(id = "inputAddress1")
    private WebElement firstAddressField;

    @FindBy(id = "inputAddress2")
    private WebElement secondAddressField;

    @FindBy(id = "inputCity")
    private WebElement cityField;

    @FindBy(id = "stateinput")
    private WebElement stateField;

    @FindBy(id = "inputPostcode")
    private WebElement zipCodeField;

    @FindBy(xpath = "//input[@value='Save Changes']")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//button[normalize-space()='Delete Contact']")
    private WebElement deleteContactButton;

    @FindBy(id = "btnCancelInviteConfirm")
    private WebElement confirmButton;

    @FindBy(name = "contactid")
    private WebElement contactsDropdown;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement countryDropdown;

    @FindBy(xpath = "//select[@id='country']//option")
    private List<WebElement> countryOptions;

    @FindBy(xpath = "(//div[@class='controls form-check'])[1]//label//input[@type='checkbox']")
    private List<WebElement> emailPreferencesCheckboxes;

    public ContactsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public ContactsPage addNewContact(User userDetails) {
        fillContactFormWith(userDetails);
        selectRandomCountryFromDropdown();
        pageActions.scrollElementIntoView(saveChangesButton);
        RandomWebElementUtils.selectCertainNumberOfRandomWebElements(2, emailPreferencesCheckboxes);
        saveChangesButton.click();
        return new ContactsPage(driver, wait);
    }

    private void selectRandomCountryFromDropdown() {
        clickCountryDropdown();
        int index = new Random().nextInt(countryOptions.size());
        WebElement randomCountry = countryOptions.get(index);
        pageActions.scrollElementIntoView(randomCountry);
        System.out.println(randomCountry.getAttribute("value"));
        randomCountry.click();
    }

    private void clickCountryDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)).click();
    }

    public ContactsPage openDeleteContactModal(User userDetails) {
        selectAddedContactFromDropdown(userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmailAddress());
        goButton.click();
        pageActions.scrollElementIntoView(deleteContactButton);
        deleteContactButton.click();
        return this;
    }

    public ContactsPage clickConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        return this;
    }

    private void fillContactFormWith(User userDetails) {
        clearAndType(firstNameField, userDetails.getFirstName());
        clearAndType(lastNameField, userDetails.getLastName());
        clearAndType(companyNameField, userDetails.getCompanyName());
        clearAndType(emailAddressField, userDetails.getEmailAddress());
        clearAndType(phoneNumberField, userDetails.getPhoneNumber());
        clearAndType(firstAddressField, userDetails.getStreet());
        clearAndType(secondAddressField, userDetails.getStreet());
        clearAndType(cityField, userDetails.getCity());
        clearAndType(stateField, userDetails.getState());
        clearAndType(zipCodeField, userDetails.getPostcode());
    }

    public void selectAddedContactFromDropdown(String firstName, String lastName, String emailAddress) {
        Select dropdownContacts = new Select(contactsDropdown);
        dropdownContacts.selectByVisibleText(firstName + " " + lastName + " - " + emailAddress);
    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void checkIfContactIsPresentAfterDeleting() {
        Select chooseContactDropdown = new Select(contactsDropdown);
        List<WebElement> allOptions = chooseContactDropdown.getOptions();
        boolean found = false;

        for (WebElement webElement : allOptions) {
            String defaultMember = "Add New Contact";
            if (defaultMember.equalsIgnoreCase(webElement.getAttribute("value"))) {
                chooseContactDropdown.selectByValue(defaultMember);
                found = true;
                break;
            }
        }
    }

    public boolean isDisplayedCorrectly() {
        return goButton.isDisplayed() && firstNameField.isDisplayed() && lastNameField.isDisplayed();
    }

    public boolean isSuccessMessageDisplayed() {
        By alertMessageBy = By.cssSelector(".alert-success");
        return pageActions.isSuccessMessageDisplayed(alertMessageBy);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(URL));
        assertTrue(isDisplayedCorrectly());
    }
}
