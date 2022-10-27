package com.phptravels;

import com.phptravels.api.ApiClient;
import com.phptravels.config.ConfigurationManager;
import com.phptravels.pages.LoginPage;
import com.phptravels.webdriver.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class TestSetup {
    protected WebDriver driver;
    protected static String email;
    protected static String password;

    protected ApiClient apiClient;

    @BeforeSuite
    public void globalSetup() {
        apiClient = new ApiClient(ConfigurationManager.getBrowserConfigInstance().apiBaseUrl());
    }

    @BeforeMethod
    public void setupWebDriver() {
        driver = getSupportedBrowser();
    }

    private static WebDriver getSupportedBrowser() {
        String propertyDefinedDriver = ConfigurationManager.getBrowserConfigInstance().defaultBrowser();
        try {
            return WebDriverFactory.valueOf(propertyDefinedDriver.toUpperCase()).createDriver();
        } catch (IllegalArgumentException ex) {
            log.error("Non-supported browser defined in browser properties file: \"{}\", defaulting to Chrome", propertyDefinedDriver);
        }
        return WebDriverFactory.CHROME.createDriver();
    }

    protected void navigateToAPage(){
        new LoginPage(driver).get();
    }

}
