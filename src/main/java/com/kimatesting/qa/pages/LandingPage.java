package com.kimatesting.qa.pages;

import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage{

    public LandingPage(WebDriver pDriver) {
        super(pDriver);
    }

    public void goToKIMATestingPage(){
        navigateTo("https://www.kimatesting.com/");
    }

    public String getKIMATestingTitle() {
        return getTitle();
    }
}
