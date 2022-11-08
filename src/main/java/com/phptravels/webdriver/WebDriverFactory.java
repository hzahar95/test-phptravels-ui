package com.phptravels.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.HashMap;

import static com.phptravels.webdriver.WebDriverPreferenceConstants.DOWNLOAD_PATH;

public enum WebDriverFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.default_directory", DOWNLOAD_PATH);
            chromePrefs.put("credentials_enable_service",false);
            chromePrefs.put("autofill.profile_enabled",false);
            return new ChromeOptions()
                    .addArguments(START_MAXIMIZED)
                    .addArguments(DISABLE_INFOBARS)
                    .addArguments(DISABLE_NOTIFICATIONS)
                    .setExperimentalOption("prefs", chromePrefs);
        }
    },
    FIREFOX {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            return new FirefoxOptions()
                    .addArguments(FULL_HD_MAX_WIDTH)
                    .addArguments(FULL_HD_MAX_HEIGHT);
        }
    };

    private static final String START_MAXIMIZED = "--start-maximized";
    private static final String DISABLE_NOTIFICATIONS = "--disable-notifications";
    private static final String DISABLE_INFOBARS = "--disable-infobars";

    private static final String INCOGNITO = "--incognito";
    private static final String FULL_HD_MAX_WIDTH = "--width=1920";
    private static final String FULL_HD_MAX_HEIGHT = "--height=1080";

    public abstract WebDriver createDriver();
    public abstract AbstractDriverOptions<?> getOptions();
}
