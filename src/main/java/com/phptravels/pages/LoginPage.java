package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class LoginPage extends LoadableComponent<LoginPage> {
    private final WebDriver driver;
    private WebDriverWait wait;

    private final PageActions pageActions;

    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/login";

    @FindBy(id = "inputEmail")
    private WebElement emailField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(xpath = "//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
    private WebElement recaptchaIframe;

    @FindBy(id = "recaptcha-anchor")
    private WebElement recaptchaCheckbox;

    @FindBy(xpath = "//button[@id='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Create account']")
    private WebElement createAccountLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public boolean isLoginFormDisplayed() {
        return emailField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
    }

    private void setEmail(String email) {
        pageActions.clearAndType(emailField, email);
    }

    private void setPassword(String password) {
        pageActions.clearAndType(passwordField, password);
    }

    public void switchToFrameAndClickRecaptcha() {
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recaptchaIframe));
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(recaptchaCheckbox)).click();
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void mouseHoover() {
        Actions actions = new Actions(driver);
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recaptcha-anchor"))));
        actions.perform();
    }

    public HomePage loginAs(String email, String password) throws InterruptedException {
        setEmail(email);
        setPassword(password);
        Thread.sleep(4000);
        switchToFrameAndClickRecaptcha();
        //mouseHoover();
        switchToParentFrame();
        Thread.sleep(4000);
        loginButton.click();
        return new HomePage(driver);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains("/login"));
        assertTrue(isLoginFormDisplayed());
    }
}
