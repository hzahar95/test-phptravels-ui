package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class ChangePasswordPage extends LoadableComponent<ChangePasswordPage> {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final PageActions pageActions;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/user/password";

    @FindBy(id = "inputExistingPassword")
    private WebElement existingPasswordField;

    @FindBy(id = "inputNewPassword1")
    private WebElement newPasswordField;

    @FindBy(id = "inputNewPassword2")
    private WebElement confirmNewPasswordField;

    @FindBy(xpath = "//button[normalize-space()='Generate Password']")
    private WebElement generatePasswordButton;

    @FindBy(xpath = "//input[@value='Save Changes']")
    private WebElement saveChangesButton;

    public ChangePasswordPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public boolean isDisplayedCorrectly() {
        return existingPasswordField.isDisplayed() && generatePasswordButton.isDisplayed() && saveChangesButton.isDisplayed();
    }

    public void setExistingPassword(String password) {
        pageActions.clearAndType(existingPasswordField, password);
    }

    public GeneratePasswordModal openPasswordGenerationModal() {
        wait.until(ExpectedConditions.elementToBeClickable(generatePasswordButton)).click();
        return new GeneratePasswordModal(driver, wait);
    }

    public String changePassword(String password) {
        setExistingPassword(password);
        GeneratePasswordModal generatePasswordModal = openPasswordGenerationModal();
        String autoGeneratePassword = generatePasswordModal.autoGeneratePassword();
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton)).click();
        return autoGeneratePassword;
    }

    public void manualPasswordChange(String oldPassword, String newPassword) {
        existingPasswordField.clear();
        existingPasswordField.sendKeys(oldPassword);
        newPasswordField.sendKeys(newPassword);
        confirmNewPasswordField.sendKeys(newPassword);
        saveChangesButton.click();
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
