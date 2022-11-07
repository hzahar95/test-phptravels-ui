package com.phptravels;

import com.phptravels.models.User;
import com.phptravels.pages.LoginPage;
import com.phptravels.pages.ProfilePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EditUserDetailsTest extends TestSetup {

    @BeforeTest
    public void loginBeforeTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver).get();
        loginPage.loginAs(email, password);
    }


    @Test
    public void user_should_be_able_to_edit_his_account_details() {
        // ARRANGE
        User newUserDetails = User.generateValidUser();

        // ACT
        ProfilePage profilePage = new ProfilePage(driver).get();
        profilePage.editUserDetails(newUserDetails);

        // ASSERT
        assertTrue(profilePage.isDisplayedCorrectly());
        assertTrue(profilePage.isSuccessMessageDisplayed());
    }

    @AfterTest
    public void revert_mail_to_original_one(){
        ProfilePage profilePage = new ProfilePage(driver).get();
        profilePage.revertBackToOriginalMail();
    }

}
