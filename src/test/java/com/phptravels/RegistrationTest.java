package com.phptravels;

import com.phptravels.models.User;
import com.phptravels.pages.HomePage;
import com.phptravels.pages.RegistrationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestSetup {

    @Test
    public void valid_register_should_take_to_login_page() throws InterruptedException {
        //ARRANGE
        RegistrationPage registrationPage = new RegistrationPage(driver, wait).get();

        //ACT
        User userDetails = User.generateValidUser();
        HomePage homePage = registrationPage.registerAs(userDetails);

        //ASSERT
        assertTrue(homePage.isCorrectlyDisplayed());
    }

}
