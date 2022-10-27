package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class LoginPage extends LoadableComponent<LoginPage> {
    private final WebDriver driver;
    private final PageActions pageActions;

    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl();

    @FindBy(id = "inputEmail")
    private WebElement emailField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    private WebElement recaptchaIframe;

    @FindBy(xpath = "//*[@id=\"recaptcha-anchor\"]/div[1]")
    private WebElement recaptchaCheckbox;

    @FindBy(xpath = "//button[@id='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Create account']")
    private WebElement createAccountLink;

    public LoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        pageActions = new PageActions(driver);
    }

    public HomePage loginAs(String email,String password){
        setEmail(email);
        setPassword(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public boolean isFormDisplayed(){
        return emailField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
    }

    private void setEmail(String email){
        pageActions.clearAndType(emailField,email);
    }

    private void setPassword(String password){
        pageActions.clearAndType(passwordField,password);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains("/login"));
        assertTrue(isFormDisplayed());
    }
}
