package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import com.phptravels.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class ProfilePage extends LoadableComponent<ProfilePage> {
    private final WebDriver driver;
    private final PageActions pageActions;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/user/profile";
    private static final String SAVE_CHANGES_BUTTON = "(//input[@name='save'])";

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(xpath = SAVE_CHANGES_BUTTON + "[1]")
    private WebElement saveNameButton;

    @FindBy(id = "inputEmail")
    private WebElement emailField;

    @FindBy(xpath = SAVE_CHANGES_BUTTON + "[2]")
    private WebElement saveEmailButton;

    @FindBy(xpath = "//h3[@class='card-title m-0']")
    private WebElement yourProfileMenu;


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public ProfilePage editUserDetails(User editedUserDetails) {
        pageActions.clearAndType(firstNameField, editedUserDetails.getFirstName());
        pageActions.clearAndType(lastNameField, editedUserDetails.getLastName());
        saveNameButton.click();
        pageActions.scrollElementIntoView(saveEmailButton);
        pageActions.clearAndType(emailField,"apiautomationworker@yahoo.com");
        saveEmailButton.click();
        return this;
    }

    public boolean isSuccessMessageDisplayed() {
        By alertMessageBy = By.cssSelector(".alert-success");
        return pageActions.isSuccessMessageDisplayed(alertMessageBy);
    }

    public boolean isDisplayedCorrectly() {
        return saveNameButton.isDisplayed() && saveEmailButton.isDisplayed() && yourProfileMenu.isDisplayed();
    }

    @Override
    protected void load() {
        driver.get(URL);
        pageActions.waitForElement(driver, By.xpath(SAVE_CHANGES_BUTTON), 2);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(URL));
        assertTrue(isDisplayedCorrectly());
    }

}
