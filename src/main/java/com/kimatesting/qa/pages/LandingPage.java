package com.kimatesting.qa.pages;

import com.kimatesting.qa.enums.FindBy;
import com.kimatesting.qa.enums.MenuSections;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends BasePage{

    public LandingPage(WebDriver pDriver) {
        super(pDriver);
    }

    private String contactButton = "a[href='#contact']";
    private String contactSectionTitle = "//h2[contains(text(),'¡Contáctanos!')]";

    public void goToKIMATestingPage(){
        navigateTo("https://www.kimatesting.com/");
    }

    public String getKIMATestingTitle() {
        return getTitle();
    }

    public void goToMenuSection(MenuSections sectionName) {
        clickElement(String.format("a[href='%s']", sectionName.getHref()), "Section: "+sectionName.getHref(), FindBy.css);
    }

    public String getSectionUrl() throws InterruptedException {
        waitFixTime();
        return getPageUrl();
    }

    public void clickOnContactButton() {
        clickElement(contactButton, "Contact Button", FindBy.css);
    }

    public WebElement getContactSectionTitle() {
        return findElementByXpath(contactSectionTitle);
    }
}
