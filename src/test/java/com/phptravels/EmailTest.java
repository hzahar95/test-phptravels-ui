package com.phptravels;

import com.phptravels.utils.EmailUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.mail.Message;

public class EmailTest extends TestSetup{
    private static EmailUtils emailUtils;

    @BeforeClass
    public static void connectToEmail() {
        try {
            emailUtils = new EmailUtils();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

//    @Test
//    public void testTextContained() {
//        try{
//            Message email = emailUtils.getMessagesBySubject("Email Address Verification", true, 5)[1];
//            Assert.assertTrue(emailUtils.isTextInMessage(email, "Welcome to PHPTRAVELS"),"Verification message is not in email");
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.fail(e.getMessage());
//        }
//    }

    @Test
    public void testLink() {

        try{
            Message email = emailUtils.getMessagesBySubject("Email Address Verification", true, 5)[0];
            String link = emailUtils.getUrlsFromMessage(email, "Verify your email address").get(0);

            driver.get(link);

            //TODO: continue testing
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
