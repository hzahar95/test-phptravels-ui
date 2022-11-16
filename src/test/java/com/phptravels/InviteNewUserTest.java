package com.phptravels;

import com.phptravels.pages.InviteNewUserPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class InviteNewUserTest extends TestSetup {

    @Test
    public void logged_user_can_invite_another_user_along_with_giving_permissions() throws InterruptedException {
        //ARRANGE
        loginBeforeTest();
        InviteNewUserPage inviteNewUserPage = new InviteNewUserPage(driver).get();

        //ACT
        inviteNewUserPage.sendInvite();

        //ASSERT
        assertTrue(inviteNewUserPage.isSuccessMessageDisplayed());
    }

    //TODO
    @Test
    public void user_can_cancel_the_invitation() {

    }

}
