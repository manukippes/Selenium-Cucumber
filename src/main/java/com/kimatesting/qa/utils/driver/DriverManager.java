package com.kimatesting.qa.utils.driver;


import com.kimatesting.qa.enums.Browser;
import com.kimatesting.qa.enums.Env;
import com.kimatesting.qa.enums.Platform;
import com.kimatesting.qa.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.time.Duration;
import java.util.Optional;


public class DriverManager {
    private static DriverFactory driverFactory = new DriverFactory();
    private static WebDriver driver;

    public static void initDriver() throws Exception {
        Browser browser = Browser.valueOf(Optional.ofNullable(System.getProperty("browser")).orElse(ConfigLoader.getProperty("browser")));
        Boolean browserHeadless = Boolean.valueOf(Optional.ofNullable(System.getProperty("browserHeadless")).orElse(ConfigLoader.getProperty("browserHeadless")));
        Platform remotePlatform= Platform.valueOf(Optional.ofNullable(System.getProperty("remotePlatform")).orElse(ConfigLoader.getProperty("remote.platform")));
        int browserWidth = Integer.parseInt(ConfigLoader.getProperty("browserWidth"));
        int browserHeight = Integer.parseInt(ConfigLoader.getProperty("browserHeight"));
        Boolean remoteExecution= Boolean.valueOf(ConfigLoader.getProperty("remote.execution"));
        driver =  driverFactory.createDriver(remoteExecution,remotePlatform,browser,browserHeadless, browserWidth, browserHeight);
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void closeDriver(){
        if(driver != null){
            driver.quit();
        }
    }

    public WebDriverWait waitFor() throws Exception {
        return new WebDriverWait(getDriver(),  Duration.ofSeconds(Long.parseLong(ConfigLoader.getProperty("timeout"))));
    }
}
