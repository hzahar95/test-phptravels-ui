package com.phptravels;

import com.phptravels.models.User;
import com.phptravels.pages.ProfilePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EditUserDetailsTest extends TestSetup {

    @Test
    public void user_should_be_able_to_edit_his_account_details() throws InterruptedException {
        // ARRANGE
        loginBeforeTest();
        User newUserDetails = User.generateValidUser();

        // ACT
        ProfilePage profilePage = new ProfilePage(driver).get();
        profilePage.editUserDetails(newUserDetails);

        // ASSERT
        assertTrue(profilePage.isDisplayedCorrectly());
        assertTrue(profilePage.isSuccessMessageDisplayed());
    }

    @AfterTest
    public void revertMailToOriginalOne() {
        ProfilePage profilePage = new ProfilePage(driver).get();
        profilePage.revertBackToOriginalMail(email);
        assertTrue(profilePage.isSuccessMessageDisplayed());
    }

}
