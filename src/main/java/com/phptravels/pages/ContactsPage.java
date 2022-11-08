package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import com.phptravels.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
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

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(xpath = "//input[@value='Save Changes']")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//button[normalize-space()='Delete Contact']")
    private WebElement deleteContactButton;

    public ContactsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public ContactsPage addNewContactAs(User userDetails) {
        fillContactFormWith(userDetails);
        selectRandomCountryFromDropdown();
        //select checkboxes
        pageActions.scrollElementIntoView(saveChangesButton);
        saveChangesButton.click();
        return new ContactsPage(driver,wait);
    }

    public DeleteContactModal openDeleteContactModal(){
        selectAddedContactFromDropdown();
        pageActions.scrollElementIntoView(deleteContactButton);
        deleteContactButton.click();
        return new DeleteContactModal(driver,wait);
    }

    public void deleteAddedContact() {
        DeleteContactModal deleteContactModal = openDeleteContactModal();
        deleteContactModal.clickConfirm();
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

    private void selectRandomCountryFromDropdown() {
        List<WebElement> options = driver.findElements(By.xpath("//select[@id='country']"));
        Random rand = new Random();
        int list = rand.nextInt(options.size());
        options.get(list).click();
    }

    private void selectAddedContactFromDropdown(){
        List<WebElement> options = driver.findElements(By.xpath("//select[@id='inputContactId']"));
        Random rand = new Random();
        int list = rand.nextInt(options.size());
        options.get(list).click();
    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
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
