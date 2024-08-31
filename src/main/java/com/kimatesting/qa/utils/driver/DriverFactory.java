package com.kimatesting.qa.utils.driver;

import com.kimatesting.qa.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public WebDriver createDriver(Browser browser, Boolean headless, int width, int height) throws Exception {
        switch (browser){
            case CHROME:
                ChromeOptions cOptions = new ChromeOptions();
                if (headless) cOptions.addArguments("--headless=new");
                cOptions.addArguments(String.format("--window-size=%d,%d", width, height));
                return WebDriverManager.chromedriver().capabilities(cOptions).create();
            case FIREFOX:
                FirefoxOptions fOptions = new FirefoxOptions();
                if (headless) fOptions.addArguments("--headless");
                fOptions.addArguments(String.format("--width=%d", width));
                fOptions.addArguments(String.format("--height=%d", height));
                return WebDriverManager.firefoxdriver().capabilities(fOptions).create();
            case SAFARI:
                WebDriver safariDriver = WebDriverManager.safaridriver().create();
                safariDriver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
                return safariDriver;
            default:
                throw new Exception(String.format("The browser %s was not found",browser.get()));
        }
    }
}
