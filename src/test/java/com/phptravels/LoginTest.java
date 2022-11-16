package com.phptravels;

import com.phptravels.pages.HomePage;
import com.phptravels.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends TestSetup{
    @Test
    public void valid_login_should_take_to_home_page() throws InterruptedException {
        //ACT
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email,password);
        //ASSERT
        assertTrue(homePage.isCorrectlyDisplayed());
    }

    //TODO
    @Test
    public void invalid_login_should_pop_warning_message(){

    }
}
