package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import com.phptravels.utils.FakerUtils;
import com.phptravels.utils.RandomWebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class InviteNewUserPage extends LoadableComponent<InviteNewUserPage> {
    private final WebDriver driver;
    private final PageActions pageActions;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/account/users";

    @FindBy(xpath = "//input[@placeholder='name@example.com']")
    private WebElement inviteEmailField;

    @FindBy(xpath = "//button[normalize-space()='Send Invite']")
    private WebElement sendInviteButton;

    @FindBy(css = "input[value='choose']")
    private WebElement choosePermissionsRadioButton;

    @FindBy(xpath = "(//div[@id='invitePermissions'])[1]//label//input[@type='checkbox']")
    private List<WebElement> permissionCheckboxes;

    public InviteNewUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public void sendInvite() throws InterruptedException {
        pageActions.clearAndType(inviteEmailField, FakerUtils.generateValidEmail());
        choosePermissionsRadioButton.click();
        pageActions.scrollElementIntoView(sendInviteButton);
        RandomWebElementUtils.selectCertainNumberOfRandomWebElements(3, permissionCheckboxes);
        pageActions.scrollElementIntoView(sendInviteButton);
        sendInviteButton.click();
    }

    private void selectAllPermissionCheckboxes() {
        pageActions.scrollElementIntoView(sendInviteButton);
        System.out.println("Number of Check boxes : " + (permissionCheckboxes.size()));

        for (WebElement cb : permissionCheckboxes) {
            cb.click();
        }
        System.out.println("All check boxes have been checked");
    }


    public boolean isDisplayedCorrectly() {
        return inviteEmailField.isDisplayed() && sendInviteButton.isDisplayed();
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
