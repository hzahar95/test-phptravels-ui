package com.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage {

    private final WebDriver driver;

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

    @FindBy(id = "inputCountry")
    private WebElement countryField;

    @FindBy(id = "inputNewPassword1")
    private WebElement passwordField;

    @FindBy(id = "inputNewPassword2")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//span[@class='bootstrap-switch-handle-off bootstrap-switch-secondary']")
    private WebElement subscribeBoxField;

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

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private void fillFormWith(com.phptravels.models.User userDetails){
        clearAndType(firstNameField,userDetails.getFirstName());
        clearAndType(lastNameField,userDetails.getLastName());
        clearAndType(emailField,userDetails.getEmailAddress());
        clearAndType(phoneNumberField,userDetails.getPhoneNumber());
        clearAndType(streetField,userDetails.getStreet());
        clearAndType(cityField,userDetails.getCity());
        clearAndType(stateField,userDetails.getState());
        clearAndType(postcodeField,userDetails.getPostcode());
        clearAndType(countryField,userDetails.getCountry());
        clearAndType(passwordField,userDetails.getPassword());
        clearAndType(confirmPasswordField,userDetails.getConfirmPassword());
    }

    private void clearAndType(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
}
