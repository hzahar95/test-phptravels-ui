package com.phptravels;

import com.phptravels.models.User;
import com.phptravels.pages.ContactsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddRemoveContactTest extends TestSetup {

    @Test
    public void logged_user_can_add_new_contact() throws InterruptedException {
        //ARRANGE
        loginBeforeTest();
        ContactsPage contactsPage = new ContactsPage(driver, wait).get();

        //ACT
        User userDetails = User.generateValidContact();
        contactsPage.addNewContactAs(userDetails);

        //ASSERT
        assertTrue(contactsPage.isSuccessMessageDisplayed());
    }

    @Test(dependsOnMethods = "logged_user_can_add_new_contact")
    public void user_can_remove_added_contact() {
        //ARRANGE
        ContactsPage contactsPage = new ContactsPage(driver, wait).get();

        //ACT
        contactsPage.openDeleteContactModal().clickConfirm();

        //ASSERT


    }

}
