package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import com.phptravels.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class RegistrationPage extends LoadableComponent<RegistrationPage> {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final PageActions pageActions;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/register.php";

    @FindBy(xpath = "//form[@id='frmCheckout']")
    private WebElement registrationForm;

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputEmail")
    private WebElement emailField;

    @FindBy(id = "inputPhone")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//div[@class='selected-dial-code']")
    private WebElement dialCodeField;

    @FindBy(id = "inputAddress1")
    private WebElement streetField;

    @FindBy(id = "inputCity")
    private WebElement cityField;

    @FindBy(id = "stateinput")
    private WebElement stateField;

    @FindBy(id = "inputPostcode")
    private WebElement postcodeField;

    @FindBy(id = "inputNewPassword1")
    private WebElement passwordField;

    @FindBy(id = "inputNewPassword2")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    private WebElement recaptchaIframe;

    @FindBy(xpath = "//*[@id=\"recaptcha-anchor\"]/div[1]")
    private WebElement recaptchaCheckbox;

    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerButton;

    @FindBy(id = "Primary_Sidebar-Already_Registered-Login")
    private WebElement loginLink;

    @FindBy(id = "Primary_Sidebar-Already_Registered-Lost_Password_Reset")
    private WebElement lostPasswordResetLink;

    @FindBy(xpath = "//li[@class='country preferred highlight']//span[@class='country-name'][normalize-space()='United Kingdom']")
    private WebElement dialCode;

    @FindBy(className = "country-list")
    private WebElement countryList;

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public HomePage registerAs(User userInfo) throws InterruptedException {
        fillFormWith(userInfo);
        Thread.sleep(10000);
        registerButton.click();
        return new HomePage(driver);
    }

    public LoginPage goToLogin() {
        loginLink.click();
        return new LoginPage(driver);
    }

    private void fillFormWith(com.phptravels.models.User userDetails) throws InterruptedException {
        clearAndType(firstNameField, userDetails.getFirstName());
        clearAndType(lastNameField, userDetails.getLastName());
        clearAndType(emailField, userDetails.getEmailAddress());
        //selectDialCode();
        clearAndType(phoneNumberField, userDetails.getPhoneNumber());
        clearAndType(streetField, userDetails.getStreet());
        clearAndType(cityField, userDetails.getCity());
        clearAndType(stateField, userDetails.getState());
        clearAndType(postcodeField, userDetails.getPostcode());
        selectCountryFromDropdown();
        pageActions.scrollElementIntoView(passwordField);
        clearAndType(passwordField, userDetails.getPassword());
        clearAndType(confirmPasswordField, userDetails.getConfirmPassword());
        clickNoToMailingList();
        pageActions.scrollElementIntoView(registerButton);
        Thread.sleep(4000);
        switchToFrameAndClickRecaptcha();
        switchToParentFrame();
        Thread.sleep(4000);
    }

    public void selectDialCode() throws InterruptedException {
        driver.findElement(By.cssSelector(".selected-dial-code")).click();
        Thread.sleep(3000);
        WebElement targetElement = driver.findElement(By.xpath("//li[contains(.,'Macedonia (FYROM) (Македонија)+389')]"));
        pageActions.scrollElementIntoView(targetElement);
    }

    public void selectCountryFromDropdown() {
        Select countryDropdown = new Select(driver.findElement(By.id("inputCountry")));
        countryDropdown.selectByVisibleText("Macedonia ");
    }

    public void clickNoToMailingList() {
        WebElement checkbox = driver.findElement(By.xpath("//div[@class='bootstrap-switch-container']"));
        checkbox.click();
    }

    public void switchToFrameAndClickRecaptcha() {
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recaptchaIframe));
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(recaptchaCheckbox)).click();
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(URL));
        assertTrue(registrationForm.isDisplayed());
    }
}
