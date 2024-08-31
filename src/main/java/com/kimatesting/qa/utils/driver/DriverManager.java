package com.kimatesting.qa.utils.driver;


import com.kimatesting.qa.enums.Browser;
import com.kimatesting.qa.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DriverManager {
    private static DriverFactory driverFactory = new DriverFactory();
    private static WebDriver driver;

    public static void initDriver() throws Exception {
        Browser browser = Browser.valueOf(ConfigLoader.getProperty("browser"));
        Boolean browserHeadless = Boolean.valueOf(ConfigLoader.getProperty("browserHeadless"));
        int browserWidth = Integer.parseInt(ConfigLoader.getProperty("browserWidth"));
        int browserHeight = Integer.parseInt(ConfigLoader.getProperty("browserHeight"));
        driver = driverFactory.createDriver(browser,browserHeadless, browserWidth, browserHeight);
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
