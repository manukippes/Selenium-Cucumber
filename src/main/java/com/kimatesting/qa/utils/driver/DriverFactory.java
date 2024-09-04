package com.kimatesting.qa.utils.driver;

import com.kimatesting.qa.enums.Browser;
import com.kimatesting.qa.enums.Platform;
import com.kimatesting.qa.utils.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;


public class DriverFactory {

    private String selenoidUrl = Optional.ofNullable(System.getProperty("selenoidUrl")).orElse(ConfigLoader.getProperty("remote.selenoid.url"));
    private String seleniumGridUrl = Optional.ofNullable(System.getProperty("seleniumGridUrl")).orElse(ConfigLoader.getProperty("remote.selenium.grid.url"));

    public WebDriver createDriver(Boolean remoteExecution, Platform remotePlatform, Browser browser, Boolean headless, int width, int height) throws Exception {
        if (remoteExecution) {
            String remoteUrl = getRemoteUrl(remotePlatform);
            return new RemoteWebDriver(URI.create(remoteUrl).toURL(), getRemoteBrowserOptions(browser, remotePlatform, headless, width, height));
        } else {
            return createLocalDriver(browser, headless, width, height);
        }
    }

    private WebDriver createLocalDriver(Browser browser, Boolean headless, int width, int height) throws Exception {
        switch (browser) {
            case CHROME:
                return WebDriverManager.chromedriver().capabilities(createChromeBaseOptions(headless, width, height)).create();
            case FIREFOX:
                return WebDriverManager.firefoxdriver().capabilities(createFirefoxBaseOptions(headless, width, height)).create();
            case SAFARI:
                WebDriver driver = WebDriverManager.safaridriver().create();
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
                return driver;
            default:
                throw new Exception(String.format("The browser %s was not found", browser.get()));
        }
    }

    private String getRemoteUrl(Platform remotePlatform) throws Exception {
        switch (remotePlatform) {
            case seleniumGrid:
                return seleniumGridUrl;
            case selenoid:
                return selenoidUrl;
            default:
                throw new Exception(String.format("The remote platform %s was not found", remotePlatform));
        }
    }

    private MutableCapabilities getRemoteBrowserOptions(Browser browser, Platform remotePlatform, Boolean headless, int width, int height) {
        MutableCapabilities options;
        switch (browser) {
            case CHROME:
                options = createChromeBaseOptions(headless, width, height);
                break;
            case FIREFOX:
                options = createFirefoxBaseOptions(headless, width, height);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        if (remotePlatform.equals(Platform.selenoid)) {
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("enableVideo", true);
                put("enableVNC", true);
            }});
        }
        return options;
    }

    private ChromeOptions createChromeBaseOptions(Boolean headless, int width, int height) {
        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments(String.format("--window-size=%d,%d", width, height));
        return options;
    }

    private FirefoxOptions createFirefoxBaseOptions(Boolean headless, int width, int height) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("--headless");
        options.addArguments(String.format("--width=%d", width));
        options.addArguments(String.format("--height=%d", height));
        return options;
    }
}
