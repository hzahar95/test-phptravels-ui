package com.phptravels;

import com.phptravels.models.User;
import com.phptravels.pages.HomePage;
import com.phptravels.pages.RegistrationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestSetup {

    @Test
    public void valid_register_should_take_to_login_page() throws InterruptedException {
        //ACT
        RegistrationPage registrationPage = new RegistrationPage(driver, wait).get();
        HomePage homePage = registrationPage.registerAs(User.generateValidUser());
        //ASSERT
        assertTrue(homePage.isCorrectlyDisplayed());
    }

}
