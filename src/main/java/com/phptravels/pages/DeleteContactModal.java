package com.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteContactModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmButton;

    public DeleteContactModal(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public ContactsPage clickConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        return new ContactsPage(driver,wait);
    }
}
