package com.kimatesting.qa.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverManager {
    private static WebDriver driver;

    public static void initDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void closeDriver(){
        if(driver != null){
            driver.quit();
        }
    }
}
